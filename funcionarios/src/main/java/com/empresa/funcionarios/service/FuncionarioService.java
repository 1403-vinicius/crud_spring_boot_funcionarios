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
import com.empresa.funcionarios.model.Funcionario;
import com.empresa.funcionarios.model.FuncionarioDepartamento;
import com.empresa.funcionarios.repository.DepartamentoRepository;
import com.empresa.funcionarios.repository.FuncionarioDepartamentoRepository;
import com.empresa.funcionarios.repository.FuncionarioRepository;
import com.empresa.funcionarios.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Vinicius
 *
 */
@Service
@Slf4j
public class FuncionarioService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private FuncionarioDepartamentoRepository funcionarioDepartamentoRepository;

	public ResponseEntity<?> listarDepartamentos() {

		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

		try {

			listaFuncionarios = funcionarioRepository.findAll();

			for (Funcionario funcionarios : listaFuncionarios) {
				if (funcionarios.getFuncionarioDepartamentos() != null) {

					for (FuncionarioDepartamento aux : funcionarios.getFuncionarioDepartamentos()) {
						Departamento dp = new Departamento();
						dp.setDepartamento_id(aux.getPk().getDepartamento().getDepartamento_id());
						dp.setDepartamento_name(aux.getPk().getDepartamento().getDepartamento_name());
						funcionarios.getDepartamentos().add(dp);
					}
				}
			}

			return ResponseEntity.ok().body(listaFuncionarios);

		} catch (Exception e) {
			System.out.println("Erro ao listarDepartamentos: " + e);
		}
		return null;
	}

	public ResponseEntity<?> listaFuncionarioDepartamento(Integer idDpto) {

		try {

			Departamento dpto = departamentoRepository.findById(idDpto).get();
			List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
			List<Funcionario> listaFuncionariosAux = new ArrayList<Funcionario>();

			List<FuncionarioDepartamento> list = funcionarioDepartamentoRepository.funcionarioPorDepartamento(dpto);
			if (list != null) {
				for (FuncionarioDepartamento funcDpto : list) {
					listaFuncionarios.add(funcDpto.getPk().getFuncionario());
				}
			}

			if (listaFuncionarios != null) {
				for (Funcionario funcionario : listaFuncionarios) {
					Departamento dp = null;
					FuncionarioDepartamento dpAux = null;
					for (Departamento d : funcionario.getDepartamentos()) {
						dp = departamentoRepository.findById(d.getDepartamento_id()).get();
						dpAux = new FuncionarioDepartamento();
						dpAux.setFuncionario(funcionario);
						dpAux.setgetDepartamento(dp);
						funcionario.getFuncionarioDepartamentos().add(dpAux);
					}
					listaFuncionariosAux.add(funcionario);
				}

			}

			return ResponseEntity.ok().body(listaFuncionariosAux);

		} catch (Exception e) {
			System.out.println("Erro ao listarDepartamentos: " + e);
		}
		return null;
	}

	public ResponseEntity<?> criarFuncionario(Funcionario funcionario) {
		String msg = "";
		try {

			for (Departamento dpto : funcionario.getDepartamentos()) {
				funcionario.setHistoricoDepartamentos(dpto.getDepartamento_name());
			}

			if (funcionarioRepository.save(funcionario) != null) {
				msg = messageSource.getMessage("funcionario.criar", null, LocaleContextHolder.getLocale());
				return ResponseEntity.ok(ResponseUtil.getResponse(msg, "success", null));
			}

		} catch (Exception e) {
			System.out.println("Erro ao criarFuncionario: " + e);
		}

		msg = messageSource.getMessage("funcionario.erro", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));
	}

	public ResponseEntity<?> atualizarFuncionario(@PathVariable(value = "id") Integer id,
			@RequestBody Funcionario funcionario) {
		String msg = "";

		try {

			Funcionario funcionarioAux = funcionarioRepository.findById(id).get();

			funcionarioAux.setFuncionario_name(funcionario.getFuncionario_name());
			
			for (Departamento dpto : funcionario.getDepartamentos()) {
				if (!funcionarioAux.getHistoricoDepartamentos().equals(dpto.getDepartamento_name())) {
					funcionarioAux.setHistoricoDepartamentos(
							funcionarioAux.getHistoricoDepartamentos() + "," + dpto.getDepartamento_name());
				}
			}

			if (funcionarioRepository.save(funcionarioAux) != null) {
				msg = messageSource.getMessage("funcionario.atualizar", new String[] { null },
						LocaleContextHolder.getLocale());
				return ResponseEntity.ok().body(ResponseUtil.getResponse(msg, "success", ""));
			}

		} catch (Exception e) {
			System.out.println("Erro ao atualizar funcionario " + e);
			msg = messageSource.getMessage("funcionario.erro.atualizar", null, LocaleContextHolder.getLocale());
			return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));
		}

		msg = messageSource.getMessage("funcionario.erro.atualizar", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));

	}

	public ResponseEntity<?> excluirFuncionario(@PathVariable(value = "id") Integer id) throws Exception {
		String msg = "";

		try {

			Funcionario funcionario = funcionarioRepository.findById(id).get();
			funcionario.setDeletado(true);

			if (funcionarioRepository.save(funcionario) != null) {
				msg = messageSource.getMessage("funcionario.excluir", new String[] { "" },
						LocaleContextHolder.getLocale());
				return ResponseEntity.ok().body(ResponseUtil.getResponse(msg, "success", ""));
			}

		} catch (Exception e) {
			System.out.println("Erro ao excluir Funcionario " + e);
			msg = messageSource.getMessage("funcionario.erro.excluir", null, LocaleContextHolder.getLocale());
			return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));
		}

		msg = messageSource.getMessage("funcionario.erro.excluir", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));

	}

	public ResponseEntity<?> funcionarioDepartamento(Funcionario funcionario) {
		String msg = "";
		try {

			if (funcionario.getDepartamentos() != null) {
				Departamento dp = null;
				FuncionarioDepartamento dpAux = null;
				for (Departamento d : funcionario.getDepartamentos()) {
					dp = departamentoRepository.findById(d.getDepartamento_id()).get();
					dpAux = new FuncionarioDepartamento();
					dpAux.setFuncionario(funcionario);
					dpAux.setgetDepartamento(dp);
					funcionario.getFuncionarioDepartamentos().add(dpAux);
				}
			}

			if (funcionarioRepository.save(funcionario) != null) {
				msg = messageSource.getMessage("funcionario.vincular", null, LocaleContextHolder.getLocale());
				return ResponseEntity.ok(ResponseUtil.getResponse(msg, "success", null));
			}

		} catch (Exception e) {
			System.out.println("Erro ao funcionarioDepartamento: " + e);
		}

		msg = messageSource.getMessage("funcionario.erro.vincular", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(ResponseUtil.getResponse(msg, "error", null));
	}

}
