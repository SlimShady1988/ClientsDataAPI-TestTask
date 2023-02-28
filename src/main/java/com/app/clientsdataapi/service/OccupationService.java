package com.app.clientsdataapi.service;

import com.app.clientsdataapi.entity.Gender;
import com.app.clientsdataapi.entity.Occupation;

import java.util.List;

public interface OccupationService {
    List<Occupation> getOccupations(Gender type);
}
