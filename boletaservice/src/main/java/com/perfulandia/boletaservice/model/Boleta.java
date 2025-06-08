package com.perfulandia.boletaservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "boletas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;
    private Date fechaEmision;
    private Double totalNeto;
    private Double iva;
    private Double totalFinal;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleBoleta> detalles;

    public void agregarDetalle(DetalleBoleta detalle) {
        detalle.setBoleta(this);
        this.detalles.add(detalle);
    }
}
