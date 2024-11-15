package com.quangbui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quangbui.model.Product;
import com.quangbui.repository.ProductRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class ShoppingCartImpl implements ShoppingCartService {

    private Map<Integer, Product> cart = new HashMap<>();

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addToCart(int id) {
        Product product = cart.get(id);
        if (product == null) {
            product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
            product.setQuantity(1);
            cart.put(id, product);
        } else {
            product.setQuantity(product.getQuantity() + 1);
        }
    }

    @Override
    public List<Product> getCart() {
        return new ArrayList<>(cart.values());
    }

    @Override
    public void clearCart() {
        cart.clear();
    }

    @Override
    public void removeFromCart(int id) {
        cart.remove(id);
    }

    public int getCartSize() {
		return cart.size();
	}
    
    public void increaseQuantity(int id) {
        Product product = cart.get(id);
        if (product != null) {
            product.setQuantity(product.getQuantity() + 1);
        }
    }

    public void decreaseQuantity(int id) {
        Product product = cart.get(id);
        if (product != null && product.getQuantity() > 1) {
			product.setQuantity(product.getQuantity() - 1);
		}
	}
}