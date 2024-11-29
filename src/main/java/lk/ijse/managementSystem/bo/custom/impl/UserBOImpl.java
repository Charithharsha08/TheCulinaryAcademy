package lk.ijse.managementSystem.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.managementSystem.bo.custom.UserBO;
import lk.ijse.managementSystem.dao.DAOFactory;
import lk.ijse.managementSystem.dao.custom.UserDAO;
import lk.ijse.managementSystem.dto.UserDTO;
import lk.ijse.managementSystem.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        try {
            List<User> allUsers =userDAO.getAll();
           for (User user : allUsers) {
               userDTOS.add(new UserDTO(
                       user.getUserName(),
                       user.getPassword(),
                       user.getJobRole()));
           }
        } catch (Exception e) {
        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return userDTOS;
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
return false;
    }

    @Override
    public UserDTO searchUser(String username) {
        return null;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
return false;
    }

    @Override
    public boolean removeUser(String username) {
        try {

            return userDAO.delete(username);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"This user is in use and cannot be deleted").show();
            return false;
        }
    }
}
