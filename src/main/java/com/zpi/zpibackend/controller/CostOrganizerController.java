package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.CostOrganizer;
import com.zpi.zpibackend.entity.Event;
import com.zpi.zpibackend.entity.dto.CostOrganizerDto;
import com.zpi.zpibackend.service.CostOrganizerService;
import com.zpi.zpibackend.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/costorganizer")
public class CostOrganizerController {

    @Autowired
    private CostOrganizerService costOrganizerService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<CostOrganizerDto> getAll(){
        List<CostOrganizer> costOrganizers = costOrganizerService.getAll();
        return costOrganizers.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @GetMapping("getbyevent/{id}")
    public ResponseEntity getByEventId(@PathVariable Integer id){
        Event event = eventService.getById(id);
        if(event == null){
            return ResponseEntity.badRequest().body("Podany event nie istnieje");
        }
        else{
            List<CostOrganizer> organizers = costOrganizerService.getByEvent(event);
            var organizersDto = organizers.stream().map(this::convertToDto).collect(Collectors.toList());
            if(organizersDto.isEmpty()){
                return ResponseEntity.badRequest().body("Event nie posiada kosztorysu");
            }
            else{
                return new ResponseEntity<>(organizersDto, HttpStatus.OK);
            }
        }
    }

    @PostMapping("/add")
    public ResponseEntity addCostOrganizer(@RequestBody CostOrganizerDto costOrganizerDto){
        CostOrganizer costOrganizer = convertFromDto(costOrganizerDto);
        if(costOrganizerService.add(costOrganizer) == null){
            return ResponseEntity.badRequest().body("Cos poszlo nie tak przy dodawaniu");
        }
        else{
            return new ResponseEntity<>(convertToDto(costOrganizer), HttpStatus.OK);
        }
    }
    @PutMapping("update/{id}")
    public ResponseEntity updateCostOrganizer(@RequestBody CostOrganizerDto costOrganizerDto, @PathVariable Integer id){
        CostOrganizer costOrganizer = costOrganizerService.getById(id);
        costOrganizerDto.setOrganizerid(id);
        if(costOrganizer== null){
            return ResponseEntity.badRequest().body("Podany kosztorys nie istnieje");
        }
        else{
            CostOrganizer updated = costOrganizerService.update(convertFromDto(costOrganizerDto));
            return new ResponseEntity<>(convertToDto(updated), HttpStatus.OK);
        }
    }
    private CostOrganizerDto convertToDto(CostOrganizer costOrganizer){
        return modelMapper.map(costOrganizer, CostOrganizerDto.class);
    }

    //TODO as every convertFromDto
    private CostOrganizer convertFromDto(CostOrganizerDto costOrganizerDto){
        CostOrganizer costOrganizer = modelMapper.map(costOrganizerDto, CostOrganizer.class);
        Event event;
        if(costOrganizer.getEvent()!= null){
            event = eventService.getById(costOrganizerDto.getEvent().getEventid());
            costOrganizer.setEvent(event);
        }
        return  costOrganizer;
    }
}
