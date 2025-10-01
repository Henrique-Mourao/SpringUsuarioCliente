package com.example.demo.service;

import com.example.demo.entities.Usuario;
import com.example.demo.entities.UsuarioRequestDto;
import com.example.demo.entities.UsuarioResponseDto;
import com.example.demo.repository.usuarioRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final usuarioRepository usuarioRepository;

    public UsuarioResponseDto cadastroUsuario(UsuarioRequestDto request) {
        // Validação de dados
        if (request.email() == null || request.email().isBlank()) {
            throw new BadRequestException("Email é obrigatório");
        }

        if (request.nome() == null || request.nome().isBlank()) {
            throw new BadRequestException("Nome é obrigatório");
        }

        // Verifica se email já existe
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new ConflictException("Email já cadastrado: " + request.email());
        }

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

    public List<UsuarioResponseDto> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(user -> new UsuarioResponseDto(
                        user.getId(),
                        user.getNome(),
                        user.getEmail()))
                .toList();
    }

    public UsuarioResponseDto buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(user -> new UsuarioResponseDto(
                        user.getId(),
                        user.getNome(),
                        user.getEmail()))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuário não encontrado com ID: " + id));
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuário não encontrado com ID: " + id));
    }

    public UsuarioResponseDto atualizarUsuario(Long id, UsuarioRequestDto request) {
        // Validações
        if (request.email() == null || request.email().isBlank()) {
            throw new BadRequestException("Email é obrigatório");
        }

        if (request.nome() == null || request.nome().isBlank()) {
            throw new BadRequestException("Nome é obrigatório");
        }

        return usuarioRepository.findById(id)
                .map(user -> {
                    // Verifica se o novo email já existe em outro usuário
                    if (!user.getEmail().equals(request.email()) &&
                            usuarioRepository.existsByEmail(request.email())) {
                        throw new ConflictException(
                                "Email já cadastrado: " + request.email());
                    }

                    user.setNome(request.nome());
                    user.setEmail(request.email());
                    usuarioRepository.save(user);
                    return new UsuarioResponseDto(
                            user.getId(),
                            user.getNome(),
                            user.getEmail());
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuário não encontrado com ID: " + id));
    }

    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}