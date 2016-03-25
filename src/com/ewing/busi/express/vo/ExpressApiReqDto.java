package com.ewing.busi.express.vo;

/**
 * 快递请求参数
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月21日
 *
 */
public class ExpressApiReqDto {
    
    /**
     * 默认json返回
     */
    private final String DEFAULT_SHOW = "1";
    
    /**
     * 身份授权key，请至 身份授权key申请 进行申请
     */
    private String id;
    
    /**
     * 快递公司代号，详见<a href="">《快递公司代号对照表》</a>
     */
    private String com;
    
    /**
     * 要查询的快递单号
     */
    private String num;
    
    /**
     * 数据返回类型（默认为xml格式）：0：返回xml格式 1：返回json格式
     */
    private String show;

    public ExpressApiReqDto(String id, String com, String num, String show) {
        super();
        this.id = id;
        this.com = com;
        this.num = num;
        this.show = show;
    }

    public ExpressApiReqDto(String id, String com, String num) {
        super();
        this.id = id;
        this.com = com;
        this.num = num;
        this.show = DEFAULT_SHOW;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }



    
}
