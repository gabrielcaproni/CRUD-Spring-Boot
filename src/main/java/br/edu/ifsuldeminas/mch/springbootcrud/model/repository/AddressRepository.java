package br.edu.ifsuldeminas.mch.springbootcrud.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {}
