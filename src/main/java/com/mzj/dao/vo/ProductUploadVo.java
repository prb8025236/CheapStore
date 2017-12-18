package com.mzj.dao.vo;

/**
 * Created by Administrator on 2017/11/30.
 */
public class ProductUploadVo {
    private String uri;
    private String url;

    public ProductUploadVo() {
    }

    public ProductUploadVo(String uri, String url) {
        this.uri = uri;
        this.url = url;
    }

    public ProductUploadVo(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ProductUploadVo{" +
                "uri='" + uri + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
