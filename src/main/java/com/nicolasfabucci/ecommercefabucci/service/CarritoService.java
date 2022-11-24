package com.nicolasfabucci.ecommercefabucci.service;

import com.nicolasfabucci.ecommercefabucci.models.documents.CarritoDocument;
import com.nicolasfabucci.ecommercefabucci.models.documents.CarritoItem;
import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoItem;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.CarritoItemRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.CarritoRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.responses.CarritoResponse;

import java.util.List;
import java.util.Optional;

public interface CarritoService {

    public List<CarritoDocument> getCarts();

    public CarritoResponse createCart(CarritoRequest carrito);

    CarritoResponse getCartByUser(String userId);

    public void deleteCart(String cartId);

    public List<CarritoItem> getCartProducts(String userId);

    public void addProductToCart(String userId, Long productId, CarritoItemRequest item);

    public void deleteProductOnCart(String cartId, Long code);

}
