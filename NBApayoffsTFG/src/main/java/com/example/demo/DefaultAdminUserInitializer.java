package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import com.example.demo.Repository.UsuarioRep;
import com.example.demo.Service.UsuarioService;
import com.example.demo.model.Usuario;

@Component
public class DefaultAdminUserInitializer implements CommandLineRunner {

	@Autowired
    private  UsuarioService usuarioService;


    @Override
    public void run(String... args) throws Exception {
        // Verificar si ya existe un usuario administrador
        if (usuarioService.findByNombre("admin") == null) {
            // Crear un nuevo usuario administrador
            Usuario adminUser = new Usuario();
            adminUser.setNombre("admin");
            adminUser.setApellido("a");
            adminUser.setEmail("lopezcarojoselu@gmail.com");
            adminUser.setcontraseña("MiamiH22"); // Aquí deberías usar un password seguro
            adminUser.setRol(Usuario.Rol.ADMIN);

            // Guardar el usuario en la base de datos
            usuarioService.save(adminUser);

            System.out.println("Usuario administrador por defecto creado.");
        } else {
            System.out.println("Ya existe un usuario administrador en la base de datos.");
        }
    }
}
