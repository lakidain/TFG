package com.auditorias.springboot.backend;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.auditorias.springboot.backend.model.Auditor;

@MappedTypes(Auditor.class)
@MapperScan({"com.auditorias.springboot.backend.mapper"})
@SpringBootApplication
public class SpringBootBackendApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApplication.class, args);
	}
}
