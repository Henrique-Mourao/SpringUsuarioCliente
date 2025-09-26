package com.example.demo.repository;


import com.example.demo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioRepository extends JpaRepository <Usuario,Long> {


}
