package ca.mcgill.ecse321.gitfit.dao;

import ca.mcgill.ecse321.gitfit.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
    Owner findByOwnerId(int ownerId);
}
