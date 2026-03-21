package vn.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderViewController {

    @GetMapping
    public String orderList() {
        return "orders/order-list";    // → templates/order-list.html
    }

    @GetMapping("/create")
    public String createOrder() {
        return "orders/create-order";  // → templates/create-order.html
    }
}