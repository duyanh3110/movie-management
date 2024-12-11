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
import vn.duynguyen.dto.request.ServiceManagerDTO;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.dto.response.ResponseData;
import vn.duynguyen.dto.response.ResponseError;
import vn.duynguyen.service.ServiceManagerService;

@RestController
@RequestMapping("/service-manager")
@Validated
@Slf4j
@Tag(name = "Service Manager Controller")
@RequiredArgsConstructor
public class ServiceManagerController {
    private final ServiceManagerService serviceManagerService;

    @Operation(summary = "Add new service manager", description = "API create new service manager")
    @PostMapping("/")
    public ResponseData<String> addServiceManager(@Valid @RequestBody ServiceManagerDTO serviceManager) {
        log.info("Adding service manager: {}", serviceManager);
        try {
            String serviceManagerId = serviceManagerService.saveServiceManager(serviceManager);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Service manager added successfully", serviceManagerId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Add service manager failed!");
        }
    }

    @Operation(summary = "Get service manager data", description = "API get service manager data")
    @GetMapping("/{serviceManagerId}")
    public ResponseData<String> getServiceManager(@PathVariable("serviceManagerId") String serviceManagerId) {
        log.info("Get serviceManagerId: {}", serviceManagerId);
        try {
            serviceManagerService.getServiceManager(serviceManagerId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Get service manager successfully", serviceManagerId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Get service manager failed!");
        }
    }

    @Operation(summary = "Update service manager data", description = "API update service manager data")
    @PutMapping("/{serviceManagerId}")
    public ResponseData<String> updateServiceManager(@PathVariable("serviceManagerId") String serviceManagerId, @Valid @RequestBody ServiceManagerDTO serviceManager) {
        log.info("Update service manager: {}", serviceManager);
        try {
            serviceManagerService.updateServiceManager(serviceManagerId, serviceManager);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Service manager added successfully", serviceManagerId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update service manager failed!");
        }
    }

    @Operation(summary = "Delete service manager", description = "API delete service manager")
    @DeleteMapping("/{serviceManagerId}")
    public ResponseData<String> deleteServiceManager(@PathVariable("serviceManagerId") String serviceManagerId) {
        log.info("Delete serviceManagerId: {}", serviceManagerId);
        try {
            serviceManagerService.deleteServiceManager(serviceManagerId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Service manager deleted successfully", serviceManagerId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Delete service manager failed!");
        }
    }

    @Operation(summary = "Get service manager list per page", description = "Return service manager by pageNo and pageSize")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<PageResponse> getAllServiceManagers(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") @Min(10) int pageSize) {
        log.info("Request getAllServiceManagers");
        return new ResponseData<>(HttpStatus.OK.value(), "serviceManagers", serviceManagerService.getAllServiceManagers(pageNo, pageSize));
    }
}
