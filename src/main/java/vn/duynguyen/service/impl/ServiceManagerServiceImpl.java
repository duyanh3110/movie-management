package vn.duynguyen.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.duynguyen.dto.request.ServiceManagerDTO;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.dto.response.ServiceManagerResponse;
import vn.duynguyen.exception.ResourceNotFoundException;
import vn.duynguyen.model.ServiceManager;
import vn.duynguyen.repository.ServiceManagerRepository;
import vn.duynguyen.service.ServiceManagerService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceManagerServiceImpl implements ServiceManagerService {
    private final ServiceManagerRepository serviceManagerRepository;

    /**
     * Save new service manager to DB
     *
     * @param request
     * @return movieId
     */
    @Override
    public String saveServiceManager(ServiceManagerDTO request) {
        ServiceManager serviceManager = ServiceManager.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();

        serviceManagerRepository.save(serviceManager);

        return serviceManager.getId();
    }

    /**
     * Update service manager by serviceManagerId
     *
     * @param serviceManagerId
     * @param request
     */
    @Override
    public void updateServiceManager(String serviceManagerId, ServiceManagerDTO request) {
        ServiceManager serviceManager = getServiceManagerById(serviceManagerId);
        serviceManager.setName(request.getName());
        serviceManager.setPrice(request.getPrice());
        serviceManagerRepository.save(serviceManager);

        log.info("Service manager has updated successfully, serviceManagerId: {}", serviceManagerId);
    }

    /**
     * Delete service manager by serviceManagerId
     *
     * @param serviceManagerId
     */
    @Override
    public void deleteServiceManager(String serviceManagerId) {
        serviceManagerRepository.deleteById(serviceManagerId);
        log.info("Service manager has deleted successfully, serviceManagerId: {}", serviceManagerId);
    }

    /**
     * Get service manager detail by serviceManagerId
     *
     * @param serviceManagerId
     * @return
     */
    @Override
    public ServiceManagerResponse getServiceManager(String serviceManagerId) {
        ServiceManager serviceManager = getServiceManagerById(serviceManagerId);
        return ServiceManagerResponse.builder()
                .id(serviceManager.getId())
                .name(serviceManager.getName())
                .price(serviceManager.getPrice())
                .build();
    }

    /**
     * Get all service manager per pageNo and pageSize
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResponse<?> getAllServiceManagers(int pageNo, int pageSize) {
        Page<ServiceManager> page = serviceManagerRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<ServiceManagerResponse> list = page.stream().map(serviceManager -> ServiceManagerResponse.builder()
                    .id(serviceManager.getId())
                    .name(serviceManager.getName())
                    .price(serviceManager.getPrice())
                    .build())
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .items(list)
                .build();
    }

    private ServiceManager getServiceManagerById(String id) {
        return serviceManagerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Service manager not found"));
    }
}
