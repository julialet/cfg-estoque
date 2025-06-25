package com.example.controleequipamentos.repository;

import com.example.controleequipamentos.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByEquipamentoId(Long equipamentoId);
    List<Emprestimo> findByUsuarioId(Long usuarioId);
    List<Emprestimo> findByDataDevolucaoRealIsNull();
    List<Emprestimo> findByEquipamentoIdAndDataDevolucaoRealIsNull(Long equipamentoId);
} 