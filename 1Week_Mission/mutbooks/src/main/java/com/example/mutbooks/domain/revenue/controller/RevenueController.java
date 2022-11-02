package com.example.mutbooks.domain.revenue.controller;

import com.example.mutbooks.domain.order.entity.Order;
import com.example.mutbooks.domain.order.entity.enumulation.OrderStatus;
import com.example.mutbooks.domain.order.entity.enumulation.OrderType;
import com.example.mutbooks.domain.order.service.OrderService;
import com.example.mutbooks.domain.pay.entity.enumulation.PayStatus;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequiredArgsConstructor
public class RevenueController {

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

    /*주문내역 페이지*/
    @GetMapping("{username}/adm/rebate/rebateOrderItemList")
    public String showTotalOrderList(Model model, @PathVariable String username){
        List<Order> orderLists = orderService.findAllUsername(username);
        model.addAttribute("orderLists", orderLists);
        return "revenue/orderList";
    }


    /*주문내역 상세 페이지*/
    @GetMapping("/{username}/adm/rebate/rebateOrderItemList/{orderId}")
    public String showTotalOrderDetail(Model model , @PathVariable String username, @PathVariable Long orderId){
        /*해당 id의 order 객체만을 가져옴*/
        Order findOrder = orderService.findById(orderId);
        model.addAttribute("findOrder", findOrder);
        return "revenue/orderDetail";
    }

    /*매출관리 페이지*/
    @GetMapping("/{storeSN}/admin/store/management/revenue")
    public String showTotalSalesList(Model model , @PathVariable String storeSN){
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("store", store);
        model.addAttribute("storeSN" , storeSN);
        /*스토어넘버로 모든 주문 내역을 가져온다. order 테이블의 모든 것을 조회하기 위해 객체를 가져옴*/
        List<Order> orderLists = orderService.findAllOrderByStoreSN(storeSN);
        model.addAttribute("orderLists", orderLists);
        return "storeMgmt/revenueList";
    }

    /* 메뉴관리 페이지 */
    @GetMapping("/{storeSN}/admin/store/management/menu")
    public String showMenuMgmt(Model model , @PathVariable String storeSN) {
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("store", store);
        return "storeMgmt/mgmtMenu";
    }

    /*매출관리 정렬 페이지*/
    @GetMapping("/{storeSN}/admin/store/management/revenue/{orderBy}")
    public String showTotalSalesList(Model model , @PathVariable String storeSN, @PathVariable String orderBy){
        Store store = storeService.findBySerialNumber(storeSN);
        model.addAttribute("store", store);
        model.addAttribute("storeSN" , storeSN);

        /*정렬방법에 따라 SQL로 원하는 값을 가져온다.*/
        switch (orderBy) {
            case "price":
                List<FindListFormDto> findList1 = orderService.getFindListFormDtoListForPriceDaily(storeSN);
                //맵으로 정렬
                Map<String, Long> findList = orderService.calculateSumForDay(findList1);
                //최신순 정렬
                Map<String, Long> treeMap = new TreeMap<>(Collections.reverseOrder());
                treeMap.putAll(findList);
                model.addAttribute("findList", treeMap);
                break;
            case "user":
                List<FindByUserDailyDto> findList2 = orderService.getFindByUserDailyDtoListForUserDaily(storeSN);
                //맵으로 정렬
                Map<String, Long> findList3 = orderService.calculateSumForUser(findList2);
                //최신순 정렬
                Map<String, Long> treeMap2 = new TreeMap<>(Collections.reverseOrder());
                treeMap2.putAll(findList3);
                model.addAttribute("findList3", treeMap2);
                break;
        }
        return "storeMgmt/revenueDetail";
    }
}
