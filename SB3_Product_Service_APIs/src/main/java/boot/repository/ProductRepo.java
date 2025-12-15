package boot.repository;

import org.springframework.data.repository.CrudRepository;

import boot.model.Product;

public interface ProductRepo extends CrudRepository<Product, Integer>{

}
