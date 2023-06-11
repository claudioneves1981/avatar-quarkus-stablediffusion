package infraestructure.repositories;



import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;
import domain.models.ProfilePhoto;
import domain.repositories.ProfilePhotoRepository;
import infraestructure.rest.StableDiffusionService;
import infraestructure.rest.client.StableDiffusionAlternative;
import jakarta.enterprise.context.RequestScoped;
import org.apache.commons.io.FileUtils;
import org.jboss.logging.Logger;

import java.io.File;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Map;

@RequestScoped
public class UnitOfWorkProfilePhotoRepository implements ProfilePhotoRepository {

    private final HibernateProfilePhotoPersistenceRespository persistenceRespository;

    private final S3ProfilePhotoStorageRespository storageRespository;

    private final StableDiffusionAlternative stableDiffusionService;

    private Map<String, ProfilePhoto> entities;

    public UnitOfWorkProfilePhotoRepository(HibernateProfilePhotoPersistenceRespository persistenceRespository,
                                            S3ProfilePhotoStorageRespository storageRespository,
                                            StableDiffusionAlternative stableDiffusionService) {
        this.persistenceRespository = persistenceRespository;
        this.storageRespository = storageRespository;
        this.stableDiffusionService = stableDiffusionService;
        this.entities = Map.of();
    }

    @Override
    public void registerEntities(Map<String, ProfilePhoto> entities){
        this.entities = entities;
    }

    @Override
    public void commit(){
        entities.forEach((customerId, profilePhoto) -> {
            try {
                persistenceRespository.save(customerId, profilePhoto);

                var generated = stableDiffusionService.img2img(profilePhoto);
                //var originalS3 = storageRespository.store(customerId, profilePhoto).await().indefinitely();
                //var generatedS3 = storageRespository.store(customerId, profilePhoto, generated).await().indefinitely();
                var updated = new ProfilePhoto(profilePhoto.id(), profilePhoto.originalPhoto(), generated.toString());
                persistenceRespository.save(customerId, updated);
            }catch (Exception exception){
                Logger.getLogger(getClass()).error(exception);
            }
        });
    }

    @Override
    public void rollback(){

    }
}
