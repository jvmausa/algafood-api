package com.jvmausa.algafood;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
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
		.statusCode(200);
		
	}


}
