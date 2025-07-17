package br.edu.ifsuldeminas.mch.springbootcrud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Address;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Cliente; // 1. IMPORTAR A CLASSE CLIENTE
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.AddressRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.UserRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.ClienteRepository; // 2. IMPORTAR O REPOSITÓRIO DO CLIENTE
import jakarta.transaction.Transactional;

@Component
@Transactional
public class AtSystemStartup implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AddressRepository addressRepository;

    // 3. INJETAR O REPOSITÓRIO DO CLIENTE
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Bloco de código para criar Endereços e Usuários (existente)
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

        Cliente cliente1 = new Cliente();
        cliente1.setName("Tech Solutions Ltda");
        cliente1.setEmail("contato@techsolutions.com");
        cliente1.setPhone("11987654321");
        cliente1.setDepartment("TI");
        
        Cliente cliente2 = new Cliente();
        cliente2.setName("Inova Corp");
        cliente2.setEmail("suporte@inovacorp.com.br");
        cliente2.setPhone("21912345678");
        cliente2.setDepartment("Financeiro");

        Cliente cliente3 = new Cliente();
        cliente3.setName("Marketing Criativo");
        cliente3.setEmail("atendimento@marketingcriativo.com");
        cliente3.setPhone("31999998888");
        cliente3.setDepartment("Marketing");
        
        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);
        clienteRepository.save(cliente3);
    }
}