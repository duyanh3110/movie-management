package vn.duynguyen.service;

import vn.duynguyen.dto.request.AuditoriumRequestDTO;
import vn.duynguyen.dto.response.AuditoriumResponse;
import vn.duynguyen.dto.response.PageResponse;

public interface AuditoriumService {

    String saveAuditorium(AuditoriumRequestDTO auditorium);

    void updateAuditorium(String auditoriumId,AuditoriumRequestDTO auditorium);

    void deleteAuditorium(String auditoriumId);

    AuditoriumResponse getAuditorium(String auditoriumId);

    PageResponse<?> getAllAuditoriums(int pageNo, int pageSize);
}
