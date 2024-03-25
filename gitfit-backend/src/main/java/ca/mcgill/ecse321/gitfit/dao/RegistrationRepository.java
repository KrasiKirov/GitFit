package ca.mcgill.ecse321.gitfit.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.gitfit.model.Registration;

@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Integer> {
    Registration findRegistrationById(int id);
}
