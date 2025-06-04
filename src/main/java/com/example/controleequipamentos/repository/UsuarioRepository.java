package com.example.controleequipamentos.repository;

import com.example.controleequipamentos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> { }
