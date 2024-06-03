package com.rokwonkk.springboot.cruddemo.service;

import com.rokwonkk.springboot.cruddemo.dao.EmployeeRepository;
import com.rokwonkk.springboot.cruddemo.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    // 'Employee' 객체를 찾기 위한 메서드 정의, 'id' 값을 파라미터로 받음
    @Override
    public Employee findById(int id) {
        // 'repository'의 'findById' 메서드를 호출하여 ID에 해당하는 'Employee' 객체를 검색
        // 결과는 'Optional<Employee>' 타입으로 반환됨
        // Optional은 결과가 null일 가능성을 안전하게 처리하기 위해 사용됨
        Optional<Employee> result = repository.findById(id);

        // 'employee' 라는 이름의 Employee 타입 변수를 선언하고 초기값으로 null을 할당함
        Employee employee = null;

        // 'result'가 비어있지 않은지 확인
        if(result.isPresent()) {
            // 'result'에서 실제 'Employee' 객체를 추출하여 'employee' 변수에 할당함
            employee = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id: " + id);
        }

        // 최종적으로 찾은 'Employee' 객체 또는 null을 반환함
        // 객체가 존재하지 않는 경우 null이 반환됨.
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
