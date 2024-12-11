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
import vn.duynguyen.dto.request.AuditoriumRequestDTO;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.dto.response.ResponseData;
import vn.duynguyen.dto.response.ResponseError;
import vn.duynguyen.service.AuditoriumService;

@RestController
@RequestMapping("/auditorium")
@Validated
@Slf4j
@Tag(name = "Auditorium Controller")
@RequiredArgsConstructor
public class AuditoriumController {
    private final AuditoriumService auditoriumService;

    @Operation(summary = "Add new auditorium", description = "API create new auditorium")
    @PostMapping("/")
    public ResponseData<String> addAuditorium(@Valid @RequestBody AuditoriumRequestDTO auditorium) {
        log.info("Adding auditorium: {}", auditorium);
        try {
            String auditoriumId = auditoriumService.saveAuditorium(auditorium);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Auditorium added successfully", auditoriumId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Add auditorium failed!");
        }
    }

    @Operation(summary = "Get auditorium data", description = "API get auditorium data")
    @GetMapping("/{auditoriumId}")
    public ResponseData<String> getAuditorium(@PathVariable("auditoriumId") String auditoriumId) {
        log.info("Get auditoriumId: {}", auditoriumId);
        try {
            auditoriumService.getAuditorium(auditoriumId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Get auditorium successfully", auditoriumId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Get auditorium failed!");
        }
    }

    @Operation(summary = "Update auditorium data", description = "API update auditorium data")
    @PutMapping("/{auditoriumId}")
    public ResponseData<String> updateAuditorium(@PathVariable("auditoriumId") String auditoriumId, @Valid @RequestBody AuditoriumRequestDTO auditorium) {
        log.info("Update auditorium: {}", auditorium);
        try {
            auditoriumService.updateAuditorium(auditoriumId, auditorium);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Auditorium added successfully", auditoriumId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update auditorium failed!");
        }
    }

    @Operation(summary = "Delete auditorium", description = "API delete auditorium")
    @DeleteMapping("/{auditoriumId}")
    public ResponseData<String> deleteAuditorium(@PathVariable("auditoriumId") String auditoriumId) {
        log.info("Delete auditoriumId: {}", auditoriumId);
        try {
            auditoriumService.deleteAuditorium(auditoriumId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Auditorium deleted successfully", auditoriumId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Delete auditorium failed!");
        }
    }

    @Operation(summary = "Get auditorium list per page", description = "Return auditorium by pageNo and pageSize")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<PageResponse> getAllAuditoriums(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") @Min(10) int pageSize) {
        log.info("Request getAllAuditoriums");
        return new ResponseData<>(HttpStatus.OK.value(), "auditoriums", auditoriumService.getAllAuditoriums(pageNo, pageSize));
    }
}
