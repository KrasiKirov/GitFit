// package ca.mcgill.ecse321.gitfit.dao;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import ca.mcgill.ecse321.gitfit.model.FitnessClass;

// @SpringBootTest
// public class FitnessClassRepositoryTest {
//     @Autowired
//     private FitnessClassRepository fitnessClassRepository;

//     @AfterEach
//     public void clearDatabase() {
//         fitnessClassRepository.deleteAll();
//     }

//     @Test
//     public void testPersistAndLoadFitnessClass() {
//         // Create fitness class
//         String name = "Goat Yoga";
//         String description = "The best yoga class";

//         FitnessClass fitnessClass = new FitnessClass();
//         fitnessClass.setName(name);
//         fitnessClass.setDescription(description);

//         // Save fitness class
//         fitnessClass = fitnessClassRepository.save(fitnessClass);

//         // Read fitness class from database
//         fitnessClass = fitnessClassRepository.findFitnessClassById(fitnessClass.getId());

//         // Assert fitness class is not null and has correct attributes
//         assertNotNull(fitnessClass);
//         assertEquals(name, fitnessClass.getName());
//         assertEquals(description, fitnessClass.getDescription());
//     }
// }
