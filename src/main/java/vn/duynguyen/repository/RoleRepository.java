package vn.duynguyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.duynguyen.model.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = "select r from Role r inner join UserHasRole ur on r.id = ur.user.id where ur.user.id=:userId")
    List<Role> findAllByUserId(String userId);
}
