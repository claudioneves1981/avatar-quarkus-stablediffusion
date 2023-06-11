package infraestructure.rest.client;

import com.squareup.okhttp.*;
import domain.models.ProfilePhoto;

import java.io.IOException;

public interface StableDiffusionAlternative {

    Response img2img(ProfilePhoto profilePhoto) throws IOException;

}
