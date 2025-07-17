package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Título não pode ser vazio.")
    private String titulo;

    @NotBlank(message = "Descrição não pode ser vazia.")
    private String descricao;

    @NotNull(message = "Data de abertura não pode ser nula.")
    private LocalDate dataAbertura;

    @NotNull(message = "Prioridade não pode ser nula.")
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @NotNull(message = "Status não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "O chamado deve estar associado a um cliente.")
    private Cliente cliente;

    public enum Prioridade {
        BAIXA, MEDIA, ALTA
    }

    public enum Status {
        ABERTO, EM_ANDAMENTO, CONCLUIDO
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}