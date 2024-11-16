package org.example.ps27852_lab7.Service;

import org.example.ps27852_lab7.entity.Item;

import java.util.Map;

public interface ItemService {

    Map<Integer, Item> getAllItems();

    void deleteItemById(Integer id);

    void updateItem(Integer id, int qty);

    Item getItemById(Integer id);

    void createItem(Integer id);

    void clearCart();

    Double totalPrice();
}