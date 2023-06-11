package application.dto;

import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.File;
import java.util.UUID;

public record ProfilePhoto(File fileUpload) {

    public static ProfilePhoto create(File fileUpload){
        return new ProfilePhoto(fileUpload);
    }

    public domain.models.ProfilePhoto toDomain(){
        return new domain.models.ProfilePhoto(UUID.randomUUID().toString(),fileUpload().getAbsolutePath(),UUID.randomUUID().toString());

    }
}
