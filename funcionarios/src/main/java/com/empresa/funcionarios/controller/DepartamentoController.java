/**
 * 
 */
package com.empresa.funcionarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.funcionarios.model.Departamento;
import com.empresa.funcionarios.service.DepartamentoService;

/**
 * @author Vinicius
 *
 */
@RestController
@RequestMapping("/api")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/listaDepartamentos")
	public ResponseEntity<?> listarCargos() {
		return departamentoService.listarDepartamentos();
	}

	@PostMapping("/criarDepartamento")
	public ResponseEntity<?> criarDepartamento(@RequestBody Departamento depto) {
		return departamentoService.criarDepartamento(depto);
	}
	
	@PostMapping("/chefeDepartamento")
	public ResponseEntity<?> chefeDepartamento(@RequestBody Departamento depto) {
		return departamentoService.chefeDepartamento(depto);
	}

	@PutMapping("/atualizarDepartamento/{id}")
	public ResponseEntity<?> atualizarDepartamento(@PathVariable(value = "id") Integer id,
			@RequestBody Departamento departamento) {
		return departamentoService.atualizarDepartamento(id, departamento);
	}

	@DeleteMapping("/excluirDepartamento/{id}")
	public ResponseEntity<?> excluirDepartamento(@PathVariable(value = "id") Integer id) throws Exception {
		return departamentoService.excluirDepartamento(id);
	}

}
