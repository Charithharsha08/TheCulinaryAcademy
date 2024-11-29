package lk.ijse.managementSystem.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.managementSystem.bo.custom.CourseBO;
import lk.ijse.managementSystem.controller.LoginFormController;
import lk.ijse.managementSystem.dao.DAOFactory;
import lk.ijse.managementSystem.dao.custom.CourseDAO;
import lk.ijse.managementSystem.dto.CourseDTO;
import lk.ijse.managementSystem.dto.StudentDTO;
import lk.ijse.managementSystem.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);


    @Override
    public boolean addCourse(CourseDTO courseDTO) {
        try {
            return courseDAO.add(new Course(1,courseDTO.getDescription(),courseDTO.getDuration(),courseDTO.getPrice()));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
            return false;
        }
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        try {
            List<CourseDTO> courseDTOS = new ArrayList<>();
            List<Course> allCourses = courseDAO.getAll();
            for (Course course : allCourses) {
                courseDTOS.add(new CourseDTO(
                        course.getId(),
                        course.getDescription(),
                        course.getDuration(),
                        course.getPrice()
                ));

            }
            return courseDTOS;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
            return null;
        }

    }

    @Override
    public boolean updateCourse(CourseDTO dto) {
        try {
            return courseDAO.update(new Course(dto.getId(),dto.getDescription(),dto.getDuration(),dto.getPrice()));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
            return false;
        }
    }

    @Override
    public boolean deleteCourse(int id) {
        try {
            return courseDAO.delete(String.valueOf(id));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
            return false;
        }
    }

    @Override
    public CourseDTO searchCourse(int id) {
    return null;
    }
}
