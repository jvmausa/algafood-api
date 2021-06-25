package com.jvmausa.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.domain.model.Estado;
import com.jvmausa.algafood.domain.repository.EstadoRepository;
import com.jvmausa.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@GetMapping()
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Estado buscar(@PathVariable Long id) {
		return cadastroEstado.buscarOuFalhar(id);
		
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
		estado = cadastroEstado.salvar(estado);
		return ResponseEntity.status(HttpStatus.CREATED).body(estado);

	}

	@PutMapping("/{id}")
	public Estado atualizar(@PathVariable Long id, @RequestBody Estado estado) {
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(id);
				
			BeanUtils.copyProperties(estado, estadoAtual, "id");
			
			return cadastroEstado.salvar(estadoAtual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroEstado.excluir(id);
	}

}
