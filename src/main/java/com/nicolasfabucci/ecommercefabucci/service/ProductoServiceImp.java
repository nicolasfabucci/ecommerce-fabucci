package com.nicolasfabucci.ecommercefabucci.service;

import com.nicolasfabucci.ecommercefabucci.handler.ApiRestException;
import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoDocument;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.ProductoRequest;
import com.nicolasfabucci.ecommercefabucci.repositories.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServiceImp implements ProductoService{

    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public ProductoDocument create(ProductoRequest request) throws ApiRestException {
        final ProductoDocument producto = new ProductoDocument();

        producto.setCodigo(request.getCodigo());
        producto.setCategoria(request.getCategoria());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());

        productoRepository.save(producto);

        return producto;
    }

    @Override
    @Transactional
    public ProductoDocument update(String codigo, ProductoRequest request) throws ApiRestException {
        return null;
    }

    @Override
    public List<ProductoDocument> getAll() {
        return null;
    }

    @Override
    public ProductoDocument getByCodigo(String codigo) throws ApiRestException {
        return null;
    }

    @Override
    public List<ProductoDocument> getByCategoria(String categoria) throws ApiRestException {
        return null;
    }

    @Override
    public void delete(String codigo) throws ApiRestException {

    }
}
