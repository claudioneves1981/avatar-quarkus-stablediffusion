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

    @JsonProperty("output") List<String> output;

    public StableDiffusionAlternateService(StableDiffusionAlternative stableDiffusion){
        this.stableDiffusion = stableDiffusion;
    }

    @Transactional
    @Override
    public Response img2img(ProfilePhoto profilePhoto) throws IOException {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"key\": \"ASJdg4ccuknMEwOXGv9XlE0SJuIhZi96JayHOfnGeh5p5WqWk0tk9hCpldgm\",\n    \"prompt\": \"modern disney style\",\n    \"negative_prompt\": \"ugly, disfigured\",\n    \"width\": \"512\",\n    \"height\": \"512\",\n    \"samples\": \"1\",\n    \"num_inference_steps\": \"21\",\n    \"safety_checker\": \"no\",\n    \"enhance_prompt\": \"yes\",\n    \"guidance_scale\": 7.0,\n    \"strength\": 0.9,\n    \"seed\": null,\n    \"webhook\": null,\n    \"track_id\": null\n}");
        Request request = new Request.Builder()
                .url("https://stablediffusionapi.com/api/v3/text2img")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        ResponseBody responseBody = ResponseBody.create(mediaType, String.valueOf(output));
        return client.newCall(request).execute();
    }

}
