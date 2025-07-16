package br.edu.ifsuldeminas.mch.springbootcrud.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
}
