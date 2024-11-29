package lk.ijse.managementSystem.bo.custom;

import lk.ijse.managementSystem.bo.SuperBo;
import lk.ijse.managementSystem.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBo {

    List<UserDTO> getAllUsers();

    boolean addUser(UserDTO userDTO);
    UserDTO searchUser(String username);
    boolean updateUser(UserDTO userDTO);
    boolean removeUser(String username);
}
