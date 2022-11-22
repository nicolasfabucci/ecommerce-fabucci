package com.nicolasfabucci.ecommercefabucci.service;

import com.nicolasfabucci.ecommercefabucci.handler.ApiRestException;
import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoDocument;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.ProductoRequest;
import com.nicolasfabucci.ecommercefabucci.repositories.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductoServiceImp implements ProductoService{

    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public ProductoDocument create(ProductoRequest request) throws ApiRestException {
        validateRequestCreate(request);
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
        validateRequestExists(codigo);
        ProductoDocument producto = productoRepository.findByCodigo(codigo);
        producto.setCodigo(request.getCodigo());
        producto.setCategoria(request.getCategoria());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());

        var entitySaved = productoRepository.save(producto);

        return producto;
    }

    @Override
    public List<ProductoDocument> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public ProductoDocument getByCodigo(String codigo) throws ApiRestException {
        validateRequestExists(codigo);
        return productoRepository.findByCodigo(codigo);
    }

    @Override
    public List<ProductoDocument> getByCategoria(String categoria) throws ApiRestException {
        if (productoRepository.findByCategoria(categoria).isEmpty()) {
            throw new ApiRestException(categoria, "La categoria no existe");
        }
        return productoRepository.findByCategoria(categoria);
    }

    @Override
    public void delete(String codigo) throws ApiRestException {
        validateRequestExists(codigo);
        ProductoDocument document = productoRepository.findByCodigo(codigo);
        productoRepository.delete(document);
    }

    private void validateRequestCreate(ProductoRequest request) throws ApiRestException {
        var product = productoRepository.findByCodigo(request.getCodigo());
        if (!Objects.isNull(product)) {
            throw new ApiRestException(request.getCodigo(), "El producto ya existe");
        }
    }

    private void validateRequestExists(String code) throws ApiRestException {
        var product = productoRepository.findByCodigo(code);
        if (Objects.isNull(product)) {
            throw new ApiRestException(code, "El producto no existe");
        }
    }
}
