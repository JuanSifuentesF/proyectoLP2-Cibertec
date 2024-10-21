package com.cib.biblioteca.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Integer idUsuario;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    @Column(name = "username")
    private String userName;
    private String psw;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol")
    private Rol rol;
}