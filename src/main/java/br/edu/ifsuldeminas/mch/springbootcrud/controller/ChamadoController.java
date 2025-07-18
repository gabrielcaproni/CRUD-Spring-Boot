package br.edu.ifsuldeminas.mch.springbootcrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Chamado;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Cliente;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.ChamadoRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.ClienteRepository; 
import jakarta.validation.Valid;

@Controller
public class ChamadoController {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/chamados")
    public String chamados(Model model) {
        List<Chamado> chamados = chamadoRepository.findAll();
        model.addAttribute("chamados", chamados);
        return "chamado_lista.html";
    }

    @GetMapping("/chamados/form")
    public String chamadoForm(@ModelAttribute("chamado") Chamado chamado, Model model) {
        List<Cliente> todosClientes = clienteRepository.findAll();
        model.addAttribute("todosClientes", todosClientes);
        return "chamado_form.html";
    }

    @PostMapping("/chamados/new")
    public String chamadoSave(@Valid @ModelAttribute("chamado") Chamado chamado,
                              BindingResult validationResults,
                              Model model) {
        if (validationResults.hasErrors()) {
            List<Cliente> todosClientes = clienteRepository.findAll();
            model.addAttribute("todosClientes", todosClientes);
            return "chamado_form.html";
        }
        chamadoRepository.save(chamado);
        return "redirect:/chamados";
    }

    @GetMapping("/chamados/update/{id}")
    public String chamadoUpdate(@PathVariable int id, Model model) {
        Optional<Chamado> chamadoOpt = chamadoRepository.findById(id);

        if (!chamadoOpt.isPresent())
            throw new IllegalArgumentException("Chamado de id " + id + " não existe");

        Chamado chamado = chamadoOpt.get();
        model.addAttribute("chamado", chamado);

        List<Cliente> todosClientes = clienteRepository.findAll();
        model.addAttribute("todosClientes", todosClientes);

        return "chamado_form.html";
    }

    @GetMapping("/chamados/delete/{id}")
    public String chamadoDelete(@PathVariable int id) {
        Optional<Chamado> chamadoOpt = chamadoRepository.findById(id);

        if (!chamadoOpt.isPresent())
            throw new IllegalArgumentException("Chamado de id " + id + " não existe");

        chamadoRepository.delete(chamadoOpt.get());
        return "redirect:/chamados";
    }
}