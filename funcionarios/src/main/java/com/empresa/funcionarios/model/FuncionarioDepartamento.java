/**
 * 
 */
package com.empresa.funcionarios.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @author Vinicius
 *
 */
@Entity
@Table(name="funcionario_departamento")
@AssociationOverrides({ @AssociationOverride(name = "pk.funcionario", joinColumns = @JoinColumn(name = "departamento_id")),
		@AssociationOverride(name = "pk.departamento", joinColumns = @JoinColumn(name = "funcionario_id")) })
public class FuncionarioDepartamento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "departamento_id", insertable = false, updatable = false)
	private int departamento_id;
	@Column(name = "funcionario_id", insertable = false, updatable = false)
	private int funcionario_id;
	@EmbeddedId
	private FuncionarioDepartamentoPK pk = new FuncionarioDepartamentoPK();
	
	public Departamento getDepartamento() {
		return getPk().getDepartamento();
	}
	
	public void setgetDepartamento(Departamento departamento) {
        getPk().setDepartamento(departamento);
    }
	
	public Funcionario getFuncionario() {
        return getPk().getFuncionario();
    }

    public void setFuncionario(Funcionario funcionario) {
        getPk().setFuncionario(funcionario);
    }
	
	public int getDepartamento_id() {
		return departamento_id;
	}
	public void setDepartamento_id(int departamento_id) {
		this.departamento_id = departamento_id;
	}
	public int getFuncionario_id() {
		return funcionario_id;
	}
	public void setFuncionario_id(int funcionario_id) {
		this.funcionario_id = funcionario_id;
	}
	public FuncionarioDepartamentoPK getPk() {
		return pk;
	}
	public void setPk(FuncionarioDepartamentoPK pk) {
		this.pk = pk;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + departamento_id;
		result = prime * result + funcionario_id;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		FuncionarioDepartamento other = (FuncionarioDepartamento) obj;
		if (departamento_id != other.departamento_id)
			return false;
		if (funcionario_id != other.funcionario_id)
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
	
	

}
