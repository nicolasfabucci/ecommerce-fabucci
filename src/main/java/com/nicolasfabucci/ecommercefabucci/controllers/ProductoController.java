package com.nicolasfabucci.ecommercefabucci.controllers;

import com.nicolasfabucci.ecommercefabucci.models.documents.ProductoDocument;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.ProductoRequest;
import com.nicolasfabucci.ecommercefabucci.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductoController {
  /*  @Autowired
    private final ProductoService productoService;

    @GetMapping(value = "producto/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getProductoById(@PathVariable(name = "id") ObjectId id) {
        final Optional<ProductoDocument> producto = productoService.getProductoById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "producto", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> guardarProducto(@RequestBody ProductoRequest productoRequest) {
        try {
            final ProductoDocument productoDocumentGuardado = productoService.postNewProducto(productoRequest);
            return ResponseEntity.created(URI.create("")).body(productoDocumentGuardado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }*/


}