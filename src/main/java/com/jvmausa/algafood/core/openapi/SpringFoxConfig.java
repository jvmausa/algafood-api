package com.jvmausa.algafood.core.openapi;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
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
import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.springfox.model.exception.Problem500OpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.CidadesModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.CozinhasModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.EstadosModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.FormasPagamentoModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.GruposModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.LinksModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.PageableModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.PedidosResumoModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.PermissoesModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.ProdutosModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.RestaurantesModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v1.UsuariosModelOpenApi;
import com.jvmausa.algafood.api.springfox.model.v2.CidadesModelV2OpenApi;
import com.jvmausa.algafood.api.springfox.model.v2.CozinhasModelV2OpenApi;
import com.jvmausa.algafood.api.v1.assembler.PermissaoModel;
import com.jvmausa.algafood.api.v1.model.CidadeModel;
import com.jvmausa.algafood.api.v1.model.CozinhaModel;
import com.jvmausa.algafood.api.v1.model.EstadoModel;
import com.jvmausa.algafood.api.v1.model.FormaPagamentoModel;
import com.jvmausa.algafood.api.v1.model.GrupoModel;
import com.jvmausa.algafood.api.v1.model.PedidoResumoModel;
import com.jvmausa.algafood.api.v1.model.ProdutoModel;
import com.jvmausa.algafood.api.v1.model.RestauranteModel;
import com.jvmausa.algafood.api.v1.model.UsuarioModel;
import com.jvmausa.algafood.api.v2.model.CidadeModelV2;
import com.jvmausa.algafood.api.v2.model.CozinhaModelV2;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
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
				.groupName("V1")
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.jvmausa.algafood.api"))
					.paths(PathSelectors.ant("/v1/**"))
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
				.apiInfo(apiInfoV1())
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
	
	@Bean
	public Docket apiDocketV2() {
		var typeResolver = new TypeResolver();

		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V2")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jvmausa.algafood.api"))
				.paths(PathSelectors.ant("/v2/**"))
				.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class), typeResolver.resolve(Problem500OpenApi.class))
				.ignoredParameterTypes(ServletWebRequest.class,
						URL.class, URI.class, URLStreamHandler.class, Resource.class,
						File.class, InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
					    typeResolver.resolve(PagedModel.class, CozinhaModelV2.class),
					    CozinhasModelV2OpenApi.class))
					.alternateTypeRules(AlternateTypeRules.newRule(
					        typeResolver.resolve(CollectionModel.class, CidadeModelV2.class),
					        CidadesModelV2OpenApi.class))
				.apiInfo(apiInfoV2())
				.tags(new Tag("Cidades", "Gerencia as cidades"),
						new Tag("Cozinhas", "Gerencia as cozinhas"));
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
	private ApiInfo apiInfoV1() {
		return new ApiInfoBuilder()
				.title("Alga Food API V1 (Deprecated)")
				.description("API aberta para cliente")
				.version("1")
				.contact(new Contact("Joao", null, "jvmausa@gmail.com"))
				.build();
	}	
	
	private ApiInfo apiInfoV2() {
		return new ApiInfoBuilder()
				.title("Alga Food API V2")
				.description("API aberta para cliente")
				.version("2")
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