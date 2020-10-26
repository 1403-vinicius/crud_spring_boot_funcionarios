/**
 * 
 */
package com.empresa.funcionarios.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vinicius
 *
 */
@Entity
@Table(name="cargo")
public class Cargo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cargo_id;
	private String cargo_name;
	private boolean deletado;
	
	public int getCargo_id() {
		return cargo_id;
	}
	public void setCargo_id(int cargo_id) {
		this.cargo_id = cargo_id;
	}
	public String getCargo_name() {
		return cargo_name;
	}
	public void setCargo_name(String cargo_name) {
		this.cargo_name = cargo_name;
	}
	
	
	
	public boolean isDeletado() {
		return deletado;
	}
	public void setDeletado(boolean deletado) {
		this.deletado = deletado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cargo_id;
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
		Cargo other = (Cargo) obj;
		if (cargo_id != other.cargo_id)
			return false;
		return true;
	}
	
	
	
	
	
}
