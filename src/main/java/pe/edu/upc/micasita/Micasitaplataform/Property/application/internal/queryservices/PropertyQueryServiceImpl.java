package pe.edu.upc.micasita.Micasitaplataform.Property.application.internal.queryservices;


import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.Property;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.queries.*;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyQueryService;
import pe.edu.upc.micasita.Micasitaplataform.Property.infrastructure.persistence.jpa.repositories.PropertyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyQueryServiceImpl implements PropertyQueryService {

    private final PropertyRepository propertyRepository;

    public PropertyQueryServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
    @Override
    public Optional<Property> handle(GetPropertyByLocationQuery query) {
        return propertyRepository.findByLocation(query.location());
    }


    @Override
    public Optional<Property> handle(GetPropertyByStatusQuery query) {
        return propertyRepository.findByStatus(query.status());
    }

    @Override
    public Optional<Property> handle(GetPropertyByTypeQuery query) {
        return propertyRepository.findByType(query.type());
    }



    @Override
    public Optional<Property> handle(GetPropertyByBedroomsQuery query) {
        return propertyRepository.findByBedrooms(query.bedrooms());
    }

    @Override
    public Optional<Property> handle(GetPropertyByBathroomsQuery query) {
        return propertyRepository.findByBathrooms(query.bathrooms());
    }

    @Override
    public Optional<Property> handle(GetPropertyByGarageSpaceQuery query) {
        return propertyRepository.findByGarageSpace(query.garageSpace());
    }

    @Override
    public Optional<Property> handle(GetPropertyByYearBuiltQuery query) {
        return propertyRepository.findByYearBuilt(query.yearBuilt());
    }


    @Override
    public Optional<Property> handle(GetPropertyByIdQuery query) {
        return propertyRepository.findById(query.propertyId());
    }

    @Override
    public List<Property> handle(GetAllPropertiesQuery query) {
        return propertyRepository.findAll();
    }
}