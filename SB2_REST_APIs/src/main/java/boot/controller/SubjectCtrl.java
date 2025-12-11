package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/subject")
public class SubjectCtrl {

	@PostMapping("/save")
	public String saveSubjects() {
		System.out.println("Subject saved Successfully");
		return "Subject Inserted";
	}

	@GetMapping("/get")
	public String getSubjects() {
		System.out.println("Subject Fetched Successfully");
		return "Subject Fetched";
	}

}
