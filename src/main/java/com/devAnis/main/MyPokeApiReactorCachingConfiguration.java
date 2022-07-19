package com.devAnis.main;


import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import reactor.netty.http.client.HttpClient;
import skaro.pokeapi.PokeApiReactorCachingConfiguration;

@Configuration
@Import(PokeApiReactorCachingConfiguration.class)
@EnableCaching
public class MyPokeApiReactorCachingConfiguration {
    @Bean
    public HttpClient httpClient() {
        return HttpClient.create()
                .compress(true)
                .resolver(DefaultAddressResolverGroup.INSTANCE);
    }
}
