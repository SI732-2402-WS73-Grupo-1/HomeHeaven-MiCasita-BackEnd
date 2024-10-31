package pe.edu.upc.micasita.Micasitaplataform.Property.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.PropertyImage;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.queries.GetPropertyImageByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.queries.GetPropertyImageByPropertyIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyImageQueryService;
import pe.edu.upc.micasita.Micasitaplataform.Property.infrastructure.persistence.jpa.repositories.PropertyImageRepository;

import java.util.Optional;

@Service
public class PropertyImageQueryServiceImpl implements PropertyImageQueryService {
    private final PropertyImageRepository propertyImageRepository;

    public PropertyImageQueryServiceImpl(PropertyImageRepository propertyImageRepository) {
        this.propertyImageRepository = propertyImageRepository;
    }

    @Override
    public Optional<PropertyImage> handle(GetPropertyImageByIdQuery query) {
        return propertyImageRepository.findById(query.id());
    }
    @Override
    public Optional<PropertyImage> handle(GetPropertyImageByPropertyIdQuery query) {
        return propertyImageRepository.findByPropertyId(query.propertyId());
    }
}
