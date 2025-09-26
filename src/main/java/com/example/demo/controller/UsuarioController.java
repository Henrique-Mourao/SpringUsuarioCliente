package com.example.demo.controller;

import com.example.demo.entities.UsuarioRequestDto;
import com.example.demo.entities.UsuarioResponseDto;
import com.example.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // CREATE
    @PostMapping
    public UsuarioResponseDto criar(@RequestBody UsuarioRequestDto request) {
        return usuarioService.cadastroUsuario(request);
    }

    // READ - todos
    @GetMapping
    public List<UsuarioResponseDto> listar() {
        return usuarioService.listarUsuarios();
    }

    // READ - por ID
    @GetMapping("/{id}")
    public UsuarioResponseDto buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public UsuarioResponseDto atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDto request) {
        return usuarioService.atualizarUsuario(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }
}
