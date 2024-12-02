package vn.duynguyen.service;

import org.springframework.stereotype.Service;
import vn.duynguyen.exception.ResourceNotFoundException;
import vn.duynguyen.model.Token;
import vn.duynguyen.repository.TokenRepository;

import java.util.Optional;

@Service
public record TokenService(TokenRepository tokenRepository) {

    /**
     * Get token by username
     *
     * @param username
     * @return token
     */
    public Token getByUsername(String username) {
        return tokenRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Not found token"));
    }

    /**
     * Save token to DB
     *
     * @param token
     * @return
     */
    public String save(Token token) {
        Optional<Token> optional = tokenRepository.findByUsername(token.getUsername());
        if (optional.isEmpty()) {
            tokenRepository.save(token);
            return token.getId();
        } else {
            Token t = optional.get();
            t.setAccessToken(token.getAccessToken());
            t.setRefreshToken(token.getRefreshToken());
            tokenRepository.save(t);
            return t.getId();
        }
    }

    /**
     * Delete token by username
     *
     * @param username
     */
    public void delete(String username) {
        Token token = getByUsername(username);
        tokenRepository.delete(token);
    }
}
