package vn.duynguyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.duynguyen.model.Auditorium;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, String> {
}
