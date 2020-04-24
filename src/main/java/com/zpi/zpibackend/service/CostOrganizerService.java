package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.CostOrganizer;
import com.zpi.zpibackend.repository.CostOrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostOrganizerService {

    @Autowired
    private CostOrganizerRepository costOrganizerRepository;

    public CostOrganizer add(CostOrganizer costOrganizer){
        return costOrganizerRepository.save(costOrganizer);
    }

    public List<CostOrganizer> getAll(){
        return (List<CostOrganizer>) costOrganizerRepository.findAll();
    }
}