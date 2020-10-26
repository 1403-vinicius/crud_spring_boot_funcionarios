/**
 * 
 */
package com.empresa.funcionarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.funcionarios.model.Departamento;
import com.empresa.funcionarios.model.FuncionarioDepartamento;

/**
 * @author Vinicius
 *
 */
public interface FuncionarioDepartamentoRepository extends JpaRepository<FuncionarioDepartamento, Integer>{
	@Query("SELECT f FROM FuncionarioDepartamento f where f.pk.departamento=?1")
	public List<FuncionarioDepartamento> funcionarioPorDepartamento(Departamento dpto);
}
