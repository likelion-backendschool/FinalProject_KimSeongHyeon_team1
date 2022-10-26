package com.example.mutbooks.domain.cart.service;

import com.example.mutbooks.domain.cart.dto.CartDto;
import com.example.mutbooks.domain.cart.dto.CartListDto;
import com.example.mutbooks.domain.cart.entity.Cart;
import com.example.mutbooks.domain.cart.repository.CartRepository;
import com.example.mutbooks.domain.product.dto.ProductDetailFormDto;
import com.example.mutbooks.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    public Cart save(ProductDetailFormDto productDetailFormDto, String username, Product product) {
        Cart cart = Cart.builder().
                username(username)
                .count(productDetailFormDto.getCount())
                .isOrdered(false)
                .product(product)
                .build();

        return cartRepository.save(cart);
    }

    public CartListDto getCartListByUsername(String username) {
        //주문상태가 false인 값을 가져온다.
        List<CartDto> cartDtos = getCartDtoListByUsername(username, false);

        CartListDto cartListDto = CartListDto.builder()
                .cartDtos(cartDtos)
                .orderType(null)
                .totalPrice(calculateTotalPrice(cartDtos))
                .build();

        return cartListDto;
    }

    private int calculateTotalPrice(List<CartDto> cartDtos) {
        int totalPrice = 0;

        for (CartDto cartDto : cartDtos) {
            int price = cartDto.getProduct().getPrice();

            totalPrice += (price * cartDto.getCount());
        }
        return totalPrice;
    }

    public List<CartDto> getCartDtoListByUsername(String nickname, boolean isOrdered) {

        List<Cart> carts = cartRepository.findByUsernameAndIsOrdered(nickname, isOrdered);
        List<CartDto> cartDtoList = new ArrayList<>();

        for (Cart cart : carts) {
            CartDto cartDto = CartDto.builder()
                    .cartId(cart.getId())
                    .product(cart.getProduct())
                    .count(cart.getCount())
                    .eachMenuTotalPrice(calculateEachMenuTotalPrice(cart))
                    .build();
            cartDtoList.add(cartDto);
        }
        return cartDtoList;
    }

    public int calculateEachMenuTotalPrice(Cart cart) {
        int menuTotalPrice = 0;
        int price = cart.getProduct().getPrice();
        /*나중에 옵션추가할 수 있도록*/
        menuTotalPrice += price;
        return menuTotalPrice;
    }
}
