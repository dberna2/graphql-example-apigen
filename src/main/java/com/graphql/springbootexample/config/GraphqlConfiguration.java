package com.graphql.springbootexample.config;

import com.graphql.springbootexample.persistence.model.BookEntity;
import com.graphql.springbootexample.persistence.repository.BookRepository;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.inject.Provider;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ComponentScan("com.graphql")
@Configuration
public class GraphqlConfiguration {

	/** Nombre de la Root Query para exponerlo */
	private static final String QUERY_ROOT = "QueryRoot";

	/** Nombre de la mutación de los coches */
	private static final String LIBRARY_MUTATION = "Mutation";

	/**
	 * Mapa con los tipos disponibles para el schema
	 *
	 * @param typeList
	 * @return
	 */
	@Bean
	public Map<String, GraphQLType> graphqlTypeMap(List<Provider<? extends GraphQLType>> typeList) {
		Map<String, GraphQLType> types = getGraphqlTypes(typeList);
		return types;
	}

	/**
	 * Integración con el SpringBoot Starter para Graphql, necesita la localización del esquema ya que no se va a usar
	 * las anotaciones para las declaraciones
	 *
	 * @param typeList
	 * @return
	 * @throws ClassNotFoundException
	 */
	@Bean
	public graphql.schema.GraphQLSchema graphQLSchemaLocator(List<Provider<? extends GraphQLType>> typeList)
			throws ClassNotFoundException {
		return generateSchema(typeList);
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer configurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * Generamos el schema del Graphql
	 *
	 * @param typeList
	 * @return
	 */
	private GraphQLSchema generateSchema(List<Provider<? extends GraphQLType>> typeList) {
		Map<String, GraphQLType> types = getGraphqlTypes(typeList);
		GraphQLObjectType queryType = (GraphQLObjectType) types.get(QUERY_ROOT);
		GraphQLObjectType libraryMutationType = (GraphQLObjectType) types.get(LIBRARY_MUTATION);

		GraphQLSchema schema = GraphQLSchema.newSchema()
				.query(queryType)
				.mutation(libraryMutationType)
				.build(new HashSet<>(types.values()));
		return schema;
	}

	/**
	 * Obtenemos los tipos componentes de nuestro esquema a modo de map para localizar los elementos que deseemos
	 *
	 * @param typeList
	 * @return
	 */
	private Map<String, GraphQLType> getGraphqlTypes(List<Provider<? extends GraphQLType>> typeList) {
		Map<String, GraphQLType> types = typeList.stream().map(Provider::get)
				.collect(Collectors.toMap(GraphQLType::getName, Function.identity()));
		return types;
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository) {
		return (args) -> Stream.of(
                new BookEntity(UUID.randomUUID().toString(),"Java", Date.from(Instant.now())),
				new BookEntity(UUID.randomUUID().toString(),"Python", Date.from(Instant.now())),
				new BookEntity(UUID.randomUUID().toString(),"PHP", Date.from(Instant.now())),
				new BookEntity(UUID.randomUUID().toString(),"C#", Date.from(Instant.now())),
				new BookEntity(UUID.randomUUID().toString(),"C++", Date.from(Instant.now()))
        ).forEach( (book) -> bookRepository.save(book));
	}
}
