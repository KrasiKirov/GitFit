package ca.mcgill.ecse321.gitfit.service;

import java.util.ArrayList;
import java.util.List;

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
    private FitnessClassRepository fitnessClassRepo;

    @Transactional
    public List<FitnessClass> findAllFitnessClasses() {
        return toList(fitnessClassRepo.findAll());
    }

    @Transactional
    public List<FitnessClass> findApprovedClasses() {
        List<FitnessClass> approvedClasses = new ArrayList<FitnessClass>();
        for (FitnessClass fitnessClass : fitnessClassRepo.findAll()) {
            if (fitnessClass.isIsApproved()) {
                approvedClasses.add(fitnessClass);
            }
        }
        return approvedClasses;
    }

    @Transactional
    public FitnessClass findFitnessClassById(int id) {
        FitnessClass fitnessClass = fitnessClassRepo.findFitnessClassById(id);
        if (fitnessClass == null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "There is no fitness class with ID " + id + ".");
        }
        return fitnessClass;
    }

    @Transactional
    public FitnessClass findFitnessClassByName(String name) {
        FitnessClass fitnessClass = fitnessClassRepo.findFitnessClassByName(name);
        if (fitnessClass == null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "There is no fitness class called " + name + ".");
        }
        return fitnessClass;
    }

    @Transactional
    public FitnessClass createFitnessClass(String name, String description, boolean isApproved, SportCenter sportCenter) {
        if (name == null || description == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Must provide a name and a description.");
        }
        
        // if there is no existing fitness class with the given name, then create a new fitness class
        try {
            findFitnessClassByName(name);
        } catch (SportCenterException e) {
            FitnessClass toCreate = new FitnessClass(name, description, isApproved, sportCenter);
            return fitnessClassRepo.save(toCreate);
        }

        // otherwise, the fitness class already exists
        throw new SportCenterException(HttpStatus.BAD_REQUEST, "There is already a fitness class called " + name + ".");
    }

    @Transactional
    public FitnessClass approveFitnessClass(String name) {
        if (name == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Must provide a name.");
        }
        
        FitnessClass fitnessClass = findFitnessClassByName(name);
        fitnessClass.setIsApproved(true);
        return fitnessClass;
    }

    private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
    
}