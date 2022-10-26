package com.example.mutbooks.domain.cart.controller;

import com.example.mutbooks.domain.cart.dto.CartListDto;
import com.example.mutbooks.domain.cart.service.CartService;
import com.example.mutbooks.domain.order.dto.OrderDtoFromCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart/list")
    public String showCart(Model model,
                           HttpServletRequest request, @CookieValue("userLogin") String userKey) {

        HttpSession session = request.getSession(true);
        String username = session.getAttribute(userKey).toString();

        CartListDto cartListDto = cartService.getCartListByUsername(username);

        model.addAttribute("cartListDto", cartListDto);
        model.addAttribute("orderDtoFromCart", new OrderDtoFromCart());
        return "cart/cart_list";
    }
}
