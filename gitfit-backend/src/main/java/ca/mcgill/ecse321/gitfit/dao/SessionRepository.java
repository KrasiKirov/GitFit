package ca.mcgill.ecse321.gitfit.dao;

import ca.mcgill.ecse321.gitfit.model.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session,Integer> {
    Session findSessionById(int id);
}
