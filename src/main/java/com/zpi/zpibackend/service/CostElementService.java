package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.CostElement;
import com.zpi.zpibackend.entity.CostOrganizer;
import com.zpi.zpibackend.repository.CostElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostElementService {

    @Autowired
    private CostElementRepository costElementRepository;

    public CostElement add(CostElement costElement){
        return costElementRepository.save(costElement);
    }

    public List<CostElement> getAll(){
        return (List<CostElement>) costElementRepository.findAll();
    }

    public CostElement getById(Integer id){
        return costElementRepository.findById(id).orElse(null);
    }

    public CostElement update(CostElement costElement){
        return costElementRepository.save(costElement);
    }

    public List<CostElement> getByCostOrganizer(CostOrganizer costOrganizer){
        return costElementRepository.findByCostOrganizer(costOrganizer);
    }
    public List<CostElement> getByParent(CostElement costElement){
        return costElementRepository.findByParent(costElement);
    }
}
