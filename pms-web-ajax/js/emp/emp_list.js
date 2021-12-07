//jQuery.ajaxSettings.traditional = true //将数组类型的json数据通过传统方法提交到后台 id:[1,2,3]==>id=1&id=2&id=3
$.fn.serializeJson=function (){
    //将表单对象转换成jQUery对象
    let $this = $(this);//<form>
    //得到表单对象的序列数据，json数组
    let params = $this.serializeArray();//[{name:"empName", value=""xxx},{name:"gender", value:1},{name:"birthday",value=""}]
    //console.log(JSON.stringify(params));
    //var s = JSON.stringify(params);js中将JSON对象转换成json字符串
    //JSON.parse(s);//js中将json字符串解析成json对象

    /*
    [
        {"name":"empName","value":"zhangsan"},
        {"name":"gender","value":"1"},
        {"name":"birthday","value":"1990-01-01"},
        {"name":"salary","value":"10"},
        {"name":"hiredate","value":"2020-10-20"},
        {"name":"hobbies","value":"2"},
        {"name":"hobbies","value":"3"}
    ]
    ===>
    {
        "empName":"zhangsan",
        "gender":"2",
        "birthday":"1990-01-01",
        "salary":"22",
        "hiredate":"2020-10-20",
        "hobbies":[2,3]
    }
     */
    //需要返回json
    let data = {};//{empName:"xxxx", gender:1}
    for (let i = 0; i < params.length; i++) {
        let param = params[i];//{name:"empName", value=""xxx}
        let paramName = param.name;//表单名:empName
        let paramValue = param.value;//表单值 xxxx
        if(!data[paramName]){
            //如果data对象中还不存在该属性，则直接添加新的属性
            data[paramName] = paramValue;
        }else{//已经存在某属性,说明这个属性的值应该是数组类型
            if($.type(data[paramName])!=="array"){
                //先将原来的属性值变成数组类型，并将原来的值存放到数组中。
                let oldValue = data[paramName];//2
                data[paramName] = [];
                data[paramName].push(oldValue);
            }
            data[paramName].push(paramValue);
        }
    }
    return  data;

}
let empMgr;

