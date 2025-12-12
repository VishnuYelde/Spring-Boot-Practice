package boot.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student") // used to give common URL
public class StudentCtrl {

	@PostMapping("/save")
	public String saveStudent() {
		System.out.println("Student Intserted");
		return "Student Saved";
	}

	@GetMapping("/get")
	public String getStudent() {
		System.out.println("Student Fetched Successfully");
		return "Student Fetched";
	}

	@DeleteMapping("/delete")
	public String deleteStudent() {
		System.out.println("Student Delete Successfully");
		return "Student Deleted";
	}

	@PutMapping("/update")
	public String updateStudent() {
		System.out.println("Student Updated Successfully");
		return "Student Updated";
	}
}
