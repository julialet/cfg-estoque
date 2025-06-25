package com.example.controleequipamentos.controller;

import com.example.controleequipamentos.model.Emprestimo;
import com.example.controleequipamentos.model.Equipamento;
import com.example.controleequipamentos.model.Usuario;
import com.example.controleequipamentos.service.EmprestimoService;
import com.example.controleequipamentos.service.EquipamentoService;
import com.example.controleequipamentos.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    private final EquipamentoService equipamentoService;
    private final UsuarioService usuarioService;

    public EmprestimoController(EmprestimoService emprestimoService,
                              EquipamentoService equipamentoService,
                              UsuarioService usuarioService) {
        this.emprestimoService = emprestimoService;
        this.equipamentoService = equipamentoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimoService.listarTodos());
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("equipamentos", equipamentoService.listarTodos());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "emprestimos";
    }

    @PostMapping
    public String salvar(@ModelAttribute Emprestimo emprestimo, RedirectAttributes redirectAttributes) {
        try {
            emprestimoService.salvar(emprestimo);
            redirectAttributes.addFlashAttribute("mensagem", "Empréstimo registrado com sucesso!");
            redirectAttributes.addFlashAttribute("tipoMensagem", "add");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao registrar empréstimo: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensagem", "danger");
        }
        return "redirect:/emprestimos";
    }

    @PostMapping("/devolver/{id}")
    public String devolver(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            emprestimoService.registrarDevolucao(id);
            redirectAttributes.addFlashAttribute("mensagem", "Devolução registrada com sucesso!");
            redirectAttributes.addFlashAttribute("tipoMensagem", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao registrar devolução: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensagem", "danger");
        }
        return "redirect:/emprestimos";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            emprestimoService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagem", "Empréstimo excluído com sucesso!");
            redirectAttributes.addFlashAttribute("tipoMensagem", "remove");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir empréstimo: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensagem", "danger");
        }
        return "redirect:/emprestimos";
    }
} 