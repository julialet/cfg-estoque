package com.example.controleequipamentos.service;

import com.example.controleequipamentos.model.Equipamento;
import com.example.controleequipamentos.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipamentoService {

    private final EquipamentoRepository repository;

    public EquipamentoService(EquipamentoRepository repository) {
        this.repository = repository;
    }

    public List<Equipamento> listarTodos() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar equipamentos: " + e.getMessage());
        }
    }

    public Equipamento buscarPorId(Long id) {
        try {
            if (id == null) {
                throw new RuntimeException("ID do equipamento não pode ser nulo");
            }
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar equipamento: " + e.getMessage());
        }
    }

    @Transactional
    public Equipamento salvar(Equipamento equipamento) {
        try {
            if (equipamento == null) {
                throw new RuntimeException("Equipamento não pode ser nulo");
            }
            if (equipamento.getNome() == null || equipamento.getNome().trim().isEmpty()) {
                throw new RuntimeException("Nome do equipamento é obrigatório");
            }
            if (equipamento.getQuantidade() == null) {
                equipamento.setQuantidade(0);
            }
            return repository.save(equipamento);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar equipamento: " + e.getMessage());
        }
    }

    @Transactional
    public void excluir(Long id) {
        try {
            if (id == null) {
                throw new RuntimeException("ID do equipamento não pode ser nulo");
            }
            if (!repository.existsById(id)) {
                throw new RuntimeException("Equipamento não encontrado");
            }
            repository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir equipamento: " + e.getMessage());
        }
    }
}
