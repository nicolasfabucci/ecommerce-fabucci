package com.nicolasfabucci.ecommercefabucci.service;


import com.nicolasfabucci.ecommercefabucci.handler.NotFoundException;
import com.nicolasfabucci.ecommercefabucci.models.documents.CarritoDocument;
import com.nicolasfabucci.ecommercefabucci.models.documents.CarritoItem;
import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoDocument;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.CarritoItemRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.CarritoRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.responses.CarritoResponse;
import com.nicolasfabucci.ecommercefabucci.repositories.CarritoRepository;
import com.nicolasfabucci.ecommercefabucci.repositories.ProductoRepository;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CarritoServiceImp implements CarritoService {
    private final CarritoRepository cartRepository;
    private final ProductoRepository productRepository;

    public List<CarritoDocument> getCarts() {
        return cartRepository.findAll();
    }

    public CarritoResponse createCart(CarritoRequest cart) {
        CarritoDocument createCart = CarritoDocument.builder()
                .fechaHora(LocalDate.now())
                .direccionEntrega(cart.getDireccionEntrega())
                .items(new ArrayList<>())
                .userId(cart.getUserId())
                .build();

        cartRepository.save(createCart);
        CarritoResponse cartResponse = CarritoResponse.builder()
                .fechaHora(createCart.getFechaHora())
                .items(createCart.getItems())
                .direccionEntrega(createCart.getDireccionEntrega())
                .userId(createCart.getUserId())
                .build();
        log.info("Carrito creado existosamente" + LocalDate.now());
        return cartResponse;
    }
    public void deleteCart(String userId) {
        Optional<CarritoDocument> cart = cartRepository.findByUserId(userId);
        if(cart.isPresent()) {
            cartRepository.deleteByUserId(userId);
            log.info("Carrito eliminado existosamente" + LocalDate.now());
        } else {
            log.error("No existe carrito para usuario" + userId + LocalDate.now());
            throw new NotFoundException("No existe carrito con para el usuario de id" + userId);
        }
    }

    public CarritoResponse getCartByUser(String userId) {
        Optional<CarritoDocument> cart = cartRepository.findByUserId(userId);

        if(cart.isPresent()) {
            log.info("Carrito encontrado existosamente" + LocalDate.now());
            return CarritoResponse.builder()
                    .items(cart.get().getItems())
                    .fechaHora(cart.get().getFechaHora())
                    .direccionEntrega(cart.get().getDireccionEntrega())
                    .build();
        } else {
            log.error("No existe carrito para usuario" + userId + LocalDate.now());
            throw new NotFoundException("No existe carrito para el usuario de id " + userId);
        }
    }

    public List<CarritoItem> getCartProducts(String userId) {
        Optional<CarritoDocument> cart = cartRepository.findByUserId(userId);
        if(cart.isPresent()) {
            log.info("Productos del carrito encontrados exitosamente" + LocalDate.now());
            return cart.get().getItems();
        } else {
            log.error("No existe carrito para usuario" + userId + LocalDate.now());
            throw new NotFoundException("No existe carrito para el usuario de id " + userId);
        }
    }

    public Optional<CarritoItem> getCartProductById(String userId, String productId) {
        Optional<CarritoDocument> cart = cartRepository.findByUserId(userId);
        Optional<CarritoItem> cartItem = Optional.empty();

        if(cart.isPresent()) {
            for(CarritoItem c : cart.get().getItems()) {
                if(c.getCodigo().equals(productId)) {
                    cartItem = Optional.of((c));
                }
            }
            log.info("Producto en el carrito encontrado exitosamente" + LocalDate.now());
            return cartItem;
        } else {
            log.error("No existe carrito para usuario" + userId + LocalDate.now());
            throw new NotFoundException("No existe carrito para el usuario de id " + userId);
        }
    }

    public void addProductToCart(String userId, String productId, CarritoItemRequest item) {
        Optional<CarritoDocument> cart = cartRepository.findByUserId(userId);
        Optional<ProductoDocument> product = Optional.ofNullable(productRepository.findByCodigo(productId));
        if(cart.isPresent() && product.isPresent()) {
            if(checkProductNotExistsOnCart(product.get().getCodigo(), cart.get())) {
                CarritoItem cartItem = CarritoItem.builder()
                        .cantidad(item.getCantidad())
                        .codigo(product.get().getCodigo())
                        .build();

                cart.get().getItems().add(cartItem);
                log.info("Producto agregado correctamente al carrito" + LocalDate.now());
                cartRepository.save(cart.get());
            } else {
                Optional<CarritoItem> cartItem = getCartItemByProductCode(product.get().getCodigo(), cart.get().getItems());
                if(cartItem.isPresent()) {
                    cartItem.get().setCantidad((cartItem.get().getCantidad() + item.getCantidad()));
                    cartRepository.save(cart.get());
                    log.info("Producto actualizado correctamente" + LocalDate.now());
                }
            };
        } else if(cart.isEmpty()) {
            log.error("No existe carrito para usuario" + userId + LocalDate.now());
            throw new NotFoundException("No existe carrito para el usuario de id " + userId);
        } else if(product.isEmpty()) {
            log.error("No existe producto para ese codigo" + productId + LocalDate.now());
            throw new NotFoundException("No existe producto con ese codigo ");
        }
    }

    public void deleteProductOnCart(String userId, String code) {
        Optional<CarritoDocument> cart = cartRepository.findByUserId(userId);
        Optional<ProductoDocument> product = Optional.ofNullable(productRepository.findByCodigo(code));

        if(cart.isPresent() && product.isPresent()) {
            Optional<CarritoItem> cartItem = getCartItemByProductCode(code, cart.get().getItems());
            if (cartItem.isPresent()) {
                List<CarritoItem> items = cart.get()
                        .getItems()
                        .stream()
                        .filter(item -> !item.getCodigo().equals(cartItem.get().getCodigo()))
                        .collect(Collectors.toList());
                cart.get().setItems(items);
                log.info("Producto eliminado correctamente del carrito" + LocalDate.now());
                cartRepository.save(cart.get());
            }
        }
    }

    private boolean checkProductNotExistsOnCart(String code, CarritoDocument cart) {
        boolean exists = true;

        for(CarritoItem c: cart.getItems()) {
            if (c.getCodigo().equals(code)) {
                exists = false;
            }
        }

        return exists;
    }

    private Optional<CarritoItem> getCartItemByProductCode(String code, List<CarritoItem> items) {
        Optional<CarritoItem> exists = Optional.empty();

        for(CarritoItem c : items) {
            if(c.getCodigo().equals(code)) {
                exists = Optional.of(c);
            }
        }

        return exists;
    }
}
