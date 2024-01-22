package com.rplara.demoparkapi.web.controller;

import com.rplara.demoparkapi.entity.Usuario;
import com.rplara.demoparkapi.service.UsuarioService;
import com.rplara.demoparkapi.web.dto.UsuarioCreateDto;
import com.rplara.demoparkapi.web.dto.UsuarioResponseDto;
import com.rplara.demoparkapi.web.dto.UsuarioSenhaDto;
import com.rplara.demoparkapi.web.dto.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// injeção de dependencias via método construtor
@RequiredArgsConstructor
// recursos da api sempre no plural
// anotações para definir que é um controller e o mapeamento da uri
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // encapsula a resposta e guarda demais informações, como código de resposta ou header que deseja enviar
    // O DTO altera o retorno dos controllers
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreateDto createDto){
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id){
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    // Retorno void não gera nenhum dado de resposta, porém gera o código de status
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UsuarioSenhaDto dto){
        Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        // corresponde ao status 204
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll(){
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        String user = usuarioService.deletar(id);
        return ResponseEntity.ok(user);
    }
}
