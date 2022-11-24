package com.nicolasfabucci.ecommercefabucci.service;

import com.nicolasfabucci.ecommercefabucci.handler.ApiRestException;
import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoDocument;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.ProductoRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.responses.ProductoResponse;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public ProductoResponse create(ProductoRequest request) throws ApiRestException;
    public ProductoResponse update(Long codigo,ProductoRequest request) throws ApiRestException;
    public List<ProductoDocument> getAll();
    public Optional<ProductoResponse> getByCodigo(Long codigo) throws ApiRestException;
    public List<ProductoResponse> getByCategoria(String categoria) throws ApiRestException;
    public void delete(Long codigo) throws ApiRestException;
}
