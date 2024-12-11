package vn.duynguyen.service;

import vn.duynguyen.dto.request.ServiceManagerDTO;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.dto.response.ServiceManagerResponse;

public interface ServiceManagerService {

    String saveServiceManager(ServiceManagerDTO serviceManagerDTO);

    void updateServiceManager(String serviceManagerId, ServiceManagerDTO serviceManagerDTO);

    void deleteServiceManager(String serviceManagerId);

    ServiceManagerResponse getServiceManager(String serviceManagerId);

    PageResponse<?> getAllServiceManagers(int pageNo, int pageSize);
}
