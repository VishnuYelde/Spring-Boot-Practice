package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.model.Product;
import boot.repository.ProductRepo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

// Only APIs Will present here

@RestController
@RequestMapping("/prod")
public class ProductController {

	@Autowired
	private ProductRepo productRepo;

	// Inserting Product Records in Table
	@PostMapping("/save") // http://localhost:8080/prod/save
	public ResponseEntity<Product> InsertProduct(@RequestBody Product product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productRepo.save(product)); // Insert the data Object and
																							// returns it.
		// change 200 status code to 201 OK, means new resource is created.
	}

	// Fetch all Products
	@GetMapping("/getall") // http://localhost:8080/prod/getall
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.status(HttpStatus.OK).body(productRepo.findAll());
	}

	// Fetch Data by ID
	@GetMapping("/getbyid") // http://localhost:8080/prod/getbyid?pid=4
	public ResponseEntity<Product> getById(@RequestParam Integer pid) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(productRepo.findById(pid).orElseThrow(() -> new RuntimeException("Product Not Found")));
	}

	// Delete Record By Id
	@DeleteMapping("/deletebyid/{pid}") // http://localhost:8080/prod/deletebyid/4
	public ResponseEntity<String> delById(@PathVariable Integer pid) {
		productRepo.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully"); // returns the Response Entity Status
																					// code with message body
	}

	// Update Product Record
//	@PutMapping("/update/{id}") //http://localhost:8080/prod/update/1
//	public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
//		Product updatedProduct = productService.updateProduct(id, product);
//		return ResponseEntity.ok().body("Product with Id = "+updatedProduct.getPid()+" is Updated");
//	}

	// Exception handled for All Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> excepHandled(Exception exception) {
		System.out.println("Exception Handled: "+exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

}
