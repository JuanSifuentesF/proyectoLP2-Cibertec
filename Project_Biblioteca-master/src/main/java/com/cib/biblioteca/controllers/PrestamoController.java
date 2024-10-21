package com.cib.biblioteca.controllers;

import com.cib.biblioteca.entities.Estado;
import com.cib.biblioteca.entities.Libro;
import com.cib.biblioteca.entities.Prestamo;
import com.cib.biblioteca.entities.Usuario;
import com.cib.biblioteca.services.IEstadoService;
import com.cib.biblioteca.services.ILibroService;
import com.cib.biblioteca.services.IPrestamoService;
import com.cib.biblioteca.services.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PrestamoController {
    private final IPrestamoService prestamoService;
    private final ILibroService libroService;
    private final IUsuarioService usuarioService;
    private final IEstadoService estadoService;

    @Autowired
    public PrestamoController(IPrestamoService prestamoService, ILibroService libroService, IUsuarioService usuarioService, IEstadoService estadoService) {
        this.prestamoService = prestamoService;
        this.libroService = libroService;
        this.usuarioService = usuarioService;
        this.estadoService = estadoService;
    }

    @GetMapping("/prestamos")
    public String prestamos(Model model) {
        atributosComunes(model);
        List<Prestamo> prestamos = prestamoService.findAll();
        model.addAttribute("prestamos", prestamos);
        return "admin/prestamos";
    }

    @GetMapping("/prestamos/new")
    public String newPrestamo(Model model) {
        atributosListado(model);
        model.addAttribute("prestamo", new Prestamo());
        return "admin/prestamo";
    }

    @PostMapping("/prestamos/save")
    public String savePrestamo(@Valid Prestamo prestamo, BindingResult br, Model model) {
        atributosListado(model);

        if (br.hasErrors()) {
            atributosListado(model);
            return "admin/prestamo";
        }


        setDefaultPrestamoValues(prestamo);
        updateLibroAndSavePrestamo(prestamo);
        return "redirect:/prestamos";
    }

    @GetMapping("/prestamos/edit/{id}")
    public String editPrestamo(@PathVariable Integer id, Model model) {
        Optional<Prestamo> optionalPrestamo = prestamoService.findById(id);
        if (optionalPrestamo.isPresent()) {
            atributosEdit(model, optionalPrestamo.get());
            return "admin/prestamo";
        } else {
            return "redirect:/prestamos";
        }
    }

    @PostMapping("/prestamos/update")
    public String updatePrestamo(@RequestParam("idPrestamo") Integer idPrestamo,
                                 @RequestParam("estado") Integer idEstado) {
        Optional<Prestamo> optionalPrestamo = prestamoService.findById(idPrestamo);
        if (optionalPrestamo.isPresent()) {
            updateEstadoPrestamo(idEstado, optionalPrestamo.get());
        }
        return "redirect:/prestamos";
    }

    @GetMapping("/prestamos/{id}") // Mis prestamos
    public String userPrestamos(@PathVariable Integer id, Model model) {
        atributosComunes(model);
        List<Prestamo> prestamosId = prestamoService.findByUsuario(id);
        AuthController.authentication(model, usuarioService);
        model.addAttribute("prestamosId", prestamosId);
        atributosComunes(model);
        return "user/prestamos";
    }

    private void atributosListado(Model model) {
        List<Libro> libros = libroService.findAll();
        List<Usuario> usuarios = usuarioService.findAll();
        List<Estado> estados = estadoService.findAll();

        model.addAttribute("libros", libros);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("estados", estados);
        atributosComunes(model);
    }

    private void setDefaultPrestamoValues(Prestamo prestamo) {
        if (prestamo.getFecPrestamo() == null) {
            prestamo.setFecPrestamo(new Date());
        }
        if (prestamo.getEstado() == null) {
            Optional<Estado> optionalEstado = estadoService.findById(1);
            prestamo.setEstado(optionalEstado.get());
        }
        if (prestamo.getDiasRetraso() == 0) {
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaDevolucion = prestamo.getFecDevolucion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long dias = ChronoUnit.DAYS.between(fechaDevolucion, fechaActual);
            prestamo.setDiasRetraso((int) dias);
        }
        if (prestamo.getEstado().getIdEstado() == 3){
            prestamo.setDiasRetraso(0);
        }
    }

    private void updateLibroAndSavePrestamo(Prestamo prestamo) {
        Libro libro = prestamo.getLibro();
        if (prestamo.getEstado().getIdEstado() == 3){
            libro.setCantidad(libro.getCantidad() + 1);
        } else{
            libro.setCantidad(libro.getCantidad() - 1);
        }

        libroService.save(libro);
        prestamoService.save(prestamo);
    }

    private void atributosEdit(Model model, Prestamo prestamo) {
        atributosComunes(model);
        model.addAttribute("prestamo", prestamo);
        model.addAttribute("modo", "actualizar");

        atributosListado(model);

        if (prestamo.getDiasRetraso() > 0) {
            double i = prestamo.getDiasRetraso() * 2;
            model.addAttribute("montoPago", i);
        }
    }

    private void updateEstadoPrestamo(Integer idEstado, Prestamo existingPrestamo) {
        Optional<Estado> optionalEstado = estadoService.findById(idEstado);
        if (optionalEstado.isPresent()) {
            Estado estado = optionalEstado.get();
            existingPrestamo.setEstado(estado);
            Libro libro = existingPrestamo.getLibro();
            libro.setCantidad(libro.getCantidad() + 1);
            prestamoService.save(existingPrestamo);
            libroService.save(libro);
        }
    }

    private void atributoRol(Model model) {
        AuthController.authentication(model, usuarioService);
    }

    public void atributosComunes(Model model) {
        model.addAttribute("title", "Gestión de Préstamos de Libros");
        model.addAttribute("titlePrestamos", "Gestión de Préstamos");
        model.addAttribute("titlePrestamo", "Préstamo");
        model.addAttribute("tipo", "prestamos");
        model.addAttribute("titleMisPrestamos", "Mis Préstamos");

        atributoRol(model);
    }

}
