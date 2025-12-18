package boot.services;

import java.beans.Beans;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import boot.dao.ProductDAO;
import boot.dto.FilterDTO;
import boot.model.Product;
import boot.repository.ProductRepo;

// Only Business Logic will present here

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductRepo productRepo;

	// to insert the record/Data
	public Product save(Product product) {
		return productDAO.save(product);
	}

	// to fetch data by id
	public Product getById(Integer pid) {
		return productDAO.getById(pid);
	}

	// to Update the Data/Record
	public Product updatProduct(Integer pid, Product product) {

		Product dbPrevProduct = productDAO.getById(pid);
		if (dbPrevProduct.getName() != null) {
			dbPrevProduct.setName(product.getName());
		}
		if (dbPrevProduct.getPrice() != null) {
			dbPrevProduct.setPrice(product.getPrice());
		}
		if (dbPrevProduct.getDiscription() != null) {
			dbPrevProduct.setDiscription(product.getDiscription());
		}
		if (dbPrevProduct.getColor() != null) {
			dbPrevProduct.setColor(product.getColor());
		}

		Product updatedProd = productDAO.save(dbPrevProduct);

		return updatedProd;
	}

	// to Delete record by Id
	public String deleteById(Integer pid) {
		Product dbPrevProduct = productDAO.getById(pid);
		productDAO.delete(dbPrevProduct);

		return "Product Delete Successfully";
	}

	// to Fetch all records
	public List<Product> findAllRecords() {
		return productRepo.findAll();
	}

	// Pagination list of products in single page
	public Page<Product> fetchByPage(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 15);
		Page<Product> pageProd = productRepo.findAll(pageable);
		return pageProd;
	}

	// Sorting the products in order --> asc & desc
	// parameter means column name
	public List<Product> sortingProd(String param, String order) {
		if (order != null && order.equalsIgnoreCase("desc")) {
			return productRepo.findAll(Sort.by(param).descending());// By default its ascending order
		}
		return productRepo.findAll(Sort.by(param).ascending());
	}
	
	// Filter the products based on parameters
	public List<Product> filterProd(FilterDTO filterDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(filterDTO, product);
		
		Example<Product> exampleProd = Example.of(product);
		List<Product> filterProdList = productRepo.findAll(exampleProd);
		return filterProdList;
	}
	
	
}
