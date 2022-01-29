package com.jvmausa.algafood.core.openapi;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.amazonaws.auth.policy.Resource;
import com.ctc.wstx.shaded.msv_core.util.Uri;
import com.fasterxml.classmate.TypeResolver;
import com.jvmausa.algafood.api.assembler.PermissaoModel;
import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.model.CidadeModel;
import com.jvmausa.algafood.api.model.CozinhaModel;
import com.jvmausa.algafood.api.model.EstadoModel;
import com.jvmausa.algafood.api.model.FormaPagamentoModel;
import com.jvmausa.algafood.api.model.GrupoModel;
import com.jvmausa.algafood.api.model.PedidoResumoModel;
import com.jvmausa.algafood.api.model.ProdutoModel;
import com.jvmausa.algafood.api.model.RestauranteModel;
import com.jvmausa.algafood.api.model.UsuarioModel;
import com.jvmausa.algafood.api.springfox.model.CidadesModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.CozinhasModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.EstadosModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.FormasPagamentoModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.GruposModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.LinksModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.PageableModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.PedidosResumoModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.PermissoesModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.ProdutosModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.RestaurantesModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.UsuariosModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.exception.Problem500OpenApi;

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
				.additionalModels(typeResolver.resolve(Problem.class), typeResolver.resolve(Problem500OpenApi.class))
				.ignoredParameterTypes(ServletWebRequest.class, URL.class,Uri.class, 
										URLStreamHandler.class, Resource.class, File.class, InputStream.class) 
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, CozinhaModel.class), 
						CozinhasModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, PedidoResumoModel.class), 
						PedidosResumoModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, CidadeModel.class), 
						CidadesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, EstadoModel.class), 
						EstadosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, FormaPagamentoModel.class), 
						FormasPagamentoModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, GrupoModel.class), 
						GruposModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, PermissaoModel.class), 
						PermissoesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, ProdutoModel.class), 
						ProdutosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, RestauranteModel.class), 
						RestaurantesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, UsuarioModel.class), 
						UsuariosModelOpenApi.class))
				.apiInfo(apiInfo())
				.tags(new Tag("Cidades", "Gerencia as cidades"),
				        new Tag("Grupos", "Gerencia os grupos de usuários"),
				        new Tag("Cozinhas", "Gerencia as cozinhas"),
				        new Tag("Formas de Pagamento", "Gerencia as formas de pagamento"),
				        new Tag("Pedidos", "Gerencia os pedidos"),
				        new Tag("Restaurantes", "Gerencia os restaurantes"),
				        new Tag("Estado", "Gerencia os estados"),
				        new Tag("Produtos", "Gerencia os produtos de restaurantes"),
				        new Tag("Usuários", "Gerencia os usuários"),
				        new Tag("Estatísticas", "Estatísticas da AlgaFood"),
				        new Tag("Permissões", "Gerencia as permissões"));
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
				.responseModel(new ModelRef("Problema500"))
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
				.responseModel(new ModelRef("Problema500"))
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
				.responseModel(new ModelRef("Problema500"))
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
				.responseModel(new ModelRef("Problema500"))
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