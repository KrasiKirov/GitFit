package ca.mcgill.ecse321.gitfit.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.gitfit.service.FitnessClassService;
import ca.mcgill.ecse321.gitfit.service.SportCenterService;
import ca.mcgill.ecse321.gitfit.dto.FitnessClassDto;
import ca.mcgill.ecse321.gitfit.model.FitnessClass;

@RestController
public class FitnessClassRestController {
    @Autowired
    private FitnessClassService fitnessClassService;
    private SportCenterService sportCenterService;

    @GetMapping(value = { "/fitnessclasses", "/fitnessclasses/" })
    public List<FitnessClassDto> findAllFitnessClasses() {
        return fitnessClassService.findAllFitnessClasses().stream().map(fc -> convertToDto(fc)).collect(Collectors.toList());
    }

    @GetMapping(value = { "/fitnessclasses/approved", "/fitnessclasses/approved/" })
    public List<FitnessClassDto> findApprovedClasses() {
        return fitnessClassService.findApprovedClasses().stream().map(fc -> convertToDto(fc)).collect(Collectors.toList());
    }

    @GetMapping("/fitnessclasses/{fcid}")
    public FitnessClassDto findFitnessClassById(@PathVariable int fcid) {
        FitnessClass fitnessClass = fitnessClassService.findFitnessClassById(fcid);
        return convertToDto(fitnessClass);
    }

    @GetMapping("/fitnessclasses/{name}")
    public FitnessClassDto findFitnessClassByName(@PathVariable String name) {
        FitnessClass fitnessClass = fitnessClassService.findFitnessClassByName(name);
        return convertToDto(fitnessClass);
    }

    @PostMapping(value = { "/fitnessclasses", "/fitnessclasses/" })
    @ResponseStatus(HttpStatus.CREATED)
    public FitnessClassDto createFitnessClass(@RequestBody FitnessClassDto fitnessClass) {
        FitnessClass createdFitnessClass = fitnessClassService.createFitnessClass(
            fitnessClass.getName(), 
            fitnessClass.getDescription(), 
            fitnessClass.getIsApproved(), 
            sportCenterService.getSportCenter());
        return convertToDto(createdFitnessClass);
    }

    @PutMapping("/fitnessclasses/{name}")
    public FitnessClassDto approveFitnessClass(@PathVariable String name) {
        FitnessClass fitnessClass = fitnessClassService.approveFitnessClass(name);
        return convertToDto(fitnessClass);
    }
    
    private FitnessClassDto convertToDto(FitnessClass fitnessClass) {
        return new FitnessClassDto(fitnessClass.getName(), fitnessClass.getDescription(), fitnessClass.getIsApproved());
    }
}
