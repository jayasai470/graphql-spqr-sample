package com.graphql.spqr;

import io.leangen.graphql.ExtensionProvider;
import io.leangen.graphql.GeneratorConfiguration;
import io.leangen.graphql.generator.mapping.TypeMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpqrConfiguration {
	@Bean
	public ExtensionProvider<GeneratorConfiguration, TypeMapper> pageableInputField() {
		return (config, defaults) -> defaults.prepend(new GeoJsonSpqrMapper());
	}
}