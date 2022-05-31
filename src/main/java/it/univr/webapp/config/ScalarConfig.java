package it.univr.webapp.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ScalarConfig {
    @Bean
    public GraphQLScalarType date(){
        return ExtendedScalars.Date;
    }
    @Bean
    public GraphQLScalarType graphQLBigDecimal(){
        return ExtendedScalars.GraphQLBigDecimal;
    }
}
