package com.rokwonkk.cruddemo.dao;

import com.rokwonkk.cruddemo.entity.Instructor;
import com.rokwonkk.cruddemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

}
