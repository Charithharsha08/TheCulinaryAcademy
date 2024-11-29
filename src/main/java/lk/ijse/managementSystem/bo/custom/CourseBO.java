package lk.ijse.managementSystem.bo.custom;

import lk.ijse.managementSystem.bo.SuperBo;
import lk.ijse.managementSystem.dto.CourseDTO;

import java.util.List;

public interface CourseBO extends SuperBo {
    boolean addCourse(CourseDTO courseDTO);

    List<CourseDTO> getAllCourses();


    boolean updateCourse(CourseDTO dto);

    boolean deleteCourse(int id);

    CourseDTO searchCourse(int id);
}
