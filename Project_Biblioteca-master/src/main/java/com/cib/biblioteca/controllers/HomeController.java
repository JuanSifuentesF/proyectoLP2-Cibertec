package com.cib.biblioteca.controllers;

import com.cib.biblioteca.entities.Libro;
import com.cib.biblioteca.entities.Prestamo;
import com.cib.biblioteca.entities.Usuario;
import com.cib.biblioteca.services.ILibroService;
import com.cib.biblioteca.services.IPrestamoService;
import com.cib.biblioteca.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    private final ILibroService libroService;
    private final IPrestamoService prestamoService;
    private final IUsuarioService usuarioService;

    @Autowired
    public HomeController(ILibroService libroService, IPrestamoService prestamoService, IUsuarioService usuarioService) {
        this.libroService = libroService;
        this.prestamoService = prestamoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public String home(Model model) {
        List<Libro> libros = libroService.findAll();
        model.addAttribute("libros", libros);

        atributoRol(model);
        atributosComunes(model);
        return "/home";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam String buscar, @RequestParam String tipo, Model model) {

        atributoRol(model);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Obtener Id del Usuario
        Optional<Usuario> optionalUsuario = usuarioService.findByEmail(email);
        model.addAttribute("idUsuario", optionalUsuario.get().getIdUsuario());

        if (tipo.equals("home")) {
            List<Libro> libros = libroService.buscar(buscar);
            model.addAttribute("libros", libros);
            atributosComunes(model);
            model.addAttribute("tipo", "home");
            return "/home";
        }
        if (tipo.equals("prestamos")) {
            List<Prestamo> prestamos = prestamoService.buscar(buscar);
            model.addAttribute("prestamos", prestamos);
            atributosComunes(model);
            model.addAttribute("tipo", "prestamos");
            return "admin/prestamos";
        }
        if (tipo.equals("libros")){
            List<Libro> libros = libroService.buscar(buscar);
            model.addAttribute("libros", libros);
            atributosComunes(model);
            model.addAttribute("tipo", "libros");
            return "admin/libros";
        }
        if (tipo.equals("misPrestamos")){
            List<Prestamo> prestamos = prestamoService.buscarPorUsuario(buscar, optionalUsuario.get().getIdUsuario());
            model.addAttribute("prestamosId", prestamos);

            if (!prestamos.isEmpty()) {
                for (Prestamo prestamo : prestamos) {
                    if (prestamo.getDiasRetraso() > 0) {
                        double i = prestamo.getDiasRetraso() * 2;
                        model.addAttribute("montoPago", i);
                    }
                }
            }

            atributosComunes(model);
            model.addAttribute("tipo", "prestamos");
            return "user/prestamos";
        }
        return "redirect:/";
    }

    private void atributoRol(Model model) {
        AuthController.authentication(model, usuarioService);
    }

    private void atributosComunes(Model model) {
        model.addAttribute("title", "Gestión de Préstamos de Libros");
        model.addAttribute("tipo", "home");

        model.addAttribute("titlePrestamos", "Gestión de Préstamos");

        model.addAttribute("titleLibros", "Gestión de Libros");

        model.addAttribute("titleMisPrestamos", "Mis Préstamos");
    }
}
