package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.model.Product;
import boot.services.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

// Only APIs Will present here

@RestController
@RequestMapping("/prod")
public class ProductController {

	@Autowired	
	private ProductService productService;
	
	// Inserting Product Records in Table
	@PostMapping("/save")
	public Product InsertProduct(@RequestBody Product product) {
		return productService.save(product); // Insert the data Object and returns it.
	}
	
	// Fetch all Data
	@GetMapping("/getall")
	public List<Product> getAllProducts() {
		return productService.findAllRecords();
	}
	
	//Fetch Data by ID
	@GetMapping("/getbyid")
	public Product getById(@RequestParam Integer pid) {
		return productService.getById(pid);
	}
	
	// Delete Record By Id
	@DeleteMapping("/deletebyid/{pid}")
	public String delById(@PathVariable Integer pid) {
		return productService.deleteById(pid);
	}
	
	// Update Product Record
	@PutMapping("/update/{id}")
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		return productService.updatProduct(id, product);
	}
	
}
