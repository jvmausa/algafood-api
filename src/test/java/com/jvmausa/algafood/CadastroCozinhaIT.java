package com.jvmausa.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jvmausa.algafood.domain.model.Cozinha;
import com.jvmausa.algafood.domain.repository.CozinhaRepository;
import com.jvmausa.algafood.util.DatabaseCleaner;
import com.jvmausa.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private static final int COZINHA_ID_INEXISTENTE = 100;
	
	private Cozinha cozinhaAmericana;
	private int quantidadeCozinhasCadastradas;
	private String jsonCorretoCozinhaChinesa;
	
	//método que será executado antes de cada teste
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		databaseCleaner.clearTables();
		prepararDados();
		
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource("/json/correto/cozinha-chinesa.json");
	}	
	
	@Test
	public void deveRetornar200_ConsultarCozinhar() {
		given()
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.statusCode(HttpStatus.OK.value());
		
	}


	@Test
	public void deveRetornarQuantCertaDeCozinhas_ConsultarCozinhar() {
		given()
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.body("", hasSize(quantidadeCozinhasCadastradas));
		
	}
	
	@Test
	public void deveRetornar201_CadastrarCozinha() {
		given()
		.body(jsonCorretoCozinhaChinesa)
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.CREATED.value());
		
	}
	
	@Test
	public void deveRetornarRespostaEstatusCorretos_ConsultarCozinhaExistente() {
		given()
		.pathParam("id", cozinhaAmericana.getId())
		.accept(ContentType.JSON)
	.when()
		.get("/{id}")
	.then()
		.statusCode(HttpStatus.OK.value())
		.body("nome", equalTo(cozinhaAmericana.getNome()));
		
	}
	
	@Test
	public void deveRetornarstatus404_ConsultarCozinhaInexistente() {
		given()
		.pathParam("id", COZINHA_ID_INEXISTENTE)
		.accept(ContentType.JSON)
	.when()
		.get("/{id}")
	.then()
		.statusCode(HttpStatus.NOT_FOUND.value());
		
	}
	
	
	private void prepararDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaRepository.save(cozinha2);
		
		quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
		
	}
	
	
	
	
}
