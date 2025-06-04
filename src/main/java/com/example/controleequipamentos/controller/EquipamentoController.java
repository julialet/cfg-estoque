package com.example.controleequipamentos.controller;

import com.example.controleequipamentos.model.Equipamento;
import com.example.controleequipamentos.service.EquipamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private static final Logger logger = LoggerFactory.getLogger(EquipamentoController.class);
    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        try {
            logger.info("Listando todos os equipamentos");
            model.addAttribute("equipamentos", service.listarTodos());
            model.addAttribute("equipamento", new Equipamento());
            return "equipamentos";
        } catch (Exception e) {
            logger.error("Erro ao listar equipamentos", e);
            return "redirect:/error";
        }
    }

    @PostMapping
    public String salvar(@ModelAttribute Equipamento equipamento, RedirectAttributes redirectAttributes) {
        try {
            logger.info("Salvando equipamento: {}", equipamento);
            if (equipamento == null) {
                logger.error("Equipamento é nulo");
                throw new RuntimeException("Equipamento não pode ser nulo");
            }
            if (equipamento.getNome() == null || equipamento.getNome().trim().isEmpty()) {
                logger.error("Nome do equipamento é vazio");
                throw new RuntimeException("Nome do equipamento é obrigatório");
            }
            if (equipamento.getQuantidade() == null) {
                logger.info("Quantidade é nula, definindo como 0");
                equipamento.setQuantidade(0);
            }
            
            Equipamento saved = service.salvar(equipamento);
            logger.info("Equipamento salvo com sucesso: {}", saved);
            
            redirectAttributes.addFlashAttribute("mensagem", "Equipamento salvo com sucesso!");
            redirectAttributes.addFlashAttribute("tipoMensagem", "add");
        } catch (Exception e) {
            logger.error("Erro ao salvar equipamento: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao salvar equipamento: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensagem", "danger");
        }
        return "redirect:/equipamentos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        try {
            logger.info("Editando equipamento com ID: {}", id);
            Equipamento equipamento = service.buscarPorId(id);
            logger.info("Equipamento encontrado: {}", equipamento);
            
            if (equipamento == null) {
                logger.warn("Equipamento não encontrado para o ID: {}", id);
                return "redirect:/equipamentos";
            }
            
            model.addAttribute("equipamento", equipamento);
            model.addAttribute("equipamentos", service.listarTodos());
            logger.info("Model attributes: equipamento={}, equipamentos size={}", 
                       equipamento, service.listarTodos().size());
            
            return "equipamentos";
        } catch (Exception e) {
            logger.error("Erro ao editar equipamento com ID: {} - {}", id, e.getMessage(), e);
            return "redirect:/equipamentos";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            logger.info("Excluindo equipamento com ID: {}", id);
            service.excluir(id);
            redirectAttributes.addFlashAttribute("mensagem", "Equipamento excluído com sucesso!");
            redirectAttributes.addFlashAttribute("tipoMensagem", "remove");
        } catch (Exception e) {
            logger.error("Erro ao excluir equipamento com ID: " + id, e);
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir equipamento: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensagem", "danger");
        }
        return "redirect:/equipamentos";
    }
}
