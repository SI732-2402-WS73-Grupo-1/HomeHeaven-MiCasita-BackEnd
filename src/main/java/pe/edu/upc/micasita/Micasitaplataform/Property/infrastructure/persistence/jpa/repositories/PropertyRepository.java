package pe.edu.upc.micasita.Micasitaplataform.Property.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.Property;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findByLocation(@NonNull String location);

    boolean existsByLocation(@NonNull String location);
    boolean existsByLocationAndIdIsNot(@NonNull String location, @NonNull Long id);
    boolean existsById(@NonNull Long id);
    void deleteById(@NonNull Long id);
    Optional<Property> findByBedrooms(@NonNull Integer bedrooms);


    Optional<Property> findByStatus(Property status);

    Optional<Property> findByType(Property type);


    Optional<Property> findByBathrooms(@NonNull Integer bathrooms);

    Optional<Property> findByGarageSpace(@NonNull Integer integer);

    Optional<Property> findByYearBuilt(@NonNull Integer integer);

    @NonNull
    Optional<Property> findById(@NonNull Long id);
    @NonNull
    List<Property> findAll();
}