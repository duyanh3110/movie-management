package vn.duynguyen.service;

import vn.duynguyen.dto.request.ServiceTypeDTO;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.dto.response.ServiceTypeResponse;

public interface ServiceTypeService {

    String saveServiceType(ServiceTypeDTO serviceTypeDTO);

    void updateServiceType(String serviceTypeId, ServiceTypeDTO serviceTypeDTO);

    void deleteServiceType(String serviceTypeId);

    ServiceTypeResponse getServiceType(String serviceTypeId);

    PageResponse<?> getAllServiceTypes(int pageNo, int pageSize);
}
