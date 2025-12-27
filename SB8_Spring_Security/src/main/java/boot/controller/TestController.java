package boot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestController {

	@GetMapping("/reg")
	public String reg() {
		return "Registered";
	}

	@GetMapping("/greet")
	public String greet() {
		return "Namaskar";
	}

	@GetMapping("/msg")
	public String msg() {
		return "Have a great Future";
	}

}
