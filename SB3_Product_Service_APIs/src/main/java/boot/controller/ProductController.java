package boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import boot.model.Product;
import boot.repository.ProductRepo;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/prod")
public class ProductController {

	// ---------------------introduction to How to send the data from client to Server--------------------------------------
	
	@GetMapping("/data")
	public String getProductData(@RequestParam Integer pid, @RequestParam String name) {
		// to check API, you have to give the Key and Value to the URL in Postman API in Params tab.
		// the API method we created & there parameters name are EXACTLY same as the KEY in the Params tab.

		System.out.println("Product ID: " + pid);
		System.out.println("Name : " + name);
		return "Product Id: " + pid + " Name: " + name;
	}

	@GetMapping("/path/{name}/{price}") // (Query String) send the data using URL path --> /path/S24_Ultra/95000.0
	public String getPathData(@PathVariable(name = "name") String ProductName, @PathVariable double price) {
		System.out.println("Product Name: " + ProductName);
		System.out.println("Product Price: " + price);
		return "Product Name: " + ProductName + " Product Price: " + price;
	}

	@GetMapping("/head") // Sent the data using Headers Data will not get Exposed.
	public String getHeadData(@RequestHeader String token) { // Only Sensitive Data should be send using headers.
		System.out.println("Token Key: " + token);
		return "Header Data: Token --> " + token;
	}

	@GetMapping("/body") // Send the JSON data using Body(raw data and JSON, XML, etc)
	public Product getBodyData(@RequestBody Product product) {
		System.out.println(product);
		return product;
	}
	//-----------------------------------------------------------------------------------------------------------------------------
	
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
