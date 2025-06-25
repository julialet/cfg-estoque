package com.example.controleequipamentos.service;

import com.example.controleequipamentos.model.Emprestimo;
import com.example.controleequipamentos.model.Equipamento;
import com.example.controleequipamentos.repository.EmprestimoRepository;
import com.example.controleequipamentos.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, 
                           EquipamentoRepository equipamentoRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimoRepository.findByDataDevolucaoRealIsNull();
    }

    public Emprestimo buscarPorId(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
    }

    @Transactional
    public Emprestimo salvar(Emprestimo emprestimo) {
        // Verifica se o equipamento existe e está disponível
        Equipamento equipamento = equipamentoRepository.findById(emprestimo.getEquipamento().getId())
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        if (equipamento.getQuantidade() <= 0) {
            throw new RuntimeException("Equipamento não está disponível para empréstimo");
        }

        // Verifica se já existe algum empréstimo ativo para este equipamento
        List<Emprestimo> emprestimosAtivos = emprestimoRepository.findByEquipamentoIdAndDataDevolucaoRealIsNull(
                emprestimo.getEquipamento().getId());
        if (!emprestimosAtivos.isEmpty()) {
            throw new RuntimeException("Este equipamento já está emprestado");
        }

        emprestimo.setDataEmprestimo(LocalDate.now());
        return emprestimoRepository.save(emprestimo);
    }

    @Transactional
    public Emprestimo registrarDevolucao(Long id) {
        Emprestimo emprestimo = buscarPorId(id);
        
        if (emprestimo.getDataDevolucaoReal() != null) {
            throw new RuntimeException("Este empréstimo já foi devolvido");
        }

        emprestimo.setDataDevolucaoReal(LocalDate.now());
        return emprestimoRepository.save(emprestimo);
    }

    @Transactional
    public void excluir(Long id) {
        if (!emprestimoRepository.existsById(id)) {
            throw new RuntimeException("Empréstimo não encontrado");
        }
        emprestimoRepository.deleteById(id);
    }
} 