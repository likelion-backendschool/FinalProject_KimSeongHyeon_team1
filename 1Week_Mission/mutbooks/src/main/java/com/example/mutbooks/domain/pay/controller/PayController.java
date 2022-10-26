package com.example.mutbooks.domain.pay.controller;

import com.example.mutbooks.domain.cart.service.CartService;
import com.example.mutbooks.domain.order.dao.OrderHashMapCache;
import com.example.mutbooks.domain.order.entity.Order;
import com.example.mutbooks.domain.order.service.OrderService;
import com.example.mutbooks.domain.pay.dto.PaySuccessDto;
import com.example.mutbooks.domain.pay.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PayController {

    private final PayService payService;
    private final OrderService orderService;
    private final CartService cartService;
    private final OrderHashMapCache orderHashMapCache;

    /* 카카오페이 보여주기 */
    @GetMapping("/order/{id}/pay")
    public String showKakaoPay(@PathVariable Long id) {

        return "redirect:" + payService.doKakaoPay(id);
    }

    /* 결제 성공 */
    @GetMapping("/order/{id}/paySuccess")
    public String showKakaoPayHistory(@RequestParam("pg_token") String pg_token, Model model,
                                      @PathVariable Long id) {

        Order order = orderService.findById(id);
        cartService.modifyIsOrdered(order);

        payService.approveKakaoPay(pg_token, id);
        PaySuccessDto paySuccessDto = orderService.getPaySuccessDto(order);
        orderHashMapCache.removeOrderDtoFromCart(paySuccessDto.getUsername());

        model.addAttribute("paySuccessDto", paySuccessDto);
        return "pay/kakaoPaySuccess";
    }

    /* 결제취소 */
    @GetMapping("/order/{orderId}/canclePay")
    public String cancelKakaoPay(@PathVariable String storeSN, @PathVariable Long orderId) {

        payService.cancelKakaoPay(orderId);
        orderService.cancelOrderByUser(orderId);
        return "redirect:/%s/cart".formatted(storeSN);
    }
}
