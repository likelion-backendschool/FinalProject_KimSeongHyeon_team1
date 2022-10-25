package com.example.mutbooks.domain.product.controller;

import com.example.mutbooks.domain.cart.entity.Cart;
import com.example.mutbooks.domain.cart.service.CartService;
import com.example.mutbooks.domain.product.dto.ProductDetailFormDto;
import com.example.mutbooks.domain.product.entity.Product;
import com.example.mutbooks.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    /*기본창*/
    @GetMapping("/product/list")
    public String showProductView(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);

        return "product/product_list";
    }

    /* 도서 상세보기 */
    @GetMapping("/product/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("id", id);


        ProductDetailFormDto productDetailFormDto = productService.getProductFormById(product);
        model.addAttribute("productDetailFormDto", productDetailFormDto);
        return "product/product_detail";
    }

    @PostMapping("/product/{id}")
    public String saveCart(@PathVariable Long id, @ModelAttribute ProductDetailFormDto productDetailFormDto,
                           HttpServletRequest request, @CookieValue("userLogin") String userKey){
        HttpSession session = request.getSession(true);
        String username = session.getAttribute(userKey).toString();
        Product product = productService.findById(id);

        Cart cart = cartService.save(productDetailFormDto, username, product);

        return "redirect:/" + storeSN + "/menu";
    }
}
