/**
 * 
 */
package com.empresa.funcionarios.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Vinicius
 *
 */
@Entity
@Table(name = "departamento")
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int departamento_id;
	private String departamento_name;
	private boolean deletado;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.departamento", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<FuncionarioDepartamento> funcionarioDepartamentos = new HashSet<FuncionarioDepartamento>(0);
	
	@OneToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario chefeDepartamento;

	public boolean isDeletado() {
		return deletado;
	}

	public void setDeletado(boolean deletado) {
		this.deletado = deletado;
	}

	public Set<FuncionarioDepartamento> getFuncionarioDepartamentos() {
		return funcionarioDepartamentos;
	}

	public void setFuncionarioDepartamentos(Set<FuncionarioDepartamento> funcionarioDepartamentos) {
		this.funcionarioDepartamentos = funcionarioDepartamentos;
	}

	public int getDepartamento_id() {
		return departamento_id;
	}

	public void setDepartamento_id(int departamento_id) {
		this.departamento_id = departamento_id;
	}

	public String getDepartamento_name() {
		return departamento_name;
	}

	public void setDepartamento_name(String departamento_name) {
		this.departamento_name = departamento_name;
	}

	public Funcionario getChefeDepartamento() {
		return chefeDepartamento;
	}

	public void setChefeDepartamento(Funcionario chefeDepartamento) {
		this.chefeDepartamento = chefeDepartamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + departamento_id;
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
		Departamento other = (Departamento) obj;
		if (departamento_id != other.departamento_id)
			return false;
		return true;
	}

}
