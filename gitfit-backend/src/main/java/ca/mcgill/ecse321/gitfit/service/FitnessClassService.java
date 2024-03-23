package ca.mcgill.ecse321.gitfit.service;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.gitfit.model.FitnessClassApprovalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.gitfit.dao.FitnessClassRepository;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.FitnessClass;
import ca.mcgill.ecse321.gitfit.model.SportCenter;
import jakarta.transaction.Transactional;

@Service
public class FitnessClassService {
    @Autowired
    private FitnessClassRepository fitnessClassRepository;
    @Autowired
    private SportCenterService sportCenterService;
    @Transactional
    public List<FitnessClass> findAllFitnessClasses() {
        return toList(fitnessClassRepository.findAll());
    }

    @Transactional
    public List<FitnessClass> findApprovedClasses() {
        List<FitnessClass> approvedClasses = new ArrayList<FitnessClass>();
        for (FitnessClass fitnessClass : fitnessClassRepository.findAll()) {
            if (fitnessClass.getApprovalStatus()==FitnessClassApprovalStatus.APPROVED) {
                approvedClasses.add(fitnessClass);
            }
        }
        return approvedClasses;
    }

    @Transactional
    public List<FitnessClass> findPendingClasses() {
        List<FitnessClass> pendingClasses = new ArrayList<FitnessClass>();
        for (FitnessClass fitnessClass : fitnessClassRepository.findAll()) {
            if (fitnessClass.getApprovalStatus()==FitnessClassApprovalStatus.PENDING) {
                pendingClasses.add(fitnessClass);
            }
        }
        return pendingClasses;
    }

    @Transactional
    public List<FitnessClass> findRejectedClasses() {
        List<FitnessClass> rejectedClasses = new ArrayList<FitnessClass>();
        for (FitnessClass fitnessClass : fitnessClassRepository.findAll()) {
            if (fitnessClass.getApprovalStatus()==FitnessClassApprovalStatus.REJECTED) {
                rejectedClasses.add(fitnessClass);
            }
        }
        return rejectedClasses;
    }

    @Transactional
    public FitnessClass findFitnessClassById(int id) {
        FitnessClass fitnessClass = fitnessClassRepository.findFitnessClassById(id);
        if (fitnessClass == null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "There is no fitness class with ID " + id + ".");
        }
        return fitnessClass;
    }

    @Transactional
    public FitnessClass findFitnessClassByName(String name) {
        FitnessClass fitnessClass = fitnessClassRepository.findFitnessClassByName(name);
        if (fitnessClass == null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "There is no fitness class called " + name + ".");
        }
        return fitnessClass;
    }

    @Transactional
    public FitnessClass createFitnessClass(String name, String description) {
        if (name == null || description == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Must provide a name and a description.");
        }

        // if there is no existing fitness class with the given name, then create a new fitness class
        try {
            findFitnessClassByName(name);
        } catch (SportCenterException e) {
            FitnessClass toCreate = new FitnessClass(name, description,sportCenterService.getSportCenter());
            return fitnessClassRepository.save(toCreate);
        }

        // otherwise, the fitness class already exists
        throw new SportCenterException(HttpStatus.BAD_REQUEST, "There is already a fitness class called " + name + ".");
    }

//    @Transactional
//    public FitnessClass createFitnessClassOLD(String name, String description, boolean isApproved, SportCenter sportCenter) {
//        if (name == null || description == null) {
//            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Must provide a name and a description.");
//        }
//
//        // if there is no existing fitness class with the given name, then create a new fitness class
//        try {
//            findFitnessClassByName(name);
//        } catch (SportCenterException e) {
//            FitnessClass toCreate = new FitnessClass(name, description, isApproved, sportCenter);
//            return fitnessClassRepository.save(toCreate);
//        }
//
//        // otherwise, the fitness class already exists
//        throw new SportCenterException(HttpStatus.BAD_REQUEST, "There is already a fitness class called " + name + ".");
//    }

    @Transactional
    public FitnessClass approveFitnessClass(String name) {
        if (name == null || name.isEmpty()) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Must provide a name.");
        }
        
        FitnessClass fitnessClass = findFitnessClassByName(name);
        fitnessClass.setApprovalStatus(FitnessClassApprovalStatus.APPROVED);
        return fitnessClassRepository.save(fitnessClass);
    }

    @Transactional
    public FitnessClass pendingFitnessClass(String name) {
        if (name == null || name.isEmpty()) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Must provide a name.");
        }

        FitnessClass fitnessClass = findFitnessClassByName(name);
        fitnessClass.setApprovalStatus(FitnessClassApprovalStatus.PENDING);
        return fitnessClassRepository.save(fitnessClass);
    }

    @Transactional
    public FitnessClass rejectFitnessClass(String name) {
        if (name == null || name.isEmpty()) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Must provide a name.");
        }

        FitnessClass fitnessClass = findFitnessClassByName(name);
        fitnessClass.setApprovalStatus(FitnessClassApprovalStatus.REJECTED);
        return fitnessClassRepository.save(fitnessClass);
    }

    @Transactional
    public FitnessClass updateFitnessClass(String name, String description) {
        if (name == null || name.isEmpty() || description == null || description.isEmpty()) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Must provide a name and a description.");
        }

        FitnessClass fitnessClass = findFitnessClassByName(name);
        fitnessClass.setDescription(description);
        return fitnessClassRepository.save(fitnessClass);
    }

    @Transactional
    public void deleteRejectedFitnessClasses() {
        for (FitnessClass fitnessClass : fitnessClassRepository.findAll()) {
            if (fitnessClass.getApprovalStatus()==FitnessClassApprovalStatus.REJECTED) {
                fitnessClassRepository.delete(fitnessClass);
            }
        }
    }

    @Transactional
    public void deleteFitnessClass(String name) {
        if (name == null || name.isEmpty()) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Must provide a name.");
        }

        FitnessClass fitnessClass = findFitnessClassByName(name);
        fitnessClassRepository.delete(fitnessClass);
    }

    private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
    
}