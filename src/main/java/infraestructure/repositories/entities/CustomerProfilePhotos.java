package infraestructure.repositories.entities;

import domain.models.Customer;
import domain.models.ProfilePhoto;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "profile_photos")
@Table(name = "profile_photos")
public class CustomerProfilePhotos {

    @Column(name = "original_photo")
    String originalPhoto;

    @Column(name = "generated_photo")
    String generatedPhoto;

    @Column(name = "customer_id")
        String customerId;

    @Id
    @Column(name = "id")
        String id;


    public Customer toDomain(){
        return new Customer(customerId, List.of(new ProfilePhoto(id,
                originalPhoto,
                generatedPhoto)));

    }

    public static CustomerProfilePhotos fromDomain(String customerId, ProfilePhoto profilePhoto){
        var entity = new CustomerProfilePhotos();

        entity.customerId = customerId;
        entity.id = profilePhoto.id();

        entity.originalPhoto = profilePhoto.originalPhoto();
        entity.generatedPhoto = profilePhoto.generatedPhoto();

        return entity;
    }

}
