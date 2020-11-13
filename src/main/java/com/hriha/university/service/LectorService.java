package com.hriha.university.service;

import com.hriha.university.entity.Lector;
import com.hriha.university.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectorService {
    @Autowired
    private LectorRepository lectorRepository;

    public List<Lector> findAll() {
        return lectorRepository.findAll();
    }

    public void save(Lector lector) {
        if (lector != null) {
            lectorRepository.save(lector);
        }
    }
}
