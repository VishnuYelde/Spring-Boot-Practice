package boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import boot.model.Product;

@RepositoryRestResource(path = "products")
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
