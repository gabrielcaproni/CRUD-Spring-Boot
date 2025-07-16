package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Nome do usuário não pode ser vazio.")
	private String name;
	
	@NotNull(message = "Gênero não pode ser nulo.")
	private Gender gender;
	
	//@Column(name = "e_mail")
	@NotBlank(message = "Email do usuário não pode ser vazio.")
	@Email(message = "Email inválido.")
	private String email;
	
	@OneToOne(optional = false)
	@Valid
	private Address address;
	
	@Transient
	private String log;
	
	public User() {
		setName("");
		setGender(Gender.F);
		setEmail("");
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	
	public enum Gender {
		M('M'),
		F('F');
		
		private Character gender;
		
		private Gender(Character gender) {
			this.gender = gender;
		}
		
		public Character getGender() {
			return gender;
		}
	}
}
