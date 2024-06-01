package com.rokwonkk.cruddemo.dao;

import com.rokwonkk.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private final EntityManager em;

    // inject entity manager using constructor injection
    // 생성자가 하나일땐 autowired 생략가능.
    @Autowired
    public StudentDAOImpl(EntityManager em) {
        this.em = em;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        em.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        //find( entity class , primary key )
        return em.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s order by lastName desc", Student.class);

        // return query results
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName2) {

        // create query
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s WHERE s.lastName = :lastName1", Student.class);

        // set query parameters
        // 첫번째 파라미터 키 값이 쿼리 부분에서의 lastName1으로 들어가고, 두번째 파라미터인 value 값이 매개변수로 들어간다.
        query.setParameter("lastName1", lastName2);

        //return query results
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        // retrieve the student
        Student student = em.find(Student.class, id);

        // delete the student
        em.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        return em.createQuery("DELETE FROM Student").executeUpdate();
    }
}




















