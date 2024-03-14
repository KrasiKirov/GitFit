package ca.mcgill.ecse321.gitfit.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.SportCenter;

@Service
public class SportCenterService {

    @Autowired
    private SportCenterRepository sportCenterRepository;

    /**
     * Get presisted sport center
     * 
     * @author William Wang (wangwiza)
     * @param name
     * @return
     */
    @Transactional
    public SportCenter getSportCenter() {
        SportCenter sportCenter = sportCenterRepository.findAll().iterator().next();
        return sportCenter;
    }

    /**
     * Set the name of the sport center
     * 
     * @author William Wang (wangwiza)
     * @param name
     */
    @Transactional
    public SportCenter setSportCenterName(String name) {
        SportCenter sportCenter = sportCenterRepository.findAll().iterator().next();
        sportCenter.setName(name);
        sportCenter = sportCenterRepository.save(sportCenter);
        return sportCenter;
    }

    /**
     * Set the max capacity of the sport center
     * 
     * @author William Wang (wangwiza)
     * @param maxCapacity
     */
    @Transactional
    public SportCenter setSportCenterMaxCapacity(int maxCapacity) {
        if (maxCapacity < 0) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Max capacity cannot be negative");
        }
        SportCenter sportCenter = sportCenterRepository.findAll().iterator().next();
        sportCenter.setMaxCapacity(maxCapacity);
        sportCenter = sportCenterRepository.save(sportCenter);
        return sportCenter;
    }

    /**
     * Set the opening hours of the sport center
     * 
     * @author William Wang (wangwiza)
     * @param newOpeningTime
     * @param newClosingTime
     * @return
     */
    public SportCenter setOpenHours(Time newOpeningTime, Time newClosingTime) {
        if (newOpeningTime == null || newClosingTime == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Opening and closing time cannot be null");
        }
        if (newOpeningTime.after(newClosingTime)) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Opening time cannot be after closing time");
        }
        SportCenter sportCenter = sportCenterRepository.findAll().iterator().next();
        sportCenter.setOpeningTime(newOpeningTime);
        sportCenter.setClosingTime(newClosingTime);
        sportCenter = sportCenterRepository.save(sportCenter);
        return sportCenter;
    }

    /**
     * Get the opening hours of the sport center
     * 
     * @author William Wang (wangwiza)
     * @return
     */
    @Transactional
    public List<Time> getOpenHours() {
        SportCenter sportCenter = sportCenterRepository.findAll().iterator().next();
        List<Time> openingHours = new ArrayList<>();
        openingHours.add(sportCenter.getOpeningTime());
        openingHours.add(sportCenter.getClosingTime());
        return openingHours;
    }
}
