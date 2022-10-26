package com.example.fileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class FileUploadApplication {

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver
				= new CommonsMultipartResolver();
		resolver.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
		resolver.setMaxUploadSize(52428800L); //50MB
		resolver.setMaxUploadSizePerFile(52428800L); //50MB

		return resolver;
	}


	public static void main(String[] args) {
		SpringApplication.run(FileUploadApplication.class, args);
	}

}
