package com.quangbui.service;

import java.util.List;

import com.quangbui.model.Product;

public interface ShoppingCartService {

	void addToCart(int id);

	void clearCart();

	List<Product> getCart();

	void removeFromCart(int id);
	
}
