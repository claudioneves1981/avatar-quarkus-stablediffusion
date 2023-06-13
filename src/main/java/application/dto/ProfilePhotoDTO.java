package application.dto;

import java.util.UUID;

public record ProfilePhotoDTO(String originalPhoto, String prompt, String negativePrompt, String width, String height, String samples, String numInferenceSteps, Double guidanceScale, Double strength) {

    public domain.models.ProfilePhoto toDomain(){
        return new domain.models.ProfilePhoto(
                UUID.randomUUID().toString(),
                originalPhoto(),
                prompt(),
                negativePrompt(),
                width(),
                height(),
                samples(),
                numInferenceSteps(),
                guidanceScale(),
                UUID.randomUUID().toString()
        );

    }
}
