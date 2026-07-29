package com.vitorlourencoc.projeto_crud_spring.controller;

import com.vitorlourencoc.projeto_crud_spring.business.UsuarioService;
import com.vitorlourencoc.projeto_crud_spring.infrastructure.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario){
        usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "email")
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @GetMapping(params = "telefone")
    public ResponseEntity<Usuario> buscarUsuarioPorTelefone(@RequestParam String telefone){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorTelefone(telefone));
    }

    @DeleteMapping(params = "email")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam String email){
        usuarioService.deletarUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(params = "telefone")
    public ResponseEntity<Void> deletarUsuarioPorTelefone(@RequestParam String telefone){
        usuarioService.deletarUsuarioPorTelefone(telefone);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarUsuarioPorId(@RequestParam Integer id, @RequestBody Usuario usuario){
        usuarioService.atualizarUsuarioPorId(id, usuario);
        return ResponseEntity.ok().build();
    }




}
