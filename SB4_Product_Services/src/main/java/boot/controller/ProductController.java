package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.dto.FilterDTO;
import boot.model.Product;
import boot.services.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	@PostMapping("/save") //http://localhost:8080/prod/save
	public Product InsertProduct(@RequestBody Product product) {
		return productService.save(product); // Insert the data Object and returns it.
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
	public String delById(@PathVariable Integer pid) {
		return productService.deleteById(pid);
	}

	// Update Product Record
	@PutMapping("/update/{id}") //http://localhost:8080/prod/update/1
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		return productService.updatProduct(id, product);
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
	public List<Product> filterProd(@RequestBody FilterDTO filterDTO) {
		List<Product> filterProdsList = productService.filterProd(filterDTO);
		return filterProdsList;
	}
	
	// Filter by Price Range
	@GetMapping("/pricerange")
	public List<Product> getProdByPriceRange(@RequestParam Double fprice, Double tPrice) {
		List<Product> priceRangeProdList = productService.priceRange(fprice, tPrice);
		return priceRangeProdList;
	}
	
	// filter by Name and Price 
	@GetMapping("/nameprice")
	public List<Product> getProdByNameAndPrice(@RequestParam String name, Double price) {
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
	
	
	
	
	
	
	
	
	
	
	
}
