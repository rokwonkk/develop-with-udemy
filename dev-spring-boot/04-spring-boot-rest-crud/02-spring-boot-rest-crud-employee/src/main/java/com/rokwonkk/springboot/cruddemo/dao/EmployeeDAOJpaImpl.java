package com.rokwonkk.springboot.cruddemo.dao;

import com.rokwonkk.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    // define field for entitymanager
    private final EntityManager em;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Employee> findAll() {

        //create a query
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);

        // execute query and get result list return the results
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        // get employee return employee
        return em.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        // save employee return the saveEmployee
        return em.merge(employee);
    }

    @Override
    public void deleteById(int id) {
        //find employee by id
        Employee employee = em.find(Employee.class, id);

        // remove employee
        em.remove(employee);
    }
}
