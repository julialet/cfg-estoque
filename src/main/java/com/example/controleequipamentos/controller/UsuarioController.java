package com.example.controleequipamentos.controller;

import com.example.controleequipamentos.model.Usuario;
import com.example.controleequipamentos.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", service.listarTodos());
        model.addAttribute("usuario", new Usuario());
        return "usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = service.buscarPorId(id);
        model.addAttribute("usuarios", service.listarTodos());
        model.addAttribute("usuario", usuario);
        return "usuarios";
    }

    @PostMapping
    public String salvar(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            service.salvar(usuario);
            redirectAttributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
            redirectAttributes.addFlashAttribute("tipoMensagem", "add");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao salvar usuário: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensagem", "danger");
        }
        return "redirect:/usuarios";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            service.excluir(id);
            redirectAttributes.addFlashAttribute("mensagem", "Usuário excluído com sucesso!");
            redirectAttributes.addFlashAttribute("tipoMensagem", "remove");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir usuário: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensagem", "danger");
        }
        return "redirect:/usuarios";
    }
}
