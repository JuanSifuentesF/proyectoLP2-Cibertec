package com.cib.biblioteca.controllers;

import com.cib.biblioteca.entities.Usuario;
import com.cib.biblioteca.services.IUsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Optional;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("titleLogin", "Inicio de Sesión");
        return "/login";
    }
    @GetMapping("/logout")
    public String logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login";
    }
    static void authentication(Model model, IUsuarioService usuarioService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        for (GrantedAuthority role : roles) {
            model.addAttribute("rol", role.getAuthority());
        }
        String email = authentication.getName();
        Optional<Usuario> optionalUsuario = usuarioService.findByEmail(email);
        model.addAttribute("idUsuario", optionalUsuario.get().getIdUsuario());
        model.addAttribute("nomUsuario", optionalUsuario.get().getNombre());
        model.addAttribute("nomApeUsuario", optionalUsuario.get().getNombre());
    }
}
