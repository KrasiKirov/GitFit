package ca.mcgill.ecse321.gitfit.controller;

import ca.mcgill.ecse321.gitfit.service.SportCenterService;
import ca.mcgill.ecse321.gitfit.dto.SportCenterResponseDto;
import ca.mcgill.ecse321.gitfit.model.SportCenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William Wang (wangwiza)
 * 
 * 
 */
@CrossOrigin(origins = "*")
@RestController
public class SportCenterRestController {

    @Autowired
    private SportCenterService sportCenterService;

    /**
     * Get the sport center
     * @author William Wang (wangwiza)
     * @return
     */
    @GetMapping(value = { "/sportcenter", "/sportcenter." })
    public SportCenterResponseDto getSportCenter() {
        return convertToDto(sportCenterService.getSportCenter());
    }

    /**
     * Update sport center name
     * 
     * @author William Wang (wangwiza)
     * @param name
     * @return
     */
    @PutMapping(value = { "/sportcenter/name", "/sportcenter/name/" })
    public SportCenterResponseDto updateSportCenterName(@RequestParam (name = "name") String name) {
        return convertToDto(sportCenterService.updateSportCenterName(name));
    }


    /**
     * Convert model instance to DTO instance
     * 
     * @author William Wang (wangwiza)
     * @param sportCenter
     * @return
     */
    private SportCenterResponseDto convertToDto(SportCenter sportCenter) {
        if (sportCenter == null) {
            throw new IllegalArgumentException("There is no such sport center!");
        }
        return new SportCenterResponseDto(sportCenter);
    }
}
