package pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources;


public record PropertyResource(Long Id,
                               String title, String description,String owner, String price, String location,
                               String status, String type, String currency,String size, Integer bedrooms, Integer bathrooms,
                               Integer garageSpace, Integer yearBuilt) {
}