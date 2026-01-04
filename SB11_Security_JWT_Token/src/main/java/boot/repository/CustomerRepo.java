package boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import boot.entity.Customer;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByEmail(String email);
}
