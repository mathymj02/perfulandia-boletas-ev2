package com.perfulandia.boletaservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalles_boleta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleBoleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producto;
    private Integer cantidad;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "boleta_id")
    private Boleta boleta;
}
