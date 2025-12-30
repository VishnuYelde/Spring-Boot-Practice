package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("admin")
public class AdminController {

	@GetMapping("/hi")
	public String sayHi() {
		return "Hii Admin...!";
	}

}
