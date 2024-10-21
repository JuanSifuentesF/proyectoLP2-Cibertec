package com.cib.biblioteca.controllers;

import com.cib.biblioteca.entities.Categoria;
import com.cib.biblioteca.entities.Libro;
import com.cib.biblioteca.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class LibroController {
    private final ILibroService libroService;
    private final ICategoriaService categoriaService;
    private final IUploadFileService fileService;
    private final IUsuarioService usuarioService;

    @Autowired
    public LibroController(ILibroService libroService, ICategoriaService categoriaService, UploadFileServiceImpl fileService, IUsuarioService usuarioService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
        this.fileService = fileService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/libros")
    public String libros(Model model) {
        atributosComunes(model);
        List<Libro> libros = libroService.findAll();
        model.addAttribute("libros", libros);
        return "admin/libros";
    }

    @GetMapping("/libros/new")
    public String newLibro(Model model) {
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        atributosComunes(model);
        model.addAttribute("libro", new Libro());
        return "admin/libro";
    }

    @PostMapping("/libros/save")
    public String saveLibro(@Valid Libro libro, BindingResult br, Model model, @RequestParam("img") MultipartFile file) throws IOException {
        List<Categoria> categorias = categoriaService.findAll();
        if (br.hasErrors()) {
            atributosComunes(model);

            model.addAttribute("categorias", categorias);

            return "admin/libro";
        }
        handleImageUpload(libro, file);
        libroService.save(libro);
        return "redirect:/libros";
    }

    @GetMapping("/libros/edit/{id}")
    public String editLibro(@PathVariable Integer id, Model model) {
        Optional<Libro> optionalLibro = libroService.findById(id);
        List<Categoria> categorias = categoriaService.findAll();
        if (optionalLibro.isPresent()) {
            atributosComunes(model);
            model.addAttribute("libro", optionalLibro.get());
            model.addAttribute("categorias", categorias);
            model.addAttribute("tituloLibro", optionalLibro.get().getTitulo());
            return "admin/libro";
        } else {
            return "redirect:/libros";
        }
    }

    @GetMapping("/libros/detail/{id}")
    public String detailLibro(@PathVariable Integer id, Model model) {
        Optional<Libro> optionalLibro = libroService.findById(id);
        if (optionalLibro.get().getCantidad() > 0) {
            model.addAttribute("estado", "Disponible");
        } else {
            model.addAttribute("estado", "Agotado");
        }
        model.addAttribute("libro", optionalLibro.get());
        model.addAttribute("randomLibros", randomLibros());
        atributosComunes(model);
        return "user/ver-libro";
    }

    private List<Libro> randomLibros() {
        List<Libro> libros = libroService.findAll();
        Collections.shuffle(libros);
        return libros.subList(0, Math.min(4, libros.size()));
    }

    @GetMapping("/libros/delete/{id}")
    public String deleteLibro(@PathVariable Integer id, Model model) {
        try {
            libroService.findById(id).ifPresent(this::handleImageDelete);
            libroService.delete(id);
            return "redirect:/libros";
        } catch (Exception e) {
            model.addAttribute("error", "Error al eliminar el libro");
            return "redirect:/libros";
        }
    }

    private void atributoRol(Model model) {
        AuthController.authentication(model, usuarioService);
    }

    private void atributosComunes(Model model) {
        model.addAttribute("title", "Gestión de Préstamos de Libros");
        model.addAttribute("titleLibros", "Gestión de Libros");
        model.addAttribute("titleLibro", "Libro");
        model.addAttribute("titleMasLibros", "Más Libros");
        model.addAttribute("tipo", "libros");

        atributoRol(model);
    }

    private void handleImageUpload(Libro libro, MultipartFile file) throws IOException {
        if (libro.getIdLibro() == null) {
            String nomPortada = fileService.savePortada(file);
            libro.setPortada(nomPortada);
        } else {
            handleImageEdit(libro, file);
        }
    }

    private void handleImageEdit(Libro libro, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            libroService.findById(libro.getIdLibro()).ifPresent(existeLibro -> libro.setPortada(existeLibro.getPortada()));
        } else {
            String nomPortada = fileService.savePortada(file);
            libro.setPortada(nomPortada);

            libroService.findById(libro.getIdLibro()).ifPresent(this::handleImageDelete);
        }
    }

    private void handleImageDelete(Libro libro) {
        if (libro != null && libro.getPortada() != null && !libro.getPortada().equals("default.png")) {
            fileService.deletePortada(libro.getPortada());
        }
    }
}
