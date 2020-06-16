package com.zpi.zpibackend.controller;

import com.zpi.zpibackend.entity.CostElement;
import com.zpi.zpibackend.entity.CostOrganizer;
import com.zpi.zpibackend.entity.dto.CostElementDto;
import com.zpi.zpibackend.service.CostElementService;
import com.zpi.zpibackend.service.CostOrganizerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin()
@RestController
@RequestMapping("/costelement")
public class CostElementController {

    @Autowired
    private CostElementService costElementService;
    @Autowired
    private CostOrganizerService costOrganizerService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<CostElementDto> getAll(){
        List<CostElement> costElements = costElementService.getAll();
        return costElements.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @GetMapping("getbycostorganizer/{id}")
    public ResponseEntity getByCostOrganizer(@PathVariable Integer id){
        CostOrganizer costOrganizer = costOrganizerService.getById(id);
        if(costOrganizer == null){
            return ResponseEntity.badRequest().body("Podany kosztorys nie istnieje");
        }
        else {
            List<CostElement> costElements = costElementService.getByCostOrganizer(costOrganizer);
            List<CostElementDto> costElementDtos = costElements.stream().map(this::convertToDto).collect(Collectors.toList());
            if(costElementDtos.isEmpty()){
                return ResponseEntity.badRequest().body("Brak elementów kosztorysu");
            }
            else return new ResponseEntity<>(costElementDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/getbyparent/{id}")
    public ResponseEntity getByParent(@PathVariable Integer id){
        CostElement costElement = costElementService.getById(id);
        if(costElement == null){
            return ResponseEntity.badRequest().body("Podany kosztorys- rodzic nie istnieje");
        }
        else{
            List<CostElement> costElements = costElementService.getByParent(costElement);
            List<CostElementDto> costElementDtos = costElements.stream().map(this::convertToDto).collect(Collectors.toList());
            if(costElementDtos.isEmpty()){
                return ResponseEntity.badRequest().body("Brak dzieci kosztorysu");
            }
            else
                return new ResponseEntity<>(costElementDtos, HttpStatus.OK);
        }
    }
    @PostMapping("/add")
    public ResponseEntity addCostElement(@RequestBody CostElementDto costElementDto){
        CostElement costElement = convertFromDto(costElementDto);
        if(costElementService.add(costElement) == null){
            return ResponseEntity.badRequest().body("Cos poszlo nie tak przy dodawaniu");
        }
        else return new ResponseEntity<>(convertToDto(costElement), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCostElement(@RequestBody CostElementDto costElementDto, @PathVariable Integer id){
        CostElement costElement = costElementService.getById(id);
        costElementDto.setCostelementid(id);
        if(costElement == null){
            return ResponseEntity.badRequest().body("Podany element kosztorysu nie istnieje");
        }
        else{
            CostElement updated = costElementService.update(convertFromDto(costElementDto));
            return new ResponseEntity<>(convertToDto(updated), HttpStatus.OK);
        }
    }
    private CostElementDto convertToDto(CostElement costElement){
        return modelMapper.map(costElement, CostElementDto.class);
    }
    //TODO as every convertFromDto
    private CostElement convertFromDto(CostElementDto costElementDto){
        CostElement costElement = modelMapper.map(costElementDto, CostElement.class);
        CostOrganizer costOrganizer;
        CostElement parent;
        if(costElementDto.getCostOrganizer() != null){
            costOrganizer = costOrganizerService.getById(costElementDto.getCostOrganizer().getOrganizerid());
            costElement.setCostOrganizer(costOrganizer);
        }
        if(costElementDto.getParent() !=null){
            parent = costElementService.getById(costElementDto.getParent().getCostelementid());
            costElement.setParent(parent);
        }
        return costElement;
    }
}
