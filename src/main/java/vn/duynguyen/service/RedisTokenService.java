package vn.duynguyen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.duynguyen.exception.InvalidDataException;
import vn.duynguyen.model.RedisToken;
import vn.duynguyen.repository.RedisTokenRepository;

@Service
@RequiredArgsConstructor
public class RedisTokenService {
    private final RedisTokenRepository redisTokenRepository;

    public void save(RedisToken token) {
        redisTokenRepository.save(token);
    }

    public void remove(String id) {
        isExists(id);
        redisTokenRepository.deleteById(id);
    }

    public boolean isExists(String id) {
        if (!redisTokenRepository.existsById(id)) {
            throw new InvalidDataException("Token not exists");
        }
        return true;
    }
}
