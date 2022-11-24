package com.nicolasfabucci.ecommercefabucci.service;

import com.nicolasfabucci.ecommercefabucci.handler.ApiRestException;
import com.nicolasfabucci.ecommercefabucci.handler.NotFoundException;
import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoDocument;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.ProductoRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.responses.ProductoResponse;
import com.nicolasfabucci.ecommercefabucci.repositories.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductoServiceImp implements ProductoService{

    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public ProductoResponse create(ProductoRequest request) throws ApiRestException {
        ProductoDocument producto = ProductoDocument.builder()
                .codigo(request.getCodigo())
                .categoria(request.getCategoria())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .build();
        productoRepository.save(producto);

        log.info("El producto fue creado!");

        ProductoResponse productoResponse = ProductoResponse.builder()
                .codigo(producto.getCodigo())
                .precio(producto.getPrecio())
                .descripcion(producto.getDescripcion())
                .categoria(producto.getCategoria())
                .build();
        return productoResponse;
    }

    @Override
    @Transactional
    public ProductoResponse update(Long codigo, ProductoRequest request) throws ApiRestException {
        Optional<ProductoDocument> producto = Optional.ofNullable(productoRepository.findByCodigo(codigo));
        if(producto.isPresent()) {
            producto.get().setPrecio(request.getPrecio());
            producto.get().setDescripcion(request.getDescripcion());
            producto.get().setCategoria(request.getCategoria());
            productoRepository.save(producto.get());

            log.info("El producto fue actualizado exitosamente");

            ProductoResponse productResponse = ProductoResponse.builder()
                    .precio(request.getPrecio())
                    .descripcion(request.getDescripcion())
                    .codigo(producto.get().getCodigo())
                    .categoria(request.getCategoria())
                    .build();
            return productResponse;
        } else {
            log.error("El producto no fue encontrado" );
            throw new NotFoundException("No existe producto con código " + codigo);
        }
    }

    @Override
    public List<ProductoDocument> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<ProductoResponse> getByCodigo(Long codigo) throws ApiRestException {
        //validateRequestExists(codigo);
        //return productoRepository.findByCodigo(codigo);
        Optional<ProductoDocument> product = Optional.ofNullable(productoRepository.findByCodigo(codigo));
        if(product.isPresent()) {
            log.info("El producto fue encontrado exitosamente");
            return Optional.of(ProductoResponse.builder()
                    .precio(product.get().getPrecio())
                    .descripcion(product.get().getDescripcion())
                    .categoria(product.get().getCategoria())
                    .codigo(product.get().getCodigo())
                    .build());
        } else {
            log.error("No existe producto con codigo" + codigo);
            throw new NotFoundException("No existe producto con código " + codigo);
        }
    }

    @Override
    public List<ProductoResponse> getByCategoria(String categoria) throws ApiRestException {
        List<ProductoDocument> productos = productoRepository.findByCategoria(categoria);
        return productos
                .stream()
                .map(producto ->
                        ProductoResponse
                                .builder()
                                .codigo(producto.getCodigo())
                                .categoria(producto.getCategoria())
                                .descripcion(producto.getDescripcion())
                                .precio(producto.getPrecio())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long codigo) throws ApiRestException {
        Optional<ProductoDocument> producto = Optional.ofNullable(productoRepository.findByCodigo(codigo));
        if(producto.isPresent()) {
            productoRepository.deleteById(producto.get().getId());
        } else {
            log.error("El producto no fue encontrado " + codigo);
            throw new NotFoundException("No existe producto " + codigo);
        }

    }

}
