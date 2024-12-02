package vn.duynguyen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.duynguyen.model.RedisToken;

@Repository
public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
}
