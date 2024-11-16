package org.example.ps27852_lab7.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Repository.ProductRepository;
import org.example.ps27852_lab7.Service.ItemService;
import org.example.ps27852_lab7.entity.Item;
import org.example.ps27852_lab7.entity.Product;
import org.example.ps27852_lab7.unti.SessionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ProductRepository productRepository;
    private final SessionService sessionService;

    @Override
    public Map<Integer, Item> getAllItems() {
        HashMap<Integer, Item> sessionItems = sessionService.get("Carts");
        System.out.println("cart session: " + sessionItems + "");
        return (sessionItems != null) ? sessionItems : new HashMap<>();
    }

    @Override
    public void deleteItemById(Integer id) {
        HashMap<Integer, Item> sessionItems = sessionService.get("Carts");
        if (sessionItems != null) {
            sessionItems.remove(id);
            sessionService.set("Carts", sessionItems);  // Cập nhật lại giỏ hàng trong session
        }
    }

    @Override
    public void updateItem(Integer id, int qty) {
        HashMap<Integer, Item> sessionItems = sessionService.get("Carts");
        if (sessionItems != null && sessionItems.containsKey(id)) {
            sessionItems.get(id).setQuantity(qty);
            sessionService.set("Carts", sessionItems);  // Cập nhật lại giỏ hàng trong session
        }
    }

    @Override
    public Item getItemById(Integer id) {
        HashMap<Integer, Item> sessionItems = sessionService.get("Carts");
        return (sessionItems != null) ? sessionItems.get(id) : null;
    }

    @Override
    public void createItem(Integer id) {
        HashMap<Integer, Item> sessionItems = sessionService.get("Carts");
        if (sessionItems == null) {
            sessionItems = new HashMap<>();  // Khởi tạo giỏ hàng nếu chưa có
        }

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        Item item = new Item(
                product.getId(),
                product.getName(),
                product.getImage(),
                product.getPrice(),
                product.getCategory().getName(),
                1);
        if (sessionItems.containsKey(id)) {
            sessionItems.get(id).setQuantity(sessionItems.get(id).getQuantity() + 1);
        } else {
            sessionItems.put(id, item);
        }
        sessionService.set("Carts", sessionItems);  // Cập nhật lại giỏ hàng trong session
    }

    public void clearCart() {
        HashMap<Integer, Item> sessionItems = sessionService.get("Carts");
        if (sessionItems != null) {
            sessionItems.clear();  // Xóa giỏ hàng trong session
            sessionService.set("Carts", sessionItems);
        }
    }

    @Override
    public Double totalPrice() {
        HashMap<Integer, Item> sessionItems = sessionService.get("Carts");
        Double total = 0.0;
        if (sessionItems != null) {
            for (Map.Entry<Integer, Item> entry : sessionItems.entrySet()) {
                total += entry.getValue().getQuantity() * entry.getValue().getPrice();
            }
        }
        return total;
    }
}
