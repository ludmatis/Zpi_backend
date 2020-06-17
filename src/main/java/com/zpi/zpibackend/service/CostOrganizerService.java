package com.zpi.zpibackend.service;

import com.zpi.zpibackend.entity.CostOrganizer;
import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.repository.CostOrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostOrganizerService {

    @Autowired
    private CostOrganizerRepository costOrganizerRepository;
    @Autowired
    private CostElementService costElementService;

    public CostOrganizer add(CostOrganizer costOrganizer){
        return costOrganizerRepository.save(costOrganizer);
    }

    public List<CostOrganizer> getAll(){
        return (List<CostOrganizer>) costOrganizerRepository.findAll();
    }

    public CostOrganizer getById(Integer id){
        return costOrganizerRepository.findById(id).orElse(null);
    }

    public CostOrganizer update(CostOrganizer costOrganizer){
        return costOrganizerRepository.save(costOrganizer);
    }

    public List<CostOrganizer> getByEvent(Event event){
        return costOrganizerRepository.findByEvent(event);
    }

    public void delete(CostOrganizer costOrganizer){
        var costElements = costOrganizer.getCostElements();
        var costElementsWithParents = costElements.stream().filter(costElement -> costElement.getParent()!=null);
        var CostElementsWithoutParents = costElements.stream().filter(toDoListTask -> toDoListTask.getParent()==null);
        costElementsWithParents.forEach(costElement -> costElementService.delete(costElement));
        CostElementsWithoutParents.forEach(costElement -> costElementService.delete(costElement));
        costOrganizerRepository.delete(costOrganizer);
    }
}
