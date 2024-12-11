package vn.duynguyen.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.duynguyen.dto.request.MovieRequestDTO;
import vn.duynguyen.dto.response.MovieDetailResponse;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.exception.ResourceNotFoundException;
import vn.duynguyen.model.Movie;
import vn.duynguyen.repository.MovieRepository;
import vn.duynguyen.service.MovieService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    /**
     * Save new movie to DB
     *
     * @param request
     * @return movieId
     */
    @Override
    public String saveMovie(MovieRequestDTO request) {
        Movie movie = Movie.builder()
                .title(request.getTitle())
                .director(request.getDirector())
                .casts(request.getCasts())
                .description(request.getDescription())
                .duration(request.getDuration())
                .kind(request.getKind())
                .build();

        movieRepository.save(movie);
        
        return movie.getId();
    }

    /**
     * Update movie by movieId
     *
     * @param movieId
     * @param request
     */
    @Override
    public void updateMovie(String movieId, MovieRequestDTO request) {
        Movie movie = getMovieById(movieId);
        movie.setTitle(request.getTitle());
        movie.setDirector(request.getDirector());
        movie.setCasts(request.getCasts());
        movie.setDescription(request.getDescription());
        movie.setDuration(request.getDuration());
        movie.setKind(request.getKind());
        movieRepository.save(movie);

        log.info("Movie has updated successfully, movieId: {}", movieId);
    }

    /**
     * Delete movie by movieId
     *
     * @param movieId
     */
    @Override
    public void deleteMovie(String movieId) {
        movieRepository.deleteById(movieId);
        log.info("Movie has deleted successfully, movieId: {}", movieId);
    }

    /**
     * Get movie detail by movieId
     *
     * @param movieId
     * @return
     */
    @Override
    public MovieDetailResponse getMovie(String movieId) {
        Movie movie = getMovieById(movieId);
        return MovieDetailResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .casts(movie.getCasts())
                .description(movie.getDescription())
                .duration(movie.getDuration())
                .kind(movie.getKind())
                .build();
    }

    /**
     * Get all movie per pageNo and pageSize
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResponse<?> getAllMovies(int pageNo, int pageSize) {
        Page<Movie> page = movieRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<MovieDetailResponse> list = page.stream().map(movie -> MovieDetailResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .casts(movie.getCasts())
                .description(movie.getDescription())
                .duration(movie.getDuration())
                .kind(movie.getKind())
                .build())
            .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .items(list)
                .build();
    }

    private Movie getMovieById(String movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
    }
}
