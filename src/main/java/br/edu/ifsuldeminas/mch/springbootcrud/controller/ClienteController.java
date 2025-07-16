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

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Cliente;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.ClienteRepository;
import jakarta.validation.Valid;

@Controller
public class ClienteController {

    @Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public String clientes(Model model) {
		List<Cliente> clientes = clienteRepository.findAll();
		
		model.addAttribute("clientes", clientes);
		
		return "cliente_lista.html";
	}
	
	@GetMapping("/clientes/form")
	public String userForm(@ModelAttribute("cliente") 
	                       Cliente cliente) {
		
		return "cliente_form.html";
	}
	
	@PostMapping("/clientes/new")
	public String clienteSave(@Valid
			               @ModelAttribute("cliente")
	                       Cliente cliente,
	                       BindingResult validationResults) {
		
		if (validationResults.hasErrors()) {
			return "cliente_form.html";
		}

		clienteRepository.save(cliente);
		
		return "redirect:/clientes";
	}
	
	@GetMapping("/clientes/update/{id}")
	public String clienteUpdate(@PathVariable 
			                 int id, 
			                 Model model) {
			
		Optional<Cliente> clienteOpt = clienteRepository.findById(id);
		
		if (!clienteOpt.isPresent())
			throw new IllegalArgumentException(
					"Cliente de id " + id + " não existe");
		
		Cliente cliente = clienteOpt.get();
		model.addAttribute("cliente", cliente);
		return "cliente_form.html";
	}
	
	@GetMapping("/clientes/delete/{id}")
	public String ClienteDelete(@PathVariable() 
                             int id) {
		
		Optional<Cliente> clienteOpt = clienteRepository.findById(id);
		
		if (!clienteOpt.isPresent())
			throw new IllegalArgumentException(
					"Cliente de id " + id + " não existe");
		
		clienteRepository.delete(clienteOpt.get());
		
		return "redirect:/clientes";
	}
}
