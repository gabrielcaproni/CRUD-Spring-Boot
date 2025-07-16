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

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Address;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.AddressRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	AddressRepository addressRepository;
	
	@GetMapping("/users")
	public String users(Model model) {
		List<User> users = userRepository.findAll();
		
		model.addAttribute("usuarios", users);
		
		return "index.html";
	}
	
	@GetMapping("/users/form")
	public String userForm(@ModelAttribute("user") 
	                       User user) {
		
		return "user_form.html";
	}
	
	@PostMapping("/users/new")
	public String userSave(@Valid
			               @ModelAttribute("user")
	                       User user,
	                       BindingResult validationResults) {
		
		if (validationResults.hasErrors()) {
			return "user_form.html";
		}

		addressRepository.save(user.getAddress());
		userRepository.save(user);
		
		return "redirect:/users";
	}
	
	@GetMapping("/users/update/{id}")
	public String userUpdate(@PathVariable 
			                 int id, 
			                 Model model) {
			
		Optional<User> userOpt = userRepository.findById(id);
		
		if (!userOpt.isPresent())
			throw new IllegalArgumentException(
					"Usuário de id " + id + " não existe");
		
		User user = userOpt.get();
		model.addAttribute("user", user);
		return "user_form.html";
	}
	
	@GetMapping("/users/delete/{id}")
	public String userDelete(@PathVariable() 
                             int id) {
		
		Optional<User> userOpt = userRepository.findById(id);
		
		if (!userOpt.isPresent())
			throw new IllegalArgumentException(
					"Usuário de id " + id + " não existe");
		
		userRepository.delete(userOpt.get());
		
		return "redirect:/users";
	}
}
