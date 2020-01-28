package com.auditorias.springboot.backend.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/api/**").permitAll()
		.antMatchers(HttpMethod.PUT,"/api/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/**").permitAll()
		/*
		.antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
		.antMatchers(HttpMethod.POST, "/api/audit").permitAll()
		.antMatchers(HttpMethod.POST, "/api/cita").permitAll()
		.antMatchers(HttpMethod.POST, "/api/cita/uploads").permitAll()
		.antMatchers(HttpMethod.POST, "/api/type").permitAll()
		.antMatchers(HttpMethod.POST, "/api/assets").permitAll()
		.antMatchers(HttpMethod.POST, "/api/empresas").permitAll()
		.antMatchers(HttpMethod.POST, "/api/message").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/usuario/{id}").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/usuarioPassword/{id}").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/usuarioCompany/{id}").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/usuarioEnable/{id}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/assets").permitAll()
		.antMatchers(HttpMethod.GET,"/api/empleados/{name_company}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/empresasAudited").permitAll()
		.antMatchers(HttpMethod.GET,"/api/empresasAuditing").permitAll()
		.antMatchers(HttpMethod.GET,"/api/audit/{id}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/auditTypes").permitAll()
		.antMatchers(HttpMethod.GET,"/api//allCitas/{id}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/auditCitas/{id}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/uploads/img/{nombreFoto:.+}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/message/{id}").permitAll()
		*/
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter> (new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
}
