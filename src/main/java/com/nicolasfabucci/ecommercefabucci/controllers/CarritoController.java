package com.nicolasfabucci.ecommercefabucci.controllers;

import com.nicolasfabucci.ecommercefabucci.models.documents.CarritoItem;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.CarritoItemRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.CarritoRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.responses.CarritoResponse;
import com.nicolasfabucci.ecommercefabucci.service.CarritoServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api")
@RestController
@AllArgsConstructor
public class CarritoController {
    private final CarritoServiceImp cartServiceImpl;

    @GetMapping(value="/cart/{userId}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CarritoResponse> getCart(@PathVariable(name="userId") String userId) {
        CarritoResponse cart = cartServiceImpl.getCartByUser(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping(value="/cart", produces={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CarritoResponse> createCart(@RequestBody CarritoRequest cart) {
        CarritoResponse createCart = cartServiceImpl.createCart(cart);
        return ResponseEntity.ok(createCart);
    }

    @DeleteMapping(value="/cart/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteCart(String userId) {
        cartServiceImpl.deleteCart(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value="/cart/{userId}/product")
    public ResponseEntity<List<CarritoItem>> getCartProducts(@PathVariable(name="userId") String userId) {
        List<CarritoItem> products = cartServiceImpl.getCartProducts(userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping(value="/cart/{userId}/product/{productId}")
    public ResponseEntity<?> getCartProducts(@PathVariable(name="userId") String userId, @PathVariable(name="productId") String productId) {
        Optional<CarritoItem> product = cartServiceImpl.getCartProductById(userId, productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping(value="/cart/{userId}/product/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable(name="userId") String userId, @PathVariable(name="productId") Long productId, @RequestBody CarritoItemRequest product) {
        cartServiceImpl.addProductToCart(userId, productId, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value="/cart/{userId}/product/{productId}")
    public ResponseEntity<?> deleteProductInCart(@PathVariable(name="userId") String cartId, @PathVariable(name="productId") Long productId) {
        cartServiceImpl.deleteProductOnCart(cartId, productId);
        return ResponseEntity.noContent().build();
    }
}
