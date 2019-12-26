package com.auditorias.springboot.backend;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.auditorias.springboot.backend.model.Auditor;

@MappedTypes(Auditor.class)
@MapperScan({"com.auditorias.springboot.backend.mapper"})
@SpringBootApplication
public class SpringBootBackendApplication implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApplication.class, args);
	}
	
	@Override
	public void run(String ... args) throws Exception{
		String password="12345";
		
		for(int i=0; i < 4 ; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
		}
	}

}
