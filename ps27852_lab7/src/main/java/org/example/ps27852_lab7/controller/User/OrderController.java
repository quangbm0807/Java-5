package org.example.ps27852_lab7.controller.User;

import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Service.Impl.ItemServiceImpl;
import org.example.ps27852_lab7.Service.OrderDetailService;
import org.example.ps27852_lab7.Service.OrderService;
import org.example.ps27852_lab7.Service.ProductService;
import org.example.ps27852_lab7.entity.*;
import org.example.ps27852_lab7.unti.RandomString;
import org.example.ps27852_lab7.unti.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class OrderController {

    private final ProductService productService;
    private final ItemServiceImpl itemServiceImpl;
    public Product product;

    private final OrderService orderService;

    private final OrderDetailService orderDetailService;

    private final SessionService sessionService;

    // Trong phương thức /ThanhToan, lấy giỏ hàng từ session và tính tổng số tiền
    @GetMapping("/ThanhToan")
    public String thanhToan(Model model) {
        Account account = (Account) sessionService.get("loggedInUser");
        if (account == null) {
            return "redirect:/login";
        } else {
            // Tạo order
            Order order = new Order();
            order.setCode(RandomString.generateRandomString(10));
            order.setCreateDate(new Date());
            order.setStatus("Chưa thanh toán");
            order.setAccount(account);
            orderService.createOrder(order);
            sessionService.set("order", order);

            // Tính tổng tiền của giỏ hàng
            HashMap<Integer, Item> cart = sessionService.get("Carts");
            long totalAmount = 0;
            for (Map.Entry<Integer, Item> entry : cart.entrySet()) {
                totalAmount += entry.getValue().getPrice() * entry.getValue().getQuantity();
            }
            return "redirect:/payment/vnpay?amount=" + totalAmount;
        }

    }

    @GetMapping("/PaySuccess")
    public String paymentResult(@RequestParam Map<String,String> allParams, Model model) {
        // Lấy mã trạng thái giao dịch
        String transactionStatus = allParams.get("vnp_ResponseCode");
        String transactionId = allParams.get("vnp_TransactionNo");

        Order order = (Order)sessionService.get("order");
        Account account =(Account) sessionService.get("loggedInUser");
        HashMap<Integer, Item> cart = sessionService.get("Carts");
        // Kiểm tra trạng thái giao dịch
        if ("00".equals(transactionStatus)) {
            for (Map.Entry<Integer, Item> entry : cart.entrySet()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                Product product = productService.getProduct(entry.getValue().getId());
                orderDetail.setProduct(product);
                orderDetail.setQuantity(entry.getValue().getQuantity());
                orderDetail.setPrice(entry.getValue().getPrice() * entry.getValue().getQuantity());
                orderDetailService.createOrderDetail(orderDetail);
            model.addAttribute("message", "Giao dịch thành công.");
            model.addAttribute("status", "success");  // Giao dịch thành công
            }
                order.setStatus("Đã thanh toán");
                orderService.createOrder(order);
            sessionService.remove("Carts");
            sessionService.remove("order");
            }
        else if ("24".equals(transactionStatus)) {
            System.out.println("id order:" + "  = " + order.getId());
            orderService.deleteOrderById(order.getId());
            model.addAttribute("message", "Giao dịch đã bị hủy.");
            model.addAttribute("status", "failed");  // Giao dịch thất bại do người dùng hủy
        } else {
            model.addAttribute("message", "Giao dịch thất bại với mã: " + transactionStatus);
            model.addAttribute("status", "failed");  // Giao dịch thất bại vì lý do khác
            orderService.deleteOrderById(order.getId());
        }

        model.addAttribute("transactionId", transactionId);

        return "/user/PaySuccess";
    }
    }
