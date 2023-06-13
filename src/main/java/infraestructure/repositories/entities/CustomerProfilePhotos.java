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

    @Column(name="prompt")
    String prompt;

    @Column(name = "negative_prompt")
    String negativePrompt;

    @Column(name = "width")
    String width;

    @Column(name = "heigth")
    String height;

    @Column(name="samples")
    String samples;

    @Column(name="num_inference_steps")
    String numInferenceSteps;

    @Column(name="guidance_scale")
    Double guidanceScale;

    public Customer toDomain(){
        return new Customer(customerId, List.of(new ProfilePhoto(
                id,
                originalPhoto,
                prompt,
                negativePrompt,
                width,
                height,
                samples,
                numInferenceSteps,
                guidanceScale,
                generatedPhoto)));
    }

    public static CustomerProfilePhotos fromDomain(String customerId, ProfilePhoto profilePhoto){
        var entity = new CustomerProfilePhotos();

        entity.customerId = customerId;
        entity.id = profilePhoto.id();
        entity.guidanceScale = profilePhoto.guidanceScale();
        entity.negativePrompt = profilePhoto.negativePrompt();
        entity.prompt = profilePhoto.prompt();
        entity.numInferenceSteps = profilePhoto.numInferenceSteps();
        entity.height = profilePhoto.height();
        entity.width = profilePhoto.width();
        entity.samples = profilePhoto.samples();
        entity.originalPhoto = profilePhoto.originalPhoto();
        entity.generatedPhoto = profilePhoto.generatedPhoto();

        return entity;
    }

}
