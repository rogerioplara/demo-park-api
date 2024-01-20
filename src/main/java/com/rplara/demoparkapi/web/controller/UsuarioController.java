package com.rplara.demoparkapi.web.controller;

import com.rplara.demoparkapi.entity.Usuario;
import com.rplara.demoparkapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// injeção de dependencias via método construtor
@RequiredArgsConstructor
// recursos da api sempre no plural
// anotações para definir que é um controller e o mapeamento da uri
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // encapsula a resposta e guarda demais informações, como código de resposta ou header que deseja enviar
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        Usuario user = usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
