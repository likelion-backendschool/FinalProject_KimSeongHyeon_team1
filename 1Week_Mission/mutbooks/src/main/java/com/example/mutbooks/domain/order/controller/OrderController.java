package com.example.mutbooks.domain.order.controller;

import com.example.mutbooks.domain.order.dao.OrderHashMapCache;
import com.example.mutbooks.domain.order.dto.OrderDtoFromCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderHashMapCache orderHashMapCache;

    /*장바구니 담기*/
    @PostMapping("/order/create")
    public String createOrderDtoFormCart(HttpServletRequest request, @CookieValue("userLogin") String userKey,
                                         @ModelAttribute OrderDtoFromCart orderDtoFromCart) {
        HttpSession session = request.getSession(true);
        String username = session.getAttribute(userKey).toString();

        orderDtoFromCart.setUsername(username);

        orderHashMapCache.putOrderDtoFromCart(orderDtoFromCart, username);
        return "redirect:/" + storeSN + "/pay";
    }
}
