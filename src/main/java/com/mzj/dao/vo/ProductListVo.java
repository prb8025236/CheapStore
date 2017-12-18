package com.mzj.dao.vo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/11/29 0029.
 */
public class ProductListVo {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String subtitle;
    private String mainImage;
    private Integer status;
    private BigDecimal price;
    private String imageHost;

    @Override
    public String toString() {
        return "ProductListVo{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", imageHost='" + imageHost + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public ProductListVo(Integer categoryId, String name, String subtitle, String mainImage, Integer status, BigDecimal price, String imageHost) {

        this.categoryId = categoryId;
        this.name = name;
        this.subtitle = subtitle;
        this.mainImage = mainImage;
        this.status = status;
        this.price = price;
        this.imageHost = imageHost;
    }

    public ProductListVo() {

    }

    public ProductListVo(Integer id, Integer categoryId, String name, String subtitle, String mainImage, Integer status, BigDecimal price, String imageHost) {

        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.subtitle = subtitle;
        this.mainImage = mainImage;
        this.status = status;
        this.price = price;
        this.imageHost = imageHost;
    }
}
