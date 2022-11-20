package com.nicolasfabucci.ecommercefabucci.service;

import com.nicolasfabucci.ecommercefabucci.handler.ApiRestException;
import com.nicolasfabucci.ecommercefabucci.models.documents.CarritoDocument;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.OrdenRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.responses.OrdenResponse;
import lombok.Builder;

import java.util.List;

public interface OrdenService {
    public OrdenResponse createOrden(OrdenRequest ordenRequest) throws ApiRestException;
    public List<OrdenResponse> getAllOrdenes();
    public OrdenResponse updateOrden(Integer orderNumber, CarritoDocument carrito) throws ApiRestException;
    public void deleteOrden(Integer orderNumber) throws ApiRestException;
    public void deleteCarrito(Integer orderNumber,String code) throws ApiRestException;
    public OrdenResponse addCarrito(Integer numeroOrden,CarritoDocument cartItem) throws ApiRestException;
    public List<CarritoDocument> getAllCartItems(Integer numeroOrden) throws ApiRestException;
    public List<CarritoDocument> generateOrder(Integer numeroOrden) throws ApiRestException;
}
