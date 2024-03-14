package ca.mcgill.ecse321.gitfit.controller;

import ca.mcgill.ecse321.gitfit.service.SportCenterService;
import ca.mcgill.ecse321.gitfit.dto.SportCenterDto;
import ca.mcgill.ecse321.gitfit.model.SportCenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 
     * @author William Wang (wangwiza)
     * @return
     */
    @GetMapping(value = { "/sportcenter", "/sportcenter/" })
    public SportCenterDto getSportCenter() {
        return convertToDto(sportCenterService.getSportCenter());
    }

    /**
     * Update sport center name
     * 
     * @author William Wang (wangwiza)
     * @param sportCenter
     * @return
     */
    @PutMapping(value = { "/sportcenter/name", "/sportcenter/name/" })
    public SportCenterDto updateSportCenterName(@RequestBody SportCenterDto sportCenter) {
        return convertToDto(sportCenterService.setSportCenterName(sportCenter.getName()));
    }

    /**
     * Update sport center max capacity
     * 
     * @author William Wang (wangwiza)
     * @param sportCenter
     * @return
     */
    @PutMapping(value = { "/sportcenter/capacity", "/sportcenter/capacity/" })
    public SportCenterDto updateSportCenterMaxCapacity(@RequestBody SportCenterDto sportCenter) {
        return convertToDto(sportCenterService.setSportCenterMaxCapacity(sportCenter.getMaxCapacity()));
    }

    /**
     * Update sport center opening hours
     * 
     * @author William Wang (wangwiza)
     * @param sportCenter
     * @return
     */
    @PutMapping(value = { "/sportcenter/hours", "/sportcenter/hours/" })
    public SportCenterDto updateSportCenterHours(@RequestBody SportCenterDto sportCenter) {
        return convertToDto(
                sportCenterService.setOpenHours(sportCenter.getOpeningTime(), sportCenter.getClosingTime()));
    }

    /**
     * Convert model instance to DTO instance
     * 
     * @author William Wang (wangwiza)
     * @param sportCenter
     * @return
     */
    private SportCenterDto convertToDto(SportCenter sportCenter) {
        if (sportCenter == null) {
            throw new IllegalArgumentException("There is no such sport center!");
        }
        return new SportCenterDto(sportCenter);
    }
}
