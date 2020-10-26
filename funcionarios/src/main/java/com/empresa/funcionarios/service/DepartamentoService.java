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

import com.empresa.funcionarios.model.Departamento;
import com.empresa.funcionarios.repository.DepartamentoRepository;
import com.empresa.funcionarios.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Vinicius
 *
 */
@Service
@Slf4j
public class DepartamentoService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	public ResponseEntity<?> listarDepartamentos() {

		List<Departamento> listaDepartemantos = new ArrayList<Departamento>();

		try {

			listaDepartemantos = departamentoRepository.findAll();

			return ResponseEntity.ok().body(listaDepartemantos);

		} catch (Exception e) {
			System.out.println("Erro ao listaCargos: " + e);
		}
		return null;
	}

	public ResponseEntity<?> criarDepartamento(Departamento departamento) {
		String msg = "";
		try {
			
			departamento.setChefeDepartamento(null);
			
			if (departamentoRepository.save(departamento) != null) {
				msg = messageSource.getMessage("departamento.criar", null, LocaleContextHolder.getLocale());
				return ResponseEntity.ok(ResponseUtil.getResponse(msg, "success", null));
			}

		} catch (Exception e) {
			System.out.println("Erro ao criarDepartamento: " + e);
		}

		msg = messageSource.getMessage("departamento.erro", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));
	}
	
	public ResponseEntity<?> chefeDepartamento(Departamento departamento) {
		String msg = "";
		try {

			if (departamentoRepository.save(departamento) != null) {
				msg = messageSource.getMessage("departamento.criar", null, LocaleContextHolder.getLocale());
				return ResponseEntity.ok(ResponseUtil.getResponse(msg, "success", null));
			}

		} catch (Exception e) {
			System.out.println("Erro ao criarDepartamento: " + e);
		}

		msg = messageSource.getMessage("departamento.erro", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));
	}

	public ResponseEntity<?> atualizarDepartamento(@PathVariable(value = "id") Integer id,
			@RequestBody Departamento departamento) {
		String msg = "";

		try {

			Departamento departamentoAux = departamentoRepository.findById(id).get();

			departamentoAux.setDepartamento_name(departamento.getDepartamento_name());

			if (departamentoRepository.save(departamentoAux) != null) {
				msg = messageSource.getMessage("departamento.atualizar", new String[] { null },
						LocaleContextHolder.getLocale());
				return ResponseEntity.ok().body(ResponseUtil.getResponse(msg, "success", ""));
			}

		} catch (Exception e) {
			System.out.println("Erro ao atualizar departamento " + e);
			msg = messageSource.getMessage("departamento.erro.atualizar", null, LocaleContextHolder.getLocale());
			return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));
		}

		msg = messageSource.getMessage("departamento.erro.atualizar", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));

	}

	public ResponseEntity<?> excluirDepartamento(@PathVariable(value = "id") Integer id) throws Exception {
		String msg = "";

		try {

			Departamento departamento = departamentoRepository.findById(id).get();
			departamento.setDeletado(true);

			if (departamentoRepository.save(departamento) != null) {
				msg = messageSource.getMessage("departamento.excluir", new String[] { "" },
						LocaleContextHolder.getLocale());
				return ResponseEntity.ok().body(ResponseUtil.getResponse(msg, "success", ""));
			}

		} catch (Exception e) {
			System.out.println("Erro ao excluir departamento " + e);
			msg = messageSource.getMessage("departamento.erro.excluir", null, LocaleContextHolder.getLocale());
			return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));
		}

		msg = messageSource.getMessage("departamento.erro.excluir", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));

	}

}
