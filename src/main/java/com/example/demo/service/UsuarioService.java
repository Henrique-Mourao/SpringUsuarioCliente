package com.example.demo.service;

import com.example.demo.entities.Usuario;
import com.example.demo.entities.UsuarioRequestDto;
import com.example.demo.entities.UsuarioResponseDto;
import com.example.demo.repository.usuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor


public class UsuarioService {
    private final usuarioRepository usuarioRepository;

    public UsuarioResponseDto cadastroUsuario(UsuarioRequestDto resquest){
        Usuario usuario = new Usuario();
        usuario.setEmail(resquest.email());
        usuario.setNome(resquest.nome());
        usuarioRepository.save(usuario);

        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );

    }


}

