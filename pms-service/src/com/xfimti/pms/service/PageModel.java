package com.xfimti.pms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 类的说明：
 *    一页数据
 *      数据集合
 *      翻页的相关信息
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-20 14:16
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class PageModel<T> {
    private List<T>  data; //数据的集合
    private int pageNo;    //当前页
    private int pageSize;  //每页显示的记录数

    private int total;      //数据总条数
    private int totalPage;  //总页数
    private int firstPage;  //首页
    private int nextPage;   //下一页
    private int prePage;    //上一页
    private int lastPage;   //尾页

    private String url;

    private String firstPageUrl;    //首页地址
    private String prePageUrl;      //上一页地址
    private String nextPageUrl;     //下一页地址
    private String lastPageUrl;     //尾页地址

    private Map<String, String> QueryParams = new HashMap<>();//用来存放翻页查询条件

    /**
     * 构造翻页对象
     * @param data  翻页数据集合
     * @param total  总记录数
     * @param pageNo  页码
     * @param pageSize  每页显示的记录条数
     */
    public PageModel(List<T>  data, int total, int pageNo, int pageSize){
        this.data = data;
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;

        //计算总页数
        if(this.total%this.pageSize==0){
            this.totalPage = this.total / this.pageSize;
        }else{
            this.totalPage = this.total / this.pageSize + 1;
        }

        //初始化首页及尾页
        this.firstPage = 1;
        this.lastPage = this.totalPage;

        //初始化下一页
        this.nextPage = this.pageNo + 1 > this.totalPage?this.totalPage:this.pageNo + 1;
        //初始化上一页
        this.prePage = this.pageNo - 1 < 1 ? 1:this.pageNo - 1;

    }


    /**
     *添加查询参数，这个方法需要在setUrl方法之前调用。
     * @param paramName
     * @param paramValue
     * @return  返回PageMode,便于支持java的链式编程
     */
    public PageModel addQueryParam(String paramName, String paramValue){
        QueryParams.put(paramName, paramValue);
        return this;
    }

    /**
     * 设置翻页的地址，如果有查询参数，请先调用addQueryParam方法添加查询参数
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;//   /pms/emp/list
        ///pms/emp/list?pageNo = xxx & xxx
       /* this.firstPageUrl = this.url + "?pageNo=1";
        this.prePageUrl = this.url + "?pageNo="+this.prePage;
        this.nextPageUrl = this.url + "?pageNo="+this.nextPage;
        this.lastPageUrl = this.url + "?pageNo="+this.lastPage;

        if(!QueryParams.isEmpty()){
           for (Map.Entry<String,String> param : QueryParams.entrySet()){
               String paramName = param.getKey();
               String paramValue = param.getValue();
               this.prePageUrl = this.prePageUrl + "&"+paramName+"="+paramValue;
               this.lastPageUrl = this.lastPageUrl + "&"+paramName+"="+paramValue;
               this.nextPageUrl = this.nextPageUrl + "&"+paramName+"="+paramValue;
               this.lastPageUrl = this.lastPageUrl + "&"+paramName+"="+paramValue;
           }
        }*/

        StringBuffer fistUrl = new StringBuffer(this.url);
        fistUrl.append("?pageNo=").append(this.firstPage);
        StringBuffer preUrl = new StringBuffer(this.url);
        preUrl.append("?pageNo=").append(this.prePage);
        StringBuffer nextUrl = new StringBuffer(this.url);
        nextUrl.append("?pageNo=").append(this.nextPage);
        StringBuffer lastUrl = new StringBuffer(this.url);
        lastUrl.append("?pageNo=").append(this.lastPage);

        if(!QueryParams.isEmpty()){
            for (Map.Entry<String,String> param : QueryParams.entrySet()){
                String paramName = param.getKey();
                String paramValue = param.getValue();
                fistUrl.append("&").append(paramName).append("=").append(paramValue);
                preUrl.append("&").append(paramName).append("=").append(paramValue);
                nextUrl.append("&").append(paramName).append("=").append(paramValue);
                lastUrl.append("&").append(paramName).append("=").append(paramValue);
            }
        }

        this.firstPageUrl = fistUrl.toString();
        this.prePageUrl = preUrl.toString();
        this.nextPageUrl = nextUrl.toString();
        this.lastPageUrl = lastUrl.toString();
    }

    public List<T> getData() {
        return data;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public String getUrl() {
        return url;
    }



    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public String getPrePageUrl() {
        return prePageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public String getLastPageUrl() {
        return lastPageUrl;
    }


}
