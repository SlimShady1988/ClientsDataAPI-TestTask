package com.app.clientsdataapi.service;

import com.app.clientsdataapi.entity.Gender;
import com.app.clientsdataapi.entity.Occupation;
import com.app.clientsdataapi.repository.OccupationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccupationServiceImpl implements OccupationService{

    private final OccupationRepository repository;

    public OccupationServiceImpl(OccupationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Occupation> getOccupations(Gender type) {
       return repository.findOccupationsByType(type);
    }
}
