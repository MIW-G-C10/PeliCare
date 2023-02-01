package com.makeitworkch10.pacemakers.pelicare.service;

import com.makeitworkch10.pacemakers.pelicare.model.CareCircle;
import com.makeitworkch10.pacemakers.pelicare.model.Task;
import com.makeitworkch10.pacemakers.pelicare.repository.CareCircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Paul Moonen
 * <p>
 * p.c.c.moonen@gmail.com
 * <p>
 * Service layer for the CareCircle entity
 */
@Service
@RequiredArgsConstructor
public class CareCircleService {

    private final CareCircleRepository careCircleRepository;

    public List<CareCircle> findAllCareCircles() {
        return careCircleRepository.findAll();
    }

    public CareCircle findCareCircle( Long id ) {
        return careCircleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carecircle not found"));
    }
}
