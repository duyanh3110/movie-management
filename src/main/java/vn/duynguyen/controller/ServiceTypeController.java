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
import vn.duynguyen.dto.request.ServiceTypeDTO;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.dto.response.ResponseData;
import vn.duynguyen.dto.response.ResponseError;
import vn.duynguyen.service.ServiceTypeService;

@RestController
@RequestMapping("/service-type")
@Validated
@Slf4j
@Tag(name = "Service Type Controller")
@RequiredArgsConstructor
public class ServiceTypeController {
    private final ServiceTypeService serviceTypeService;

    @Operation(summary = "Add new service type", description = "API create new service type")
    @PostMapping("/")
    public ResponseData<String> addServiceType(@Valid @RequestBody ServiceTypeDTO serviceType) {
        log.info("Adding service type: {}", serviceType);
        try {
            String serviceTypeId = serviceTypeService.saveServiceType(serviceType);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Service type added successfully", serviceTypeId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Add service type failed!");
        }
    }

    @Operation(summary = "Get service type data", description = "API get service type data")
    @GetMapping("/{serviceTypeId}")
    public ResponseData<String> getServiceType(@PathVariable("serviceTypeId") String serviceTypeId) {
        log.info("Get serviceTypeId: {}", serviceTypeId);
        try {
            serviceTypeService.getServiceType(serviceTypeId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Get service type successfully", serviceTypeId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Get service type failed!");
        }
    }

    @Operation(summary = "Update service type data", description = "API update service type data")
    @PutMapping("/{serviceTypeId}")
    public ResponseData<String> updateServiceType(@PathVariable("serviceTypeId") String serviceTypeId, @Valid @RequestBody ServiceTypeDTO serviceType) {
        log.info("Update service type: {}", serviceType);
        try {
            serviceTypeService.updateServiceType(serviceTypeId, serviceType);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Service type added successfully", serviceTypeId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update service type failed!");
        }
    }

    @Operation(summary = "Delete service type", description = "API delete service type")
    @DeleteMapping("/{serviceTypeId}")
    public ResponseData<String> deleteServiceType(@PathVariable("serviceTypeId") String serviceTypeId) {
        log.info("Delete serviceTypeId: {}", serviceTypeId);
        try {
            serviceTypeService.deleteServiceType(serviceTypeId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Service type deleted successfully", serviceTypeId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Delete service type failed!");
        }
    }

    @Operation(summary = "Get service type list per page", description = "Return service type by pageNo and pageSize")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<PageResponse> getAllServiceTypes(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") @Min(10) int pageSize) {
        log.info("Request getAllServiceTypes");
        return new ResponseData<>(HttpStatus.OK.value(), "movies", serviceTypeService.getAllServiceTypes(pageNo, pageSize));
    }
}
