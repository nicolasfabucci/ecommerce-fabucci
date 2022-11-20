package com.nicolasfabucci.ecommercefabucci.service;

import com.nicolasfabucci.ecommercefabucci.handler.ApiRestException;
import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoDocument;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.ProductoRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.responses.ProductoResponse;

import java.util.List;

public interface ProductoService {
    public ProductoDocument create(ProductoRequest request) throws ApiRestException;
    public ProductoDocument update(String codigo,ProductoRequest request) throws ApiRestException;
    public List<ProductoDocument> getAll();
    public ProductoDocument getByCodigo(String codigo) throws ApiRestException;
    public List<ProductoDocument> getByCategoria(String categoria) throws ApiRestException;
    public void delete(String codigo) throws ApiRestException;
}
