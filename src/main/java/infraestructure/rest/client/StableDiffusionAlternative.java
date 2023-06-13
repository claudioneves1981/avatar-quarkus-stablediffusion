package infraestructure.rest.client;

import com.squareup.okhttp.*;
import domain.models.ProfilePhoto;

import java.io.IOException;

public interface StableDiffusionAlternative {

    Response text2img(ProfilePhoto profilePhoto) throws IOException;

}
