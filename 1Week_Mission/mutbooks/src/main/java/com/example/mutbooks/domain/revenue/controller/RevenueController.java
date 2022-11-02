package com.example.mutbooks.domain.revenue.controller;

import com.example.mutbooks.domain.member.entity.Member;
import com.example.mutbooks.domain.member.service.MemberService;
import com.example.mutbooks.domain.order.entity.Order;
import com.example.mutbooks.domain.order.entity.enumulation.OrderStatus;
import com.example.mutbooks.domain.order.entity.enumulation.OrderType;
import com.example.mutbooks.domain.order.service.OrderService;
import com.example.mutbooks.domain.pay.entity.enumulation.PayStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RevenueController {

    private final MemberService memberService;
    private final OrderService orderService;


    @ModelAttribute("orderStatus")
    public OrderStatus[] orderStatuses() {
        return OrderStatus.values();
    }
    @ModelAttribute("payStatus")
    public PayStatus[] payStatuses() {
        return PayStatus.values();
    }
    @ModelAttribute("orderType")
    public OrderType[] orderTypes() {
        return OrderType.values();
    }

    /*주문내역전체 페이지*/
    @GetMapping("/adm/rebate/rebateOrderItemList")
    public String redirectTotalOrderList(Principal principal){
        Optional<Member> member = memberService.findByUsername(principal.getName());

        /*empty가 아니라 권한에 따라 접속허용을 해야한다.*/
        if (member.isEmpty()) {
            return "redirect:/home";
        }
        return "redirect:/%s/adm/rebate/rebateOrderItemList".formatted(member.get().getUsername());
    }

    @GetMapping("/{username}/adm/rebate/rebateOrderItemList")
    public String showTotalOrderList(Model model, @PathVariable String username){
        Optional<Member> member = memberService.findByUsername(username);
        List<Order> orderLists = orderService.findAllByUsername(username);
        model.addAttribute("username", username);
        model.addAttribute("orderLists", orderLists);

        return "revenue/orderList";
    }




    /*주문내역 건별 페이지*/
    @GetMapping("/{username}/adm/rebate/rebateOrderItemList/{orderId}")
    public String showTotalOrderDetail(Model model , @PathVariable String username, @PathVariable Long orderId){
        /*해당 id의 order 객체만을 가져옴*/
        Order findOrder = orderService.findById(orderId);
        model.addAttribute("username", username);
        model.addAttribute("findOrder", findOrder);
        return "revenue/orderDetail";
    }
}
