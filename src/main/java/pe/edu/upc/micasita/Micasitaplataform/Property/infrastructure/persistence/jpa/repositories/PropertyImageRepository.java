package pe.edu.upc.micasita.Micasitaplataform.Property.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.PropertyImage;

import java.util.Optional;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {
    @NonNull
    Optional<PropertyImage> findById(@NonNull Long id);
    Optional<PropertyImage> findByPropertyId(@NonNull Long propertyId);
}
