package com.perfulandia.boletaservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

// Clase que representa una boleta emitida a un usuario
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario; // ID del usuario
    private Date fechaEmision;
    private double totalNeto;
    private double iva;
    private double totalFinal;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL)
    private List<DetalleBoleta> detalles; // Lista de productos en la boleta
}

