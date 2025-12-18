package boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import boot.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	// Custom Queries --> JPQL
	
	
//	@Query(value = "select * from Products", nativeQuery = true) // for SQL query
	
//	@Query("select p from Products p where p.price>=?1 and p.price<=?2") // Index based parameter
	@Query("Select p from Product p where p.price>=:fromPrice and p.price<=:toPrice") // Naming based parameter
	public List<Product> getProdPriceRange(Double fromPrice, Double toPrice); // filter products by price range
	
	@Query("select p from Product p where p.name=:prodName and p.price=:prodPrice")
	public List<Product> getProdByNameAndPrice(String prodName, Double prodPrice); // filter products by name and price
	
	
	// Custom Methods
	// No need of JPQL queries, we have to follow naming Conventions
	// 1. Method name always starts from 'findBy'. (to add the conditions we've to use the fields/states of Entity Object with convention again)
	
	public List<Product> findByNameAndPrice(String name, Double price); // add sorting
	
	public List<Product> findByNameContainingIgnoreCase(String name); 
	
	public List<Product> findByPriceBetween(Double fromPrice, Double toPrice); // add sorting
	
	public List<Product> findByPriceIsLessThanEqual(Double price); // add sorting
	
}
