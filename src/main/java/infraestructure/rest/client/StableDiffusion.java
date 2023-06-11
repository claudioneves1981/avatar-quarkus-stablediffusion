package infraestructure.rest.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "stable-diffusion-api")
public interface StableDiffusion {

    @POST
    @Path("/api/v3/img2img")
    Uni<Response> img2img(Request request);

    record Request(@JsonProperty("init_image") String initImages,
                   String prompt,
                   @JsonProperty("negative_prompt") String negativePrompt,
                   String seed,
                   Double strength,
                   @JsonProperty("guindance_scale") Double guindanceScale,
                   @JsonProperty("num_inference_steps") String numInferenceSteps,
                   String width,
                   String height) {

        public Request(String initImage) {
            this(initImage,
                    "modern disney style",
                    "ugly, disfigured",
                    "-1",
                    0.9,
                    7.0,
                    "21",
                    "512",
                    "512");
        }
    }

    record Response(List<String> output) {

    }

}
