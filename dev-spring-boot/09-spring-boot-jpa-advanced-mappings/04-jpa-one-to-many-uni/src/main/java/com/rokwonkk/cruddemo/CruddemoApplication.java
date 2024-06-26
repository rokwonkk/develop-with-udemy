package com.rokwonkk.cruddemo;

import com.rokwonkk.cruddemo.dao.AppDAO;
import com.rokwonkk.cruddemo.entity.Course;
import com.rokwonkk.cruddemo.entity.Instructor;
import com.rokwonkk.cruddemo.entity.InstructorDetail;
import com.rokwonkk.cruddemo.entity.Review;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

//			createCourseAndReviews(appDAO);

//			retrieveCourseAndReviews(appDAO);

//			deleteCourseAndReviews(appDAO);

		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 12;

		System.out.println("Deleting course id: " + id);

		appDAO.deleteCourseById(id);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and reviews
		int id = 12;
		Course course = appDAO.findCourseAndReviewsByCourseId(id);

		// print the course
		System.out.println("Course: " + course);

		// print the reviews
		System.out.println("Reviews: " + course.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create a course
		Course course = new Course("Pacman - How To Score One Million Points");

		// add some reviews
		course.addReview(new Review("Great course ... loved it!"));
		course.addReview(new Review("Cool course, job well done"));
		course.addReview(new Review("What a dumb course, you are an idiot!"));

		// save the course ... and leverage the cascade all :-)
		System.out.println("Saving course: " + course);
		System.out.println(course);
		System.out.println(course.getReviews());

		appDAO.save(course);

		System.out.println("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {

		int id = 10;
		System.out.println("Deleting course id: " + id);

		appDAO.deleteCourseById(id);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;

		//find the course
		System.out.println("Finding course with id: " + id);
		Course course = appDAO.findCourseById(id);

		//update the course
		System.out.println("Updating course id: " + id);
		course.setTitle("Enjoy Coding Simple Things");

		appDAO.update(course);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int id = 5;

		//find the instructor
		System.out.println("Finding instructor with id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		// update the instructor
		System.out.println("Updating instructor: id" + id);
		instructor.setLastName("wonnik");

		appDAO.update(instructor);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 5;

		//find instructor
		System.out.println("Finding instructor with courses with id: " + id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("instructor: " + instructor);
		System.out.println("the associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 5;

		//find instructor
		System.out.println("Finding instructor with courses with id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Found instructor: " + instructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + id);

		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		System.out.println("Found courses: " + courses);

		// associate the objects
		instructor.setCourses(courses);

		System.out.println("the associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 5;
		System.out.println("Finding instructor with courses with id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Found instructor: " + instructor);
		System.out.println("the associated instructor only: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		//create the instructor
		Instructor instructor = new Instructor("tae", "rink", "rokwonkk@ddns.net" );

		//create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail(
				"https://www.youtube.com/taerink",
				"Game");

		// asscociate the objects
		instructor.setInstructorDetail(instructorDetail);

		// create some courses
		Course Course1 = new Course("Game Development");
		Course Course2 = new Course("Code Development");

		// add courses to instructor
		instructor.add(Course1);
		instructor.add(Course2);

		// save the instructor
		//
		// Note: this will also save the object
		// because of CascadeType.RERSIST
		//
		System.out.println("Saving instructor: " + instructor);
		System.out.println("The courses: " + instructor.getCourses());

		appDAO.save(instructor);

		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 4;
		System.out.println("Deleting instructor detail with id: " + id);

		appDAO.deleteInstructorDetailById(id);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// get the instructor detail object
		int id = 1;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		// print the instructor detail
		System.out.println("Found instructor detail: " + instructorDetail);

		// print the associated instructor
		System.out.println("the associated instructor only: " + instructorDetail.getInstructor());

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {

		int id = 5;
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
