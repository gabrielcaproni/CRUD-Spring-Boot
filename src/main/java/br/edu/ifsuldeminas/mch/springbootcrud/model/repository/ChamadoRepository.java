package br.edu.ifsuldeminas.mch.springbootcrud.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Chamado;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {}