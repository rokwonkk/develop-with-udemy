package com.rokwonkk.cruddemo;

import com.rokwonkk.cruddemo.dao.AppDAO;
import com.rokwonkk.cruddemo.entity.Instructor;
import com.rokwonkk.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createInstructor(appDAO);
//			findInstructor(appDAO);
			deleteInstructor(appDAO);

		};
	}

	private void deleteInstructor(AppDAO appDAO) {

		int id = 2;
		System.out.println("Deleting instructor with id: " + id);

		appDAO.deleteInstructorById(id);

		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {

		int id = 2;
		System.out.println("Finding instructor with id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Found instructor: " + instructor);
		System.out.println("the associated instructor only: " + instructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {

		//create the instructor
		Instructor instructor = new Instructor("rok", "wonkk", "rokwonkk@ddns.net" );

		//create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail(
				"https://www.youtube.com/rokwonkk",
				"coding");

		// asscociate the objects
		instructor.setInstructorDetail(instructorDetail);

		// save the instructor
		//
		// Note: this will also save the details object
		// because of CascadeType.ALL
		//
		System.out.println("Saving instructor: " + instructor);
		appDAO.save(instructor);

		System.out.println("Done!");
	}
}
