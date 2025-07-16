package br.edu.ifsuldeminas.mch.springbootcrud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Address;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.AddressRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.UserRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class AtSystemStartup implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Address aEmerson = new Address();
		aEmerson.setNumber(123);
		aEmerson.setPlace("Rua A");
		aEmerson.setZipCode("136000");
		addressRepository.save(aEmerson);
		
		Address aNoe = new Address();
		aNoe.setNumber(100);
		aNoe.setPlace("Rua B");
		aNoe.setZipCode("136888");
		addressRepository.save(aNoe);
		
		Address aLu = new Address();
		aLu.setNumber(101);
		aLu.setPlace("Rua L");
		aLu.setZipCode("000888");
		addressRepository.save(aLu);
		
		addressRepository.flush();
		
		User emerson = new User();
		emerson.setName("Emerson A. Carvalho");
		emerson.setGender(User.Gender.M);
		emerson.setEmail("emerson@mail.com");
		emerson.setAddress(aEmerson);
		
		User luiza = new User();
		luiza.setName("Luiza O. Carvalho");
		luiza.setGender(User.Gender.F);
		luiza.setEmail("lu@mail.com");
		luiza.setAddress(aLu);
		
		User noe = new User();
		noe.setName("Noe L. Carvalho");
		noe.setGender(User.Gender.M);
		noe.setEmail("noe@mail.com");
		noe.setAddress(aNoe);
		
		userRepository.save(emerson);
		userRepository.save(luiza);
		userRepository.save(noe);
	}
}
