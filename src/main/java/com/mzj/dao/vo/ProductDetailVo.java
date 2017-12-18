package com.mzj.dao.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/29.
 */
public class ProductDetailVo {
    private Integer id;
    private Integer categoryId;
    private Integer parentCategoryId;
    private String name;
    private String subtitle;
    private String imageHost;
    private String mainImage;
    private String subImages;
    private String detail;
    private BigDecimal price;
    private Integer stock;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    public ProductDetailVo() {
    }

    public ProductDetailVo(Integer id, Date updateTime, Integer parentCategoryId, Integer categoryId, String imageHost, String subtitle, String name, String subImages, String mainImage, BigDecimal price, Integer status, String detail, Integer stock, Date createTime) {
        this.id = id;
        this.updateTime = updateTime;
        this.parentCategoryId = parentCategoryId;
        this.categoryId = categoryId;
        this.imageHost = imageHost;
        this.subtitle = subtitle;
        this.name = name;
        this.subImages = subImages;
        this.mainImage = mainImage;
        this.price = price;
        this.status = status;
        this.detail = detail;
        this.stock = stock;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProductDetailVo{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", parentCategoryId=" + parentCategoryId +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", imageHost='" + imageHost + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", subImages='" + subImages + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
