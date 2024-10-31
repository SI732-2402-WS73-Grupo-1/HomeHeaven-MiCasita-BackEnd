package pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands;



public record AddPropertyCommand(String title,
                                 String description,
                                 String owner,
                                 String price,
                                 String location,
                                 String status,
                                 String type,
                                 String currency,
                                 String size,
                                 Integer bedrooms,
                                 Integer bathrooms,
                                 Integer garageSpace,
                                 Integer yearBuilt
                                 ) {

}
