package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/t1")
	public String getMsg() {
		int a = 10/0;
		return "Error";
	}
	
}
