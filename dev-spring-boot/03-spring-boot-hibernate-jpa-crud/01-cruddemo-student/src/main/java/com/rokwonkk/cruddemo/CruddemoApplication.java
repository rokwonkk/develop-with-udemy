package com.rokwonkk.cruddemo;

import com.rokwonkk.cruddemo.dao.StudentDAO;
import com.rokwonkk.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			//readStudent(studentDAO);

			//queryForStudents(studentDAO);

			//queryForStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);

			//deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {

		int studentId = 6;
		System.out.println("Deleting student id: " + studentId);
		studentDAO.delete(studentId);

	}

	private void updateStudent(StudentDAO studentDAO) {
		//retrieve student based on the id: primary key
		int studentId = 5;
		System.out.println("Get student with ID " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change first name to "su"
		System.out.println("Update student ...");
		myStudent.setFirstName("su");

		// update the student
		studentDAO.update(myStudent);

		// display the updated student
		System.out.println("Updated student: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {

		//get a list of students
		List<Student> students = studentDAO.findByLastName("won");

		//display list of students
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		//get a list of students
		List<Student> students = studentDAO.findAll();

		//display list of students
		for (Student student : students) {
			System.out.println(student);
		}

	}

	private void readStudent(StudentDAO studentDAO) {

		// create a student object
		System.out.println("Creating new student object ...");
		Student student = new Student("su", "yn", "rokwon@gmail.com");

		// save the student
		System.out.println("Saving the student ...");
		studentDAO.save(student);

		//display id of the saved student
		int id = student.getId();
		System.out.println("Saved student with id: " + id);

		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + id);
		Student myStudent = studentDAO.findById(id);

		// display student
		System.out.println("Found student with id: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// create multiple students
		System.out.println("Creating new student objects ...");
		Student student1 = new Student("rok1","won1","rokwon1@gmail.com");
		Student student2 = new Student("rok2","won2","rokwon2@gmail.com");
		Student student3 = new Student("rok3","won3","rokwon3@gmail.com");

		// save the student objects
		System.out.println("Saving the students ...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);

	}

	private void createStudent(StudentDAO studentDAO) {
		//create the student object
		System.out.println("Creating new student object ...");
		Student student = new Student("rok","won","rokwon@gmail.com");

		//save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(student);

		//display id of the saved student
		System.out.println("Saved student. Generated id: " + student.getId());
	}
}











