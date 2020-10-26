/**
 * 
 */
package com.empresa.funcionarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.empresa.funcionarios.model.Cargo;

/**
 * @author Vinicius
 *
 */
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer>{
	@Query("SELECT c FROM Cargo c where c.deletado=false")
	public List<Cargo> listarCargos();
}
