package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/prod")
public class ProductController {

	@GetMapping("/data")
	public String getProductData(@RequestParam Integer pid, @RequestParam String name) {
		System.out.println("Product ID: " + pid);
		System.out.println("Name : " + name);
		return "Product Id: " + pid + " Name: " + name;
	}

}
