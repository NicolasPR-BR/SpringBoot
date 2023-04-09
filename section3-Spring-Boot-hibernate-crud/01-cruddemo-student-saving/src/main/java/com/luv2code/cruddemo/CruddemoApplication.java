package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	//Defines a command line application 
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

		return runner -> {
			Student student = new Student("Luli", "Lee", "lulilee@music.com");

			studentDAO.save(student);

			int id = student.getId();
			student = null;
			System.out.println("Saved student with id: " + id);

			student = studentDAO.findById(id);
			System.out.println(student);

		};
	}

	private void readStudent(StudentDAO studentDAO){
		Student student = studentDAO.findById(1);
		System.out.println(student);

	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		List<Student> studentList = new ArrayList<>();
		Student tmpStudent = new Student("Kanami", "Tono", "kanamiTono@bandmaid.com");
		Student tmpStudent1 = new Student("Mincho", "Mincho", "minchoMincho@bandmaid.com");
		Student tmpStudent2 = new Student("Akane", "Hirose", "mikokobato@bandmaid.com");

		studentList.add(new Student("Kanami", "Tono", "kanamiTono@bandmaid.com"));
		studentList.add(new Student("Mincho", "Mincho", "minchoMincho@bandmaid.com"));
		studentList.add(new Student("Akane", "Hirose", "mikokobato@bandmaid.com"));

		//Save these objects

		System.out.println("Saving the students...");

		studentList.forEach(student -> {
			studentDAO.save(student);
			System.out.println("Saved student id " + student.getId());
		});
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student tmpStudent = new Student("Miku", "Kobato", "mikokobato@bandmaid.com");

		System.out.println("Saving the student");

		studentDAO.save(tmpStudent);

		System.out.println("ID of the saved student " + tmpStudent.getId());


	}


}







