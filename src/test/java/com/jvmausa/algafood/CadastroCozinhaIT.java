package com.jvmausa.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;

	@Test
	public void deveRetornar200_ConsultarCozinhar() {
		given()
		.basePath("/cozinhas")
		.port(port)
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.statusCode(HttpStatus.OK.value());
		
	}


	@Test
	public void deveRetornar4Cozinhas_ConsultarCozinhar() {
		given()
		.basePath("/cozinhas")
		.port(port)
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.body("", hasSize(4))
		.body("nome", hasItems("Indiana", "Tailandesa"));
		
	}
	
}
