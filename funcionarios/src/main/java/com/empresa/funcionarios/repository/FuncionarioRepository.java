/**
 * 
 */
package com.empresa.funcionarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.funcionarios.model.Funcionario;

/**
 * @author Vinicius
 *
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	
}
