package com.example.demo.service;

import com.example.demo.entities.Usuario;
import com.example.demo.entities.UsuarioRequestDto;
import com.example.demo.entities.UsuarioResponseDto;
import com.example.demo.repository.usuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final usuarioRepository usuarioRepository;

    // CREATE
    public UsuarioResponseDto cadastroUsuario(UsuarioRequestDto request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.email());
        usuario.setNome(request.nome());
        usuarioRepository.save(usuario);

        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    // READ - todos
    public List<UsuarioResponseDto> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(user -> new UsuarioResponseDto(user.getId(), user.getNome(), user.getEmail()))
                .toList();
    }

    // READ - por ID
    public UsuarioResponseDto buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(user -> new UsuarioResponseDto(user.getId(), user.getNome(), user.getEmail()))
                .orElse(null);
    }

    // UPDATE
    public UsuarioResponseDto atualizarUsuario(Long id, UsuarioRequestDto request) {
        return usuarioRepository.findById(id)
                .map(user -> {
                    user.setNome(request.nome());
                    user.setEmail(request.email());
                    usuarioRepository.save(user);
                    return new UsuarioResponseDto(user.getId(), user.getNome(), user.getEmail());
                })
                .orElse(null);
    }

    // DELETE
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
