package com.jvmausa.algafood.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.jvmausa.algafood.domain.model.Cozinha;
import com.jvmausa.algafood.domain.repository.CozinhaRepository;
import com.jvmausa.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@GetMapping
	public List<Cozinha> listar() {

		return cozinhaRepository.findAll();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {

		Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		// return ResponseEntity.status(HttpStatus.OK).body(cozinha); << MAIS USADO
		// QUANDO HÁ CONDICIONAIS QUE RETORNAM HttpStatus
		// return ResponseEntity.ok(cozinha);

		// HttpHeaders headers = new HttpHeaders();
		// headers.add(HttpHeaders.LOCATION, "http://api.algafood.local:8080/cozinhas");
		// return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();

		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		}

		return ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cozinha cozinha) {

		cozinha = cadastroCozinha.salvar(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {

		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id); // usado para consultar

		// verificação se cozinha existe
		if (cozinhaAtual.isPresent()) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id"); // entre "" é o parâmetro que deve ser ignorado
																			// na
			// cópia
			Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());
			return ResponseEntity.ok(cozinhaSalva);
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCozinha.excluir(id);
	}

}
