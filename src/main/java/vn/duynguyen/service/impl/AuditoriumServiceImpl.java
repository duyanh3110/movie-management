package vn.duynguyen.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.duynguyen.dto.request.AuditoriumRequestDTO;
import vn.duynguyen.dto.response.AuditoriumResponse;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.exception.ResourceNotFoundException;
import vn.duynguyen.model.Auditorium;
import vn.duynguyen.repository.AuditoriumRepository;
import vn.duynguyen.service.AuditoriumService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuditoriumServiceImpl implements AuditoriumService {
    private final AuditoriumRepository auditoriumRepository;

    /**
     * Save new auditorium to DB
     *
     * @param request
     * @return auditoriumId
     */
    @Override
    public String saveAuditorium(AuditoriumRequestDTO request) {
        Auditorium auditorium = Auditorium.builder()
                .name(request.getName())
                .seatNumber(request.getSeatNumber())
                .build();

        auditoriumRepository.save(auditorium);

        return auditorium.getId();
    }

    /**
     * Update auditorium by auditoriumId
     *
     * @param auditoriumId
     * @param request
     */
    @Override
    public void updateAuditorium(String auditoriumId, AuditoriumRequestDTO request) {
        Auditorium auditorium = getAuditoriumById(auditoriumId);
        auditorium.setName(request.getName());
        auditorium.setSeatNumber(request.getSeatNumber());
        auditoriumRepository.save(auditorium);

        log.info("Auditorium has updated successfully, auditoriumId: {}", auditoriumId);
    }

    /**
     * Delete auditorium by auditoriumId
     *
     * @param auditoriumId
     */
    @Override
    public void deleteAuditorium(String auditoriumId) {
        auditoriumRepository.deleteById(auditoriumId);
        log.info("Auditorium has deleted successfully, auditoriumId: {}", auditoriumId);
    }

    /**
     * Get auditorium detail by auditoriumId
     *
     * @param auditoriumId
     * @return
     */
    @Override
    public AuditoriumResponse getAuditorium(String auditoriumId) {
        Auditorium auditorium = getAuditoriumById(auditoriumId);
        return AuditoriumResponse.builder()
                .id(auditorium.getId())
                .name(auditorium.getName())
                .seatNumber(auditorium.getSeatNumber())
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
    public PageResponse<?> getAllAuditoriums(int pageNo, int pageSize) {
        Page<Auditorium> page = auditoriumRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<AuditoriumResponse> list = page.stream().map(auditorium -> AuditoriumResponse.builder()
                        .id(auditorium.getId())
                        .name(auditorium.getName())
                        .seatNumber(auditorium.getSeatNumber())
                        .build())
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .items(list)
                .build();
    }

    private Auditorium getAuditoriumById(String auditoriumId) {
        return auditoriumRepository.findById(auditoriumId).orElseThrow(() -> new ResourceNotFoundException("Auditorium not found"));
    }
}
