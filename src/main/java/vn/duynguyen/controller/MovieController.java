package vn.duynguyen.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.duynguyen.dto.request.MovieRequestDTO;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.dto.response.ResponseData;
import vn.duynguyen.dto.response.ResponseError;
import vn.duynguyen.service.MovieService;

@RestController
@RequestMapping("/movie")
@Validated
@Slf4j
@Tag(name = "Movie Controller")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @Operation(summary = "Add new movie", description = "API create new movie")
    @PostMapping("/")
    public ResponseData<String> addMovie(@Valid @RequestBody MovieRequestDTO movie) {
        log.info("Adding movie: {}", movie);
        try {
            String movieId = movieService.saveMovie(movie);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Movie added successfully", movieId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Add movie failed!");
        }
    }

    @Operation(summary = "Get movie data", description = "API get movie data")
    @GetMapping("/{movieId}")
    public ResponseData<String> getMovie(@PathVariable("movieId") String movieId) {
        log.info("Get movieId: {}", movieId);
        try {
            movieService.getMovie(movieId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Get movie successfully", movieId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Get movie failed!");
        }
    }

    @Operation(summary = "Update movie data", description = "API update movie data")
    @PutMapping("/{movieId}")
    public ResponseData<String> updateMovie(@PathVariable("movieId") String movieId, @Valid @RequestBody MovieRequestDTO movie) {
        log.info("Update movie: {}", movie);
        try {
            movieService.updateMovie(movieId, movie);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Movie added successfully", movieId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update movie failed!");
        }
    }

    @Operation(summary = "Delete movie", description = "API delete movie")
    @DeleteMapping("/{movieId}")
    public ResponseData<String> deleteMovie(@PathVariable("movieId") String movieId) {
        log.info("Delete movieId: {}", movieId);
        try {
            movieService.deleteMovie(movieId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Movie deleted successfully", movieId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Delete movie failed!");
        }
    }

    @Operation(summary = "Get movie list per page", description = "Return movie by pageNo and pageSize")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<PageResponse> getAllMovies(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") @Min(10) int pageSize) {
        log.info("Request getAllMovies");
        return new ResponseData<>(HttpStatus.OK.value(), "movies", movieService.getAllMovies(pageNo, pageSize));
    }
}
