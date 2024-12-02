package vn.duynguyen.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import vn.duynguyen.dto.request.UserRequestDTO;
import vn.duynguyen.dto.response.UserDetailResponse;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.model.User;
import vn.duynguyen.util.UserStatus;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    User getByUsername(String userName);

    String saveUser(UserRequestDTO request);

    String saveUser(User user);

    void updateUser(String userId, UserRequestDTO request);

    void changeStatus(String userId, UserStatus status);

    void deleteUser(String userId);

    UserDetailResponse getUser(String userId);

    PageResponse<?> getAllUsers(int pageNo, int pageSize);

    List<String> getAllRolesByUserId(String userId);

    User getUserByEmail(String email);
}
