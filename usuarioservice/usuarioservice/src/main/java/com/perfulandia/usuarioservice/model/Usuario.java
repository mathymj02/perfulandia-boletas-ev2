package com.perfulandia.usuarioservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String correo;
    private String rol; // ADMIN, GERENTE, Usuario
}

