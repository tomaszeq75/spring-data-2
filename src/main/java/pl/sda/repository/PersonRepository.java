package pl.sda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sda.model.PersonEntity;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    List<PersonEntity> findByFirstName(String firstName);

    // JPQL
    @Query("select p from PersonEntity p where p.lastName = :lastName")
    List<PersonEntity> findByLastName(@Param("lastName") String lastName);

    // native SQL
    @Query(value = "select * from person p where p.firstName = :firstName AND p.lastName = :lastName", nativeQuery = true)
    List<PersonEntity> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);



}
