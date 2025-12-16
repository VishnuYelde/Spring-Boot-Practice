package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.model.Product;
import boot.repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

// Only APIs Will present here

@RestController
@RequestMapping("/prod")
public class ProductController {

	@Autowired	
	private ProductRepo productRepo;
	
	// Inserting data in Table
	@PostMapping("/save")
	public Product InsertProduct(@RequestBody Product product) {
		Product prod = productRepo.save(product); // Insert the data Object and returns it.
		
		return prod;
	}
	
	// Fetch all Data
	@GetMapping("/getall")
	public Iterable<Product> getAllProducts() {
		Iterable<Product> pIterable = productRepo.findAll();
		return pIterable;
	}
	
	//Fetch Data by ID
	@GetMapping("/getbyid")
	public Product getById(@RequestParam Integer pid) {
		
//		Optional<Product> prodID = productRepo.findById(pid);
		
// there are 3 ways to write this business logic 
		// 1. If-else block
		
//		if (prodID.isPresent()) {
//			Product product = prodID.get();
//			return product;
//		}else {
//			System.out.println("Product Id Not Found");
//			return null;
//		}

		
		// 2. Lambda Expression
//		Product pro = prodID.orElseThrow(() -> new RuntimeException("Product Not Found"));
//		return pro;
		
		
		
		// 3. Direct in Return Statement
		return productRepo.findById(pid).orElseThrow(() -> new RuntimeException("Product Not Found"));
		
	}
	
	// Delete Record By Id
	@DeleteMapping("/delbyid")
	public String delById() {
		
		return new String();
	}
	
	
	
}
