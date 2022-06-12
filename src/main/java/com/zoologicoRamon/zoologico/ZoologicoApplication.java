package com.zoologicoRamon.zoologico;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.zoologicoRamon.zoologico.authentication.JWTAuthorizationFilter;

@SpringBootApplication
public class ZoologicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZoologicoApplication.class, args);
	}

	@Configuration
	public class SecurityConfiguration {
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
					.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
					.antMatchers(HttpMethod.GET, "/animal").permitAll()
					.antMatchers(HttpMethod.GET, "/animal/{id}").permitAll()
					.antMatchers(HttpMethod.GET, "/cuidador").permitAll()
					.antMatchers(HttpMethod.GET, "/cuidador/{id}").permitAll()
					.antMatchers(HttpMethod.GET, "/distribucion").permitAll()
					.antMatchers(HttpMethod.GET, "/distribucion/{id}").permitAll()
					.antMatchers(HttpMethod.GET, "/ecosistema").permitAll()
					.antMatchers(HttpMethod.GET, "/ecosistema/{id}").permitAll()
					.antMatchers(HttpMethod.GET, "/especie").permitAll()
					.antMatchers(HttpMethod.GET, "/especie/{id}").permitAll()
					.antMatchers(HttpMethod.GET, "/rel-cuidador").permitAll()
					.antMatchers(HttpMethod.GET, "/rel-cuidador/{id_cuidador}/{id_animal}").permitAll()
					.antMatchers(HttpMethod.GET, "/rel-distribucion").permitAll()
					.antMatchers(HttpMethod.GET, "/rel-distribucion/{id_distribucion}/{id_animal}").permitAll()
					.antMatchers(HttpMethod.GET, "/rel-tipo").permitAll()
					.antMatchers(HttpMethod.GET, "/rel-tipo/{id_tipo}/{id_animal}").permitAll()
					.antMatchers(HttpMethod.GET, "/tipo").permitAll()
					.antMatchers(HttpMethod.GET, "/tipo/{id}").permitAll()
					.antMatchers(HttpMethod.GET, "/zona").permitAll()
					.antMatchers(HttpMethod.GET, "/zona/{id}").permitAll()
					.anyRequest().authenticated();
			return http.build();
		}

		@Bean
		CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration cc = new CorsConfiguration();
			cc.setAllowedHeaders(
					Arrays.asList("Origin,Accept", "X-Requested-With", "Content-Type", "Access-Control-Allow-Origin",
							"Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization"));
			cc.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
			cc.setAllowedOrigins(Arrays.asList("/*"));
			cc.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
			cc.addAllowedOrigin("http://localhost:4200");
			cc.setMaxAge(Duration.ZERO);
			cc.setAllowCredentials(Boolean.TRUE);
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", cc);
			return source;
		}
	}

}
