package com.nicolasfabucci.ecommercefabucci.service;

import com.nicolasfabucci.ecommercefabucci.handler.NotFoundException;
import com.nicolasfabucci.ecommercefabucci.models.documents.*;
import com.nicolasfabucci.ecommercefabucci.models.enums.StateEnum;
import com.nicolasfabucci.ecommercefabucci.models.schemas.requests.OrdenRequest;
import com.nicolasfabucci.ecommercefabucci.models.schemas.responses.OrdenResponse;
import com.nicolasfabucci.ecommercefabucci.repositories.CarritoRepository;
import com.nicolasfabucci.ecommercefabucci.repositories.OrdenRepository;
import com.nicolasfabucci.ecommercefabucci.repositories.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OrdenService {

    private final OrdenRepository ordenRepository;

    private final CarritoRepository carritoRepository;

    private final ProductoRepository productoRepository;
    private final UserDetailsServiceImpl userService;
    private final SequenceGenerator sequenceGenerator;

    public Optional<OrdenResponse> getOrderById(Long orderId) {

        Optional<OrdenDocument> order = ordenRepository.findByOrderNumber(orderId);
        if(order.isPresent()) {
            log.info("Orden encontrada");
            return Optional.of(OrdenResponse.builder()
                    .orderNumber(order.get().getOrderNumber())
                    .email(order.get().getEmail())
                    .items(order.get().getItems())
                    .total(order.get().getTotal())
                    .build());
        } else {
            log.error("La orden de compra no fue encontrada" + LocalDate.now());
            throw new NotFoundException("La orden de compra no fue encontrada");
        }
    }

    public void deleteOrder(Long orderId) {
        Optional<OrdenDocument> order = ordenRepository.findByOrderNumber(orderId);
        if(order.isPresent()) {
            log.info("La orden de compra fue encontrada");
            ordenRepository.deleteById(orderId);
        } else {
            log.error("La orden de compra no fue encontrada");
            throw new NotFoundException("La orden de compra no fue encontrada");
        }
    }

    public OrdenResponse createOrder(OrdenRequest orderRequest) {
        Optional<CarritoDocument> cart = carritoRepository.findByUserId(orderRequest.getUserId());
        Optional<UsuarioDocument> user = userService.getUserById(orderRequest.getUserId());

        if(cart.isPresent() && user.isPresent()) {
            OrdenDocument order = OrdenDocument.builder()
                    .orderNumber(sequenceGenerator.generateSequence(OrdenDocument.SEQUENCE_NAME))
                    .userId(orderRequest.getUserId())
                    .estado(StateEnum.GENERADA)
                    .items(cart.get().getItems())
                    .total(getTotalPrice(cart.get().getItems()))
                    .build();

            ordenRepository.save(order);
            log.info("La orden de compra fue encontrada");
            return OrdenResponse.builder()
                    .orderNumber(order.getOrderNumber())
                    .email(order.getEmail())
                    .items(order.getItems())
                    .total(order.getTotal())
                    .build();
        } else {
            log.error("La orden de compra no fue encontrada");
            throw new NotFoundException("No existe un carrito para ese usuario");
        }
    }
    private Double getTotalPrice(List<CarritoItem> items) {
        Double total = 0.0;

        for(CarritoItem c : items) {
            Optional<ProductoDocument> product = productoRepository.findById(c.getCodigo());
            if (product.isPresent()) {
                total = total + (product.get().getPrecio() * c.getCantidad().doubleValue());
            }
        }

        return total;
    }
}
