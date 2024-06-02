package com.rokwonkk.springboot.cruddemo.service;

import com.rokwonkk.springboot.cruddemo.dao.EmployeeDAO;
import com.rokwonkk.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO dao;

    public EmployeeServiceImpl(EmployeeDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Employee> findAll() {
        return dao.findAll();
    }

    @Override
    public Employee findById(int id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return dao.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        dao.deleteById(id);
    }
}
