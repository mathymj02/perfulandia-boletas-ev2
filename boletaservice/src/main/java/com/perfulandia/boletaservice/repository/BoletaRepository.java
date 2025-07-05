package com.perfulandia.boletaservice.repository;

import com.perfulandia.boletaservice.model.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repositorio para acceder a boletas desde la base de datos
public interface BoletaRepository extends JpaRepository<Boleta, Long> {
    List<Boleta> findByIdUsuario(Long idUsuario);
}
