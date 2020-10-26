/**
 * 
 */
package com.empresa.funcionarios.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.empresa.funcionarios.model.Cargo;
import com.empresa.funcionarios.repository.CargoRepository;
import com.empresa.funcionarios.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Vinicius
 *
 */
@Service
@Slf4j
public class CargoService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CargoRepository cargoRepository;

	public ResponseEntity<?> listarCargos() {

		List<Cargo> listaCargos = new ArrayList<Cargo>();

		try {
			
			listaCargos = cargoRepository.listarCargos();

			return ResponseEntity.ok().body(listaCargos);

		} catch (Exception e) {
			System.out.println("Erro ao listaCargos: " + e);
		}
		return null;
	}

	public ResponseEntity<?> criarCargo(Cargo cargo) {
		String msg = "";
		try {

			if (cargoRepository.save(cargo) != null) {
				msg = messageSource.getMessage("cargo.criar", null, LocaleContextHolder.getLocale());
				return ResponseEntity.ok(ResponseUtil.getResponse(msg, "success", null));
			}

		} catch (Exception e) {
			System.out.println("Erro ao criarCargo: " + e);
		}

		msg = messageSource.getMessage("cargo.erro", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));
	}

	public ResponseEntity<?> atualizarCargo(@PathVariable(value = "id") Integer id, @RequestBody Cargo cargo) {
		String msg = "";

		try {

			Cargo cargoAux = cargoRepository.findById(id).get();

			cargoAux.setCargo_name(cargo.getCargo_name());

			if (cargoRepository.save(cargoAux) != null) {
				msg = messageSource.getMessage("cargo.atualizar", new String[] { null },
						LocaleContextHolder.getLocale());
				return ResponseEntity.ok().body(ResponseUtil.getResponse(msg, "success", ""));
			}

		} catch (Exception e) {
			System.out.println("Erro ao atualizar cargo " + e);
			msg = messageSource.getMessage("cargo.erro.atualizar", null, LocaleContextHolder.getLocale());
			return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));
		}

		msg = messageSource.getMessage("cargo.erro.atualizar", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));

	}

	public ResponseEntity<?> excluirCargo(@PathVariable(value = "id") Integer id) throws Exception {
		String msg = "";

		try {

			Cargo cargo = cargoRepository.findById(id).get();
			cargo.setDeletado(true);

			if (cargoRepository.save(cargo) != null) {
				msg = messageSource.getMessage("cargo.excluir", new String[] { "" }, LocaleContextHolder.getLocale());
				return ResponseEntity.ok().body(ResponseUtil.getResponse(msg, "success", ""));
			}

		} catch (Exception e) {
			System.out.println("Erro ao excluir cargo " + e);
			msg = messageSource.getMessage("cargo.erro.excluir", null, LocaleContextHolder.getLocale());
			return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));
		}

		msg = messageSource.getMessage("cargo.erro.excluir", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));

	}

}
