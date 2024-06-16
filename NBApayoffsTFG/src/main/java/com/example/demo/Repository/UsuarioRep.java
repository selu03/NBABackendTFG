package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Usuario;
@Repository
public interface UsuarioRep extends JpaRepository<Usuario, Long	> {
	Optional<Usuario> findByEmailAndContraseña(String email, String contraseña);
	Usuario findByEmail(String email);
    Usuario findByNombre(String nombre);
}