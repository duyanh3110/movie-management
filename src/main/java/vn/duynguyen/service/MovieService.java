package vn.duynguyen.service;

import vn.duynguyen.dto.request.MovieRequestDTO;
import vn.duynguyen.dto.response.MovieDetailResponse;
import vn.duynguyen.dto.response.PageResponse;

public interface MovieService {

    String saveMovie(MovieRequestDTO movie);

    void updateMovie(String movieId, MovieRequestDTO movie);

    void deleteMovie(String movieId);

    MovieDetailResponse getMovie(String movieId);

    PageResponse<?> getAllMovies(int pageNo, int pageSize);
}
