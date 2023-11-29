package com.prontoc.prontoc;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@SpringBootApplication
public class ProntocApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProntocApplication.class, args);
	}

	@Component
	static class MyComponent {
		@Value("${server.port}")
		private int serverPort;

		@Autowired
		private RequestMappingHandlerMapping requestMappingHandlerMapping;
		@PostConstruct
		public void printServerPort() {
			try {
				System.out.println("Server is running on port: " + serverPort);

				Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
				System.out.println("List of @GetMapping endpoints:");

				map.forEach((info, method) -> {
					if (method.getMethod().isAnnotationPresent(GetMapping.class)) {
						GetMapping getMapping = method.getMethodAnnotation(GetMapping.class);
						if (getMapping != null && getMapping.value().length > 0) {
							System.out.println("http://localhost:" + serverPort + getMapping.value()[0]);
						} else {
							System.err.println("Warning: @GetMapping annotation without a value");
						}
					}
				});
			} catch (Exception e) {
				System.err.println("Error in printServerPort: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
