package com.ulasgergerli.virtucart.VirtuCart.Dtos;

import java.util.List;

public class ProductDto {

    private long id;
    private String name;
    private String description;
    private List<String> images;
    private double price;
    private double discountedPrice;
    private List<Long> categoryIds;
    private long discountId;

    public ProductDto() {
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public long getDiscountId() {
        return discountId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }
}
