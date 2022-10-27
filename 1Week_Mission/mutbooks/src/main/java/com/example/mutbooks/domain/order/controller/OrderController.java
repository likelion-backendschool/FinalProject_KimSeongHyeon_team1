package com.example.mutbooks.domain.order.controller;

import com.example.mutbooks.domain.order.dao.OrderHashMapCache;
import com.example.mutbooks.domain.order.dto.OrderDtoFromCart;
import com.example.mutbooks.domain.order.dto.OrderFormDto;
import com.example.mutbooks.domain.order.entity.Order;
import com.example.mutbooks.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderHashMapCache orderHashMapCache;


    /*주문하기*/
    @PostMapping("/order/create")
    public String createOrderDtoFormCart(HttpServletRequest request, @CookieValue("userLogin") String userKey,
                                         @ModelAttribute OrderDtoFromCart orderDtoFromCart) {
        HttpSession session = request.getSession(true);
        String username = session.getAttribute(userKey).toString();

        orderDtoFromCart.setUsername(username);

        orderHashMapCache.putOrderDtoFromCart(orderDtoFromCart, username);
        return "redirect:/pay";
    }

    /*결제하기*/
    @GetMapping("/pay")
    public String showOrder(HttpServletRequest request, @CookieValue("userLogin") String userKey,
                            Model model) {

        HttpSession session = request.getSession(true);
        String username = session.getAttribute(userKey).toString();

        OrderDtoFromCart dto = orderHashMapCache.getOrderDtoFromCart(username);

        model.addAttribute("orderFormDto", new OrderFormDto());
        model.addAttribute("totalPrice", dto.getTotalPrice());
        model.addAttribute("username", username);

        return "order/orderForm";
    }

    @PostMapping("/pay")
    public String doOrder(HttpServletRequest request,
                          @CookieValue("userLogin") String userKey, @ModelAttribute OrderFormDto orderFormDto) {

        HttpSession session = request.getSession(true);
        String username = session.getAttribute(userKey).toString();

        OrderDtoFromCart orderDtoFromCart = orderHashMapCache.getOrderDtoFromCart(username);
        Order order = orderService.save(orderDtoFromCart, orderFormDto);

        return "redirect:/order/" + order.getId() + "/pay";
    }

}
