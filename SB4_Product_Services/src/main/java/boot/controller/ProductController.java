package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.dto.FilterDTO;
import boot.model.Product;
import boot.services.ProductService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	@PostMapping("/save") //http://localhost:8080/prod/save
	public ResponseEntity<Product> InsertProduct(@Valid @RequestBody Product product) {
		return productService.save(product); // Insert the data Object and returns it.
		// change 200 status code to 201 OK, means new resource is created.
	}

	// Fetch all Data
	@GetMapping("/getall") //http://localhost:8080/prod/getall
	public List<Product> getAllProducts() {
		return productService.findAllRecords();
	}

	// Fetch Data by ID
	@GetMapping("/getbyid") //http://localhost:8080/prod/getbyid?pid=4
	public Product getById(@RequestParam Integer pid) {
		return productService.getById(pid);
	}

	// Delete Record By Id
	@DeleteMapping("/deletebyid/{pid}") //http://localhost:8080/prod/deletebyid/4
	public ResponseEntity<String> delById(@PathVariable Integer pid) {
		return productService.deleteById(pid); // returns the Response Entity Status code with message body
	}

	// Update Product Record
	@PutMapping("/update/{id}") //http://localhost:8080/prod/update/1
	public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		Product updatedProduct = productService.updateProduct(id, product);
		return ResponseEntity.ok().body("Product with Id = "+updatedProduct.getPid()+" is Updated");
	}

	// Pagination API (Used to Distribute records in Single page)
	@GetMapping("/fetchpage") //http://localhost:8080/prod/fetchpage?pageNo=1
	public Page<Product> fetchByPage(@RequestParam Integer pageNo) {
		Page<Product> products = productService.fetchByPage(pageNo);
		return products;
	}

	// Sorting API (Used to sort product based on parameter) order --> asc, desc
	@GetMapping("/sort") //http://localhost:8080/prod/sort?param=name&order=desc
	public List<Product> sortProduct(@RequestParam(required = false, defaultValue = "price") String param, @RequestParam(required = false) String order) {
		return productService.sortingProd(param, order);
	}
	
	// Filter the Products using FilterDTO object
	@GetMapping("/filter")
	public List<Product> filterProd(@Valid @RequestBody FilterDTO filterDTO) {
		List<Product> filterProdsList = productService.filterProd(filterDTO);
		return filterProdsList;
	}
	
	// Filter by Price Range
	@GetMapping("/pricerange")
	public List<Product> getProdByPriceRange(@RequestParam Double fPrice, @RequestParam Double tPrice) {
		List<Product> priceRangeProdList = productService.priceRange(fPrice, tPrice);
		return priceRangeProdList;
	}
	
	// filter by Name and Price 
	@GetMapping("/nameprice")
	public List<Product> getProdByNameAndPrice(@RequestParam String name, @RequestParam Double price) {
		return productService.filerNameAndPrice(name, price);
	}
	
	// filter by less than Price 
	@GetMapping("/lessprice")
	public List<Product> getProdByLessPrice(@RequestParam Double price) {
		return productService.filterLessPrice(price);
	}
	
	// Search by Name
	@GetMapping("/searchbyname")
	public List<Product> searchByName(@RequestParam String name) {
		return productService.searchByName(name);
	}
	
	// Exception Handling
	
	@GetMapping("/excp")
	public String demomsg() {
		String string = null;
		string.charAt(10);

		int a = 10/0;
		
		return "Exception Maybe or Maybe Not";
	}
	
	// Exception handled for Arithmetic expression
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<String> handleException(ArithmeticException ae){
		System.out.println("ArithmeticException Handled");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ae.getMessage());
	}
	
	// Exception handled for NullPointer Exception
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> nullExcepHandled(NullPointerException nException) {
		System.out.println("NullPointerException Handled");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nException.getMessage());
	}
	
	// // Exception handled for All Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> excepHandled(Exception exception) {
		System.out.println("Exception Handled");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
