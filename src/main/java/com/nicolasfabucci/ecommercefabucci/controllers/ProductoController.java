package com.nicolasfabucci.ecommercefabucci.controllers;

import com.nicolasfabucci.ecommercefabucci.handler.ApiRestException;
import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoDocument;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.ProductoRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.responses.ProductoResponse;
import com.nicolasfabucci.ecommercefabucci.service.ProductoService;
import com.nicolasfabucci.ecommercefabucci.service.ProductoServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoServiceImp productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDocument>> getAll() {
        List<ProductoDocument> products = productoService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> getByCode(@PathVariable Long codigo) throws ApiRestException {
        Optional<ProductoResponse> product = productoService.getByCodigo(codigo);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value="/categoria")
    public ResponseEntity<List<ProductoResponse>> getProductsByCategory(
            @RequestParam(name="categoria", defaultValue = "") String categoria) throws ApiRestException {
        List<ProductoResponse> products = productoService.getByCategoria(categoria);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> create(@Valid @RequestBody ProductoRequest producto) throws ApiRestException {
        ProductoResponse productoResponse = productoService.create(producto);
        return ResponseEntity.created(URI.create("/productos")).body(productoResponse);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ProductoResponse> update(
            @PathVariable Long codigo, @RequestBody ProductoRequest request) throws ApiRestException {
        ProductoResponse productoResponse = productoService.update(codigo, request);
        return ResponseEntity.ok(productoResponse);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<ProductoDocument>delete(@PathVariable Long codigo) throws ApiRestException {
        productoService.delete(codigo);
        return ResponseEntity.noContent().build();
    }


}