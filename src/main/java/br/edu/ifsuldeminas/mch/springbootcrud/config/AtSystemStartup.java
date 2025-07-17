package br.edu.ifsuldeminas.mch.springbootcrud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Address;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Cliente;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Chamado; // IMPORTAR A CLASSE CHAMADO
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.AddressRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.UserRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.ClienteRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.ChamadoRepository; // IMPORTAR O REPOSITÓRIO DO CHAMADO
import jakarta.transaction.Transactional;

import java.time.LocalDate; // Importar LocalDate para a data do chamado

@Component
@Transactional
public class AtSystemStartup implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository; // INJETAR O REPOSITÓRIO DO CHAMADO
    
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

        // Bloco de código para criar Clientes (existente)
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
        clienteRepository.flush(); // Garante que os IDs dos clientes sejam gerados antes de usar nos chamados

        // Bloco de código para criar Chamados
        Chamado chamado1 = new Chamado();
        chamado1.setTitulo("Problema de rede no escritório A");
        chamado1.setDescricao("Conexão intermitente na sala de reunião.");
        chamado1.setDataAbertura(LocalDate.now()); // Data atual
        chamado1.setPrioridade(Chamado.Prioridade.ALTA);
        chamado1.setStatus(Chamado.Status.ABERTO);
        chamado1.setCliente(cliente1); // Associar ao cliente1
        
        Chamado chamado2 = new Chamado();
        chamado2.setTitulo("Solicitação de instalação de software X");
        chamado2.setDescricao("Instalar software X nas máquinas do departamento financeiro.");
        chamado2.setDataAbertura(LocalDate.now());
        chamado2.setPrioridade(Chamado.Prioridade.MEDIA);
        chamado2.setStatus(Chamado.Status.EM_ANDAMENTO);
        chamado2.setCliente(cliente2); // Associar ao cliente2

        Chamado chamado3 = new Chamado();
        chamado3.setTitulo("Erro no sistema de e-mail marketing");
        chamado3.setDescricao("Campanhas de e-mail não estão sendo enviadas.");
        chamado3.setDataAbertura(LocalDate.now());
        chamado3.setPrioridade(Chamado.Prioridade.ALTA);
        chamado3.setStatus(Chamado.Status.ABERTO);
        chamado3.setCliente(cliente3); // Associar ao cliente3
        
        chamadoRepository.save(chamado1);
        chamadoRepository.save(chamado2);
        chamadoRepository.save(chamado3);
    }
}