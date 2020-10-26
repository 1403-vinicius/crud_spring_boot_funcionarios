/**
 * 
 */
package com.empresa.funcionarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.funcionarios.model.Departamento;

/**
 * @author Vinicius
 *
 */
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

}
