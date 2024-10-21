package com.cib.biblioteca.utils;

import com.cib.biblioteca.entities.Prestamo;
import com.cib.biblioteca.services.IPrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class PrestamoScheluder {

    private final IPrestamoService prestamoService;
    @Autowired
    public PrestamoScheluder(IPrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void actualizarDiasRetraso() {
        List<Prestamo> prestamos = prestamoService.findAll();
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getEstado().getIdEstado() == 1 || prestamo.getEstado().getIdEstado() == 2) {
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaDevolucion = prestamo.getFecDevolucion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                long dias = ChronoUnit.DAYS.between(fechaDevolucion, fechaActual);
                prestamo.setDiasRetraso((int) dias);
                if (prestamo.getDiasRetraso() == 1) {
                    prestamo.getEstado().setIdEstado(2);
                }
                prestamoService.save(prestamo);
            }
        }
    }
}
