package vn.duynguyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.duynguyen.model.ServiceType;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, String> {
}
