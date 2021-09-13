package com.jvmausa.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.model.CozinhaModel;
import com.jvmausa.algafood.api.model.PedidoResumoModel;
import com.jvmausa.algafood.api.openapi.model.CozinhasModelOpenApi;
import com.jvmausa.algafood.api.openapi.model.PageableModelOpenApi;
import com.jvmausa.algafood.api.openapi.model.PedidosResumoModelOpenApi;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer{

	
	@Bean
	public Docket apiDocket(){
		
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.jvmausa.algafood.api"))
//					.paths(PathSelectors.ant("/restaurantes/*"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(ServletWebRequest.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Page.class, CozinhaModel.class), 
						CozinhasModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Page.class, PedidoResumoModel.class), 
						PedidosResumoModelOpenApi.class))
				.apiInfo(apiInfo())
				.tags(new Tag("Cidades", "Gerencia as cidades"),
				        new Tag("Grupos", "Gerencia os grupos de usuários"),
				        new Tag("Cozinhas", "Gerencia as cozinhas"),
				        new Tag("Formas de Pagamento", "Gerencia as formas de pagamento"),
				        new Tag("Pedidos", "Gerencia os pedidos"),
				        new Tag("Restaurantes", "Gerencia os restaurantes"));
	}

	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
				.code(400)
				.message("Requisição inválida (erro do cliente)")
				.responseModel(new ModelRef("Problema"))
				.build(),
				
				new ResponseMessageBuilder()
				.code(500)
				.message("Erro Interno do servidor")
				.responseModel(new ModelRef("Problema"))
				.build()
				);
	}

	private List<ResponseMessage> globalPutResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
				.code(400)
				.message("Requisição inválida (erro do cliente)")
				.responseModel(new ModelRef("Problema"))
				.build(),
				
				new ResponseMessageBuilder()
				.code(500)
				.message("Erro Interno do servidor")
				.build(),
				
				new ResponseMessageBuilder()
				.code(406)
				.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
				.build(),
				
				new ResponseMessageBuilder()
				.code(415)
				.message("Requisição recusada porque o corpo está em um formato não suportado")
				.build()
				);
	}

	private List<ResponseMessage> globalPostResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
				.code(400)
				.message("Requisição inválida (erro do cliente)")
				.responseModel(new ModelRef("Problema"))
				.build(),
				
				new ResponseMessageBuilder()
				.code(500)
				.message("Erro Interno do servidor")
				.responseModel(new ModelRef("Problema"))
				.build(),
				
				new ResponseMessageBuilder()
				.code(406)
				.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
				.build(),
				
				new ResponseMessageBuilder()
				.code(415)
				.message("Requisição recusada porque o corpo está em um formato não suportado")
				.build()
				);
	}

	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
				.code(500)
				.message("Erro Interno do servidor.")
				.build(),
				
				new ResponseMessageBuilder()
				.code(406)
				.message("Recurso não possui representado que poderia ser aceita pelo consumidor.")
				.build()
				);
	}	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Alga Food API")
				.description("API aberta para cliente")
				.version("1.0.0")
				.contact(new Contact("Joao", null, "jvmausa@gmail.com"))
				.build();
	}	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}