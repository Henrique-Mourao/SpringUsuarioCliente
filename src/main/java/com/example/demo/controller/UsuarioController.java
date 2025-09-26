package com.example.demo.controller;

import com.example.demo.entities.UsuarioRequestDto;
import com.example.demo.entities.UsuarioResponseDto;
import com.example.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor

public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastroUsuario (@RequestBody UsuarioRequestDto Request){
        UsuarioResponseDto response=usuarioService.cadastroUsuario(Request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
//entities _ dto _ repository _ service _ controller
