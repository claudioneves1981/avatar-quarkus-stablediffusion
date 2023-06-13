package infraestructure.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.okhttp.*;
import domain.models.ProfilePhoto;
import infraestructure.rest.client.StableDiffusion;
import infraestructure.rest.client.StableDiffusionAlternative;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class StableDiffusionAlternateService implements StableDiffusionAlternative {


    private final StableDiffusionAlternative stableDiffusion;

    public StableDiffusionAlternateService(StableDiffusionAlternative stableDiffusion){
        this.stableDiffusion = stableDiffusion;
    }

    @Transactional
    @Override
    public Response text2img(ProfilePhoto profilePhoto) throws IOException {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        System.out.print(profilePhoto.toString());
        RequestBody body = RequestBody.create(mediaType, profilePhoto.toString());
        Request request = new Request.Builder()
                .url("https://stablediffusionapi.com/api/v3/text2img")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        return client.newCall(request).execute();
    }

}