$(function (){
    empMgr = {
        page:1,
        pm:{},
        delId:[],//删除的id
        loadEmpList:function (btnIdx){
            console.log(this.pm);
            if(btnIdx==1){
                this.page = 1;
            }else if(btnIdx==2){
                this.page = this.pm.prePage;
            }else if(btnIdx==3){
                this.page = this.pm.nextPage;
            }else if(btnIdx == 4){
                this.page = this.pm.lastPage;
            }


            let that = this;
            $.get("http://192.168.152.129:8888/pms_service_war_exploded/emp/list",{
                selectCondition:"",
                selectKeyWord:"",
                pageNo:this.page,
                pageSize:10
            },function (data){

                if(data.code==200){
                    that.pm = data.data;
                    let list = that.pm.data;

                    console.log(that.pm);
                    console.log(list)
                    //将分页信息显示到页面
                    $("#pageTotal").html(that.pm.total);
                    $("#pageCount").html(that.pm.totalPage);
                    $("#pageNo").html(that.pm.pageNo);

                    if(list.length<1){
                        let trNode = `<tr>
                                        <td colspan="7">对不起没有符合要求的数据!</td>
                                    </tr>`;
                        $(trNode).appendTo("#data-list");
                    }else{
                        //把原来的数据清空
                        $(".data-list-tr").remove();
                        for(let i=0; i<list.length; i++){
                            let emp = list[i];
                            let trNode = `<tr class="data-list-tr">
                                        <td><input type="checkbox" value="${emp.id}"></td>
                                        <td>${emp.empName}</td>
                                        <td>${emp.gender==1?"男":"女"}</td>
                                        //json日期数据传过来是一串数字 将他转换成日期型
                                        <td>${new Date(emp.birthday).pattern("yyyy-MM-dd")}</td>
                                        <td>${new Date(emp.hiredate).pattern("yyyy-MM-dd")}</td>
                                        <td>${emp.salary}</td>
                                        <td>
                                            <button class="btn btn-primary" data-toggle="modal" data-target="#addWindow" onclick="empMgr.openEditWindow(${emp.id})">编辑</button>
                                            <button class="btn btn-danger" data-toggle="modal" data-target="#deleteWindow" onclick="empMgr.openDelWindow('${emp.empName}',${emp.id})">删除</button>
                                        </td>
                                    </tr>`;
                            $(trNode).appendTo("#data-list");
                        }

                    }


                }
            });
        },
        findEmpList:function (){
            this.selectCondition = $("#selectCondition").val();
            this.selectKeyWord = $("#selectKeyWord").val();
            this.loadEmpList(1);
        },
        delEmpById:function (){
            let that = this;
            //"http://localhost:9001/pms-service/emp/del?id=1,2,3,4 request.getParameter("id");
            //"http://localhost:9001/pms-service/emp/del?id[]=1&id[]=2&id[]=3&id[]=4  request.getParameterValues("id[]");
            $.get("http://192.168.152.129:8888/pms_service_war_exploded/emp/del",{id:this.delId},function (data){

                //删除操作完成刷新本页面
                window.location.reload();

               if(data.code==200){
                   $('#deleteWindow').modal('hide');//隐藏删除框
                   $("#alertWindowBody").html(data.message);
                   $("#alertWindow").toast("show");
                   that.loadEmpList(1);

               }
            })
        },
        findById:function (){

        },
        saveEmp:function (){
            // console.log($("#empForm").serialize());//将表单数据序列化empName=bootstarp&gender=1&birthday=1990-01-01&hiredate=2021-11-10&hobbies=1&hobbies=3
            // console.log($("#empForm").serializeArray());//[{name:"empName", value=""xxx}，｛｝]===》{empName:xxx, gender:1}
            // console.log($("#empForm").serializeJson());;//{empName:"xxx", gender:1, ...}
            /* $.get("http://localhost:9001/pms-service/emp/save?"+$("#empForm").serialize(),
                 function (data){
                     if(data.code == 200){
                         $('#addWindow').modal('hide');//隐藏编辑框
                         $("#alertWindowBody").html(data.message);
                         $("#alertWindow").toast("show");
                         that.loadEmpList();
                     }
                 });
 */

            let that = this;
            /*$.post("http://localhost:9001/pms-service/emp/save",
                {
                    empName:$("#empName").val(),
                    gender:$("form input:radio:checked").val(),
                    birthday:$("#birthday").val(),
                    salary:$("#salary").val(),
                    hiredate:$("#hiredate").val()
                },
                function (data){
                   if(data.code == 200){
                       $('#addWindow').modal('hide');//隐藏编辑框
                       $("#alertWindowBody").html(data.message);
                       $("#alertWindow").toast("show");
                       that.loadEmpList();
                   }
            });*/

            $.post("http://192.168.152.129:8888/pms_service_war_exploded/emp/save",
                $("#empForm").serializeJson(),
                function (data){
                    if(data.code == 200){
                        $('#addWindow').modal('hide');//隐藏编辑框
                        $("#alertWindowBody").html(data.message);
                        $("#alertWindow").toast("show");
                        that.loadEmpList();
                        window.location.reload();
                    }
                });

        },
        openEditWindow(empId){
            $.get("http://192.168.152.129:8888/pms_service_war_exploded/emp/get",{id:empId},function (data){
                if(data.code==200){
                    let emp = data.data;
                    $("#empId").val(emp.id);
                    $("#empName").val(emp.empName);
                    if(emp.gender==1){
                        $("#gender1").attr("checked",true);
                    } else{
                        $("#gender2").attr("checked",true);
                    }

                    $("#birthday").val(new Date(emp.birthday).pattern("yyyy-MM-dd"));
                        $("#salary").val(emp.salary);
                        $("#hiredate").val(new Date(emp.hiredate).pattern("yyyy-MM-dd"));
                }

            })
        },
        //删除一个
        openDelWindow(name, id){//打开删除对话框
            $("#deleteWindowBody").html(name);
            this.delId.splice(0);//清除上一次选中的员工id
            //将要删除的员工id进行保存
            this.delId.push(id);

        },
        //批量删除
        openCheckedDelWindow(){//打开删除所选员工对话框
            let checkedCheckboxs = $("tr.data-list-tr  input:checkbox:checked");
            if(checkedCheckboxs.length<1){
                //alert("对不起，请选择要删除的员工!");
                $("#alertWindowBody").html("对不起，请选择要删除的员工!");
                $("#alertWindow").toast("show");
            }else{
                //循环所有选中的checkbox
                //let ids = [];
                let names = [];
                let that = this;
                this.delId.splice(0);//清除上一次选中的员工id
                checkedCheckboxs.each(function (){
                    let id = $(this).val();
                    let name = $(this).parent().next().html()
                    //ids.push(id);
                    //将要删除的员工id进行保存
                    that.delId.push(id);
                    names.push(name);
                });
                $("#deleteWindowBody").html(names.join(","));

                $('#deleteWindow').modal('show');//显示删除框
            }
        }

    }

    //加载数据列表
    empMgr.loadEmpList();

});//ready函数
