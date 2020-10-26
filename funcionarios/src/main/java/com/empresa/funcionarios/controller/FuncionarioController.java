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

import com.empresa.funcionarios.model.Funcionario;
import com.empresa.funcionarios.service.FuncionarioService;

/**
 * @author Vinicius
 *
 */
@RestController
@RequestMapping("/api")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping("/listaFuncionarios")
	public ResponseEntity<?> listarCargos() {
		return funcionarioService.listarDepartamentos();
	}
	
	@GetMapping("/listaFuncionarioDepartamento/{id}")
	public ResponseEntity<?> listaFuncionarioDepartamento(@PathVariable(value = "id") Integer id) {
		return funcionarioService.listaFuncionarioDepartamento(id);
	}

	@PostMapping("/criarFuncionario")
	public ResponseEntity<?> criarFuncionario(@RequestBody Funcionario func) {
		return funcionarioService.criarFuncionario(func);
	}
	
	@PostMapping("/funcionarioDepartamento")
	public ResponseEntity<?> funcionarioDepartamento(@RequestBody Funcionario func) {
		return funcionarioService.funcionarioDepartamento(func);
	}

	@PutMapping("/atualizarFuncionario/{id}")
	public ResponseEntity<?> atualizarFuncionario(@PathVariable(value = "id") Integer id,
			@RequestBody Funcionario funcionario) {
		return funcionarioService.atualizarFuncionario(id, funcionario);
	}

	@DeleteMapping("/excluirFuncionario/{id}")
	public ResponseEntity<?> excluirFuncionario(@PathVariable(value = "id") Integer id) throws Exception {
		return funcionarioService.excluirFuncionario(id);
	}

}
