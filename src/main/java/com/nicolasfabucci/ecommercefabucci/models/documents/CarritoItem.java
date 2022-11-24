package com.nicolasfabucci.ecommercefabucci.models.documents;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarritoItem {
    private Integer cantidad;
    private Long codigo;
}
