package boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import boot.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
