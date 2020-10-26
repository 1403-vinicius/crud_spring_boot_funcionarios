/**
 * 
 */
package com.empresa.funcionarios.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Vinicius
 *
 */
@Entity
@Table(name = "funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int funcionario_id;
	private String funcionario_name;
	private int funcionario_age;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date funcionario_birtday;
	private String funcionario_document;
	private boolean deletado;
	private String historicoDepartamentos;
	
	@Transient
	private List<Departamento> departamentos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.funcionario", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FuncionarioDepartamento> funcionarioDepartamentos = new HashSet<FuncionarioDepartamento>(0);

	public int getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(int funcionario_id) {
		this.funcionario_id = funcionario_id;
	}

	public String getFuncionario_name() {
		return funcionario_name;
	}

	public void setFuncionario_name(String funcionario_name) {
		this.funcionario_name = funcionario_name;
	}

	public int getFuncionario_age() {
		return funcionario_age;
	}

	public void setFuncionario_age(int funcionario_age) {
		this.funcionario_age = funcionario_age;
	}

	public Date getFuncionario_birtday() {
		return funcionario_birtday;
	}

	public void setFuncionario_birtday(Date funcionario_birtday) {
		this.funcionario_birtday = funcionario_birtday;
	}

	public String getFuncionario_document() {
		return funcionario_document;
	}

	public void setFuncionario_document(String funcionario_document) {
		this.funcionario_document = funcionario_document;
	}
	
	public String getHistoricoDepartamentos() {
		return historicoDepartamentos;
	}

	public void setHistoricoDepartamentos(String historicoDepartamentos) {
		this.historicoDepartamentos = historicoDepartamentos;
	}

	public boolean isDeletado() {
		return deletado;
	}

	public void setDeletado(boolean deletado) {
		this.deletado = deletado;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Set<FuncionarioDepartamento> getFuncionarioDepartamentos() {
		return funcionarioDepartamentos;
	}

	public void setFuncionarioDepartamentos(Set<FuncionarioDepartamento> funcionarioDepartamentos) {
		this.funcionarioDepartamentos = funcionarioDepartamentos;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + funcionario_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (funcionario_id != other.funcionario_id)
			return false;
		return true;
	}

	

}
