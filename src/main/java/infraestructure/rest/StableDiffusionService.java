package infraestructure.rest;

import domain.models.ProfilePhoto;
import infraestructure.rest.client.StableDiffusion;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.apache.commons.io.FileUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Base64;

@ApplicationScoped
public class StableDiffusionService {

    private final StableDiffusion stableDiffusion;

    public StableDiffusionService(@RestClient StableDiffusion stableDiffusion){
        this.stableDiffusion = stableDiffusion;
    }

    @Transactional
    public Uni<String> generate(ProfilePhoto profilePhoto) throws IOException {
        var path = "/home/claudioneves/Downloads/120997829_373740123989459_5328675156415520508_n.jpg";
        System.out.println(path);
        //var encodedString = Base64.getEncoder().encodeToString(fileContent);
        return stableDiffusion.img2img(new StableDiffusion.Request(path))
                .onItem()
                .transform(response -> response.output().stream().findFirst().orElseThrow());

    }

}
