package boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import boot.entity.Product;

@SpringBootApplication
public class Sb12SecurityOAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb12SecurityOAuthApplication.class, args);
		
		// lombok testing
		System.out.println("lombok testing...!");
		Product product = new Product();
		product.setId(101);
		product.setName("Titan Watch");
		product.setDescription("Rare collection watch");
		product.setPrice(50000);
		
		
		System.out.println("Product Info: " + product);
	}

}
