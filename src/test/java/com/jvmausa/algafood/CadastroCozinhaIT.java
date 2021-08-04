package com.jvmausa.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";

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
	public void deveRetornar4Cozinhas_ConsultarCozinhar() {
		given()
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.body("", hasSize(4))
		.body("nome", hasItems("Indiana", "Tailandesa"));
		
	}
	
	@Test
	public void deveRetornar201_CadastrarCozinha() {
		given()
		.body("{ \"nome\": \"Chinesa\" }")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.CREATED.value());
		
	}
	
}