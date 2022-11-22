package com.nicolasfabucci.ecommercefabucci.controllers;

import com.nicolasfabucci.ecommercefabucci.handler.ApiRestException;
import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoDocument;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.ProductoRequest;
import com.nicolasfabucci.ecommercefabucci.service.ProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public List<ProductoDocument> getAll() {
        return productoService.getAll();
    }

    @GetMapping("/{codigo}")
    public ProductoDocument getByCode(@PathVariable String codigo) throws ApiRestException {
        return productoService.getByCodigo(codigo);
    }

    @GetMapping("categoria/{categoria}")
    public List<ProductoDocument> getByCategory(@RequestParam String categoria) throws ApiRestException {
        return productoService.getByCategoria(categoria);
    }

    @PostMapping
    public ProductoDocument create(@Validated @RequestBody ProductoRequest request) throws ApiRestException {
        return productoService.create(request);
    }

    @PutMapping("/{codigo}")
    public ProductoDocument update(@PathVariable String codigo, @RequestBody ProductoRequest request) throws ApiRestException {
        return productoService.update(codigo,request);
    }

    @DeleteMapping("/{codigo}")
    public void delete(@PathVariable String codigo) throws ApiRestException {
        productoService.delete(codigo);
    }


}