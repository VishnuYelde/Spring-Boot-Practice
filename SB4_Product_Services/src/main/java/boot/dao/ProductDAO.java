package boot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import boot.model.Product;
import boot.repository.ProductRepo;

// Only Persistence Logic will present here

@Repository
public class ProductDAO {

	@Autowired
	private ProductRepo productRepo;
	
	// to Insert Data
	public Product save(Product product) {
		return productRepo.save(product);
	}
	
	// To Fetch_By_Id data
	public Product getById(Integer pid) {
//		Optional<Product> optProd = productRepo.findById(pid);
		
		// 1. Using If-Else
		
//		if (optProd.isPresent()) {
//			Product product = optProd.get();
//			return product;
//		} else {
//			System.out.println("Product Not Found");
//			return null;
//		}
		
		// 2. Using orElseThrow()
		
//		Product product = optProd.orElseThrow(() -> new RuntimeException("Product Not Found"));
//		return product;
		
		// 3. direct in return statement 
		
		return productRepo.findById(pid).orElseThrow(() -> new RuntimeException("Product Not Found"));
	}
	
	
	// To Delete record entity
	public void delete(Product product) {
		productRepo.delete(product);
	}
	
}
