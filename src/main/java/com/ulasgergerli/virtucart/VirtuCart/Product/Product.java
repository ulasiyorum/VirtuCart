package com.ulasgergerli.virtucart.VirtuCart.Product;

import com.ulasgergerli.virtucart.VirtuCart.Category.Category;
import com.ulasgergerli.virtucart.VirtuCart.User.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images;

    @ManyToMany(mappedBy = "products")
    private List<Category> categories;

    @ManyToOne
    private User user;

    public Product() {
    }

    public Product(String name, String description, double price, List<String> images, List<Category> categories) {
        this.name = name;
        this.description = description;
        this.images = images;
        this.price = price;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
