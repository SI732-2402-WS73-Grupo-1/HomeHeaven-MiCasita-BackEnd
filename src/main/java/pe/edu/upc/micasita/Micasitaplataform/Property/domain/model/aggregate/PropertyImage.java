package pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.AddPropertyImageCommand;

@Setter
@Getter
@Entity
public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    public String url;

    @NotNull
    @Column
    private Long propertyId;

    @ManyToOne
    @JoinColumn(name = "propertyId", insertable=false, updatable=false)
    private Property property;



    public PropertyImage() {
    }

    public PropertyImage(String url,Long propertyId) {
        this.url = url;
        this.propertyId = propertyId;
    }

    public PropertyImage(AddPropertyImageCommand addPropertyImageCommand) {
        this.url = addPropertyImageCommand.url();
        this.propertyId = addPropertyImageCommand.propertyId();
    }

    public PropertyImage updateImage(String url, Long propertyId) {
        this.url = url;
        this.propertyId = propertyId;
        return this;
    }

}