/**
 * 
 */
package com.empresa.funcionarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.funcionarios.model.Cargo;
import com.empresa.funcionarios.service.CargoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Vinicius
 *
 */
@RestController
@RequestMapping("/api")
@Api(value = "Cargo")
@CrossOrigin(origins = "*")
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@ApiOperation(value = "Listar de Cargos inseridos ao sistema")
	@GetMapping("/listaCargos")
	public ResponseEntity<?> listarCargos() {
		return cargoService.listarCargos();
	}

	@PostMapping("/criarCargo")
	public ResponseEntity<?> criarCargo(@RequestBody Cargo cargo) {
		return cargoService.criarCargo(cargo);
	}

	@PutMapping("/atualizarCargo/{id}")
	public ResponseEntity<?> atualizarCargo(@PathVariable(value = "id") Integer id, @RequestBody Cargo cargo) {
		return cargoService.atualizarCargo(id, cargo);
	}

	@DeleteMapping("/excluirCargo/{id}")
	public ResponseEntity<?> excluirCargo(@PathVariable(value = "id") Integer id) throws Exception {
		return cargoService.excluirCargo(id);
	}

}
