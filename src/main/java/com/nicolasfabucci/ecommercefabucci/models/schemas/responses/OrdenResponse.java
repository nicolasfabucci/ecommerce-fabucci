package com.nicolasfabucci.ecommercefabucci.models.schemas.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nicolasfabucci.ecommercefabucci.models.documents.CarritoDocument;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdenResponse {
    private Integer numeroOrden;
    private List<CarritoDocument> productos;
    private LocalDateTime createDate;
    private String estado;
    private String email;

}
