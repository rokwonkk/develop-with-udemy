package com.rokwonkk.cruddemo.dao;

import com.rokwonkk.cruddemo.entity.Course;
import com.rokwonkk.cruddemo.entity.Instructor;
import com.rokwonkk.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    // define field for entity manager
    private final EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        // retrieve the instructor
        Instructor instructor = entityManager.find(Instructor.class, id);

        // get the courses
        List<Course> courses = instructor.getCourses();

        // break association of all courses for the instructor
        // 모든 강사의 강좌 연관을 끊는다.
        for (Course course : courses) {
            course.setInstructor(null);
        }

        // delete the instructor
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {

            // retrieve the instructor detail
            InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

            // remove the associated object reference
            // break bi-directional link
            //
            instructorDetail.getInstructor().setInstructorDetail(null);

            // delete the instructor detail
            entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                                    "select c from Course c where c.instructor.id= :data", Course.class);
        query.setParameter("data", id);

        //execute query
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                                    "select i from Instructor i "
                                            + "JOIN FETCH i.courses "
                                            + "JOIN FETCH i.instructorDetail "
                                            + "where i.id = :data", Instructor.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {

        // retrieve the course
        Course course = entityManager.find(Course.class, id);

        // delete the course
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                                    "select c from Course c "
                                            + "JOIN FETCH c.reviews "
                                            + "where c.id = :data", Course.class);
        query.setParameter("data", id);

        // execute query

        return query.getSingleResult();

    }
}