package com.ulasgergerli.virtucart.VirtuCart.Product;

import com.ulasgergerli.virtucart.VirtuCart.Subscribers.ProductNotification;
import com.ulasgergerli.virtucart.VirtuCart.Subscribers.ProductNotificationRepository;
import com.ulasgergerli.virtucart.VirtuCart.Subscribers.ProductSubscriber;
import com.ulasgergerli.virtucart.VirtuCart.Subscribers.ProductSubscriberRepository;
import com.ulasgergerli.virtucart.VirtuCart.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductSubscriberRepository productSubscriberRepository;
    private final ProductNotificationRepository productNotificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository
    , ProductSubscriberRepository productSubscriberRepository
    , ProductNotificationRepository productNotificationRepository
    , UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productSubscriberRepository = productSubscriberRepository;
        this.productNotificationRepository = productNotificationRepository;
        this.userRepository = userRepository;
    }

    public Product addProduct(Product product) {
        return productRepository
                .save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository
                .findAll();
    }

    public Product updateProduct(Product product) {

        var subscribers = productSubscriberRepository.findAllByProductId(product.getId());

        for(var subscriber : subscribers) {

            if(subscriber.getProduct().getPrice() == product.getPrice())
                continue;

            var productNotification = ProductNotification.builder()
                    .productSubscriber(subscriber)
                    .initialPrice(subscriber.getProduct().getPrice())
                    .changedPrice(product.getPrice())
                    .isNotified(false)
                    .build();

            productNotificationRepository.save(productNotification);
        }

        return productRepository
                .save(product);
    }

    public void deleteProduct(Long id) {
        productRepository
                .deleteById(id);
    }

    public Product getProduct(Long id) {
        return productRepository
                .findById(id)
                .orElse(null);
    }

    public Product getProductByName(String name) {
        return productRepository
                .findByName(name);
    }

    public List<Product> getProductsByNameContaining(String keyword) {
        return productRepository
                .findAllByNameContaining(keyword);
    }

    public void subscribeToProduct(Long userId, Long productId) {

        var product = productRepository
                .findById(productId)
                .orElse(null);

        if(product == null)
            throw new RuntimeException("Product not found");

        var user = userRepository
                .findById(userId)
                .orElse(null);

        if(user == null)
            throw new RuntimeException("User not found");

        var productSubscriber = productSubscriberRepository
                .findByProductIdAndUserId(productId, userId).orElse(null);

        if(productSubscriber != null)
            throw new RuntimeException("Already subscribed");

        productSubscriber = productSubscriberRepository
                .save(ProductSubscriber.builder()
                        .user(user)
                        .product(product)
                        .build());
    }
}
