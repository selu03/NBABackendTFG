package com.example.demo.Controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.UsuarioRep;
import com.example.demo.Service.UsuarioService;
import com.example.demo.model.Login;
import com.example.demo.model.UpdatePasswordRequest;
import com.example.demo.model.Usuario;

@RestController
@RequestMapping("/usuario/")
public class UsuarioRest {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioRep usuarioRep;

    @GetMapping
    private ResponseEntity<List<Usuario>> getAllUsuario() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping
    private ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        usuario.setRol(Usuario.Rol.USER); // Aquí estableces el rol como USER
        try {
            Usuario usuarioGuardada = usuarioService.save(usuario);
            return ResponseEntity.created(new URI("/usuarios/" + usuarioGuardada.getId())).body(usuarioGuardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Login iniciasesion) {
        boolean isAuthenticated = usuarioService.authenticate(iniciasesion.getEmail(), iniciasesion.getcontraseña());
        Map<String, String> response = new HashMap<>();
        if (isAuthenticated) {
            response.put("message", "Login exitoso");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuarioOptional = usuarioRep.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setcontraseña(null);
            // Añade cualquier otro campo que quieras actualizar

            Usuario updatedUsuario = usuarioService.save(usuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRep.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{email}/contrasena")
    public ResponseEntity<?> updatePassword(@PathVariable String email, @RequestBody UpdatePasswordRequest request) {
        boolean isUpdated = usuarioService.updatePassword(email, request.getNewPassword());
        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }
}
