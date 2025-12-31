package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.dto.LoginDTO;
import boot.dto.RegisterDTO;
import boot.entity.Customer;
import boot.repository.CustomerRepo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/cust")
public class CustomerController {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/greet")
	public String greeting() {
		return "Namaskasr...!";
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerApi(@RequestBody RegisterDTO registerDTO) {
		Optional<Customer> optional = customerRepo.findByEmail(registerDTO.getEmail());
		
		if (optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account Already Present");
		}
		
		Customer customer = new Customer();
		customer.setName(registerDTO.getName());
		customer.setEmail(registerDTO.getEmail());
		customer.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		
		customerRepo.save(customer);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Account Registered");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginApi(@RequestBody LoginDTO loginDTO) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
			
			Authentication authenticate = authenticationManager.authenticate(token);
			if (authenticate.isAuthenticated()) {
				return ResponseEntity.status(HttpStatus.OK).body("Logged in Successfully");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
	}
	
	
	
}
