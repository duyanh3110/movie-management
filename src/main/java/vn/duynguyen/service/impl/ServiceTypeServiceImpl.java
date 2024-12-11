package vn.duynguyen.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.duynguyen.dto.request.ServiceTypeDTO;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.dto.response.ServiceTypeResponse;
import vn.duynguyen.exception.ResourceNotFoundException;
import vn.duynguyen.model.ServiceType;
import vn.duynguyen.repository.ServiceTypeRepository;
import vn.duynguyen.service.ServiceTypeService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceTypeServiceImpl implements ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;

    /**
     * Save new service type to DB
     *
     * @param request
     * @return serviceTypeId
     */
    @Override
    public String saveServiceType(ServiceTypeDTO request) {
        ServiceType serviceType = ServiceType.builder()
                .name(request.getName())
                .build();

        serviceTypeRepository.save(serviceType);

        return serviceType.getId();
    }

    /**
     * Update user by serviceTypeId
     *
     * @param serviceTypeId
     * @param request
     */
    @Override
    public void updateServiceType(String serviceTypeId, ServiceTypeDTO request) {
        ServiceType serviceType = getServiceTypeById(serviceTypeId);
        serviceType.setName(request.getName());
        serviceTypeRepository.save(serviceType);

        log.info("Service Type has updated successfully, serviceTypeId: {}", serviceTypeId);
    }

    /**
     * Delete service type by serviceTypeId
     *
     * @param serviceTypeId
     */
    @Override
    public void deleteServiceType(String serviceTypeId) {
        serviceTypeRepository.deleteById(serviceTypeId);
        log.info("Service Type has deleted successfully, serviceTypeId: {}", serviceTypeId);
    }

    /**
     * Get service type detail by serviceTypeId
     *
     * @param serviceTypeId
     * @return
     */
    @Override
    public ServiceTypeResponse getServiceType(String serviceTypeId) {
        ServiceType serviceType = getServiceTypeById(serviceTypeId);
        return ServiceTypeResponse.builder()
                .name(serviceType.getName())
                .build();
    }

    /**
     * Get all service type per pageNo and pageSize
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResponse<?> getAllServiceTypes(int pageNo, int pageSize) {
        Page<ServiceType> page = serviceTypeRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<ServiceTypeResponse> list = page.stream().map(serviceType -> ServiceTypeResponse.builder().name(serviceType.getName()).build()).toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .items(list)
                .build();
    }

    private ServiceType getServiceTypeById(String id) {
        return serviceTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Service Type not found"));
    }
}
