package com.perfulandia.boletaservice.service;

import com.perfulandia.boletaservice.model.Boleta;
import com.perfulandia.boletaservice.repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoletaService {
    @Autowired
    private final BoletaRepository boletaRepo;

    public BoletaService(BoletaRepository boletaRepo) {
        this.boletaRepo = boletaRepo;
    }

    public Boleta crearBoleta(Boleta boleta) {
        double totalNeto = boleta.getDetalles().stream()
                .mapToDouble(d -> d.getCantidad() * d.getPrecioUnitario())
                .sum();

        double iva = totalNeto * 0.19;
        double totalFinal = totalNeto + iva;

        boleta.setFechaEmision(new Date());
        boleta.setTotalNeto(totalNeto);
        boleta.setIva(iva);
        boleta.setTotalFinal(totalFinal);

        boleta.getDetalles().forEach(d -> d.setBoleta(boleta));

        return boletaRepo.save(boleta);
    }

    public List<Boleta> obtenerBoletasPorUsuario(Long idUsuario) {
        return boletaRepo.findByIdUsuario(idUsuario);
    }

    public Optional<Boleta> obtenerBoletaPorId(Long id) {
        return boletaRepo.findById(id);
    }

    public List<Boleta> obtenerTodas() {
        return List.of();
    }
}