package application.dto;

import domain.models.ProfilePhoto;

import java.util.List;
import java.util.stream.Collectors;

public record CustomerDTO(String customerId, List<String> photos) {

    public static CustomerDTO fromDomain(domain.models.Customer domain){
        return new CustomerDTO(domain.id(),
                            domain.profilePhotos().stream().map(ProfilePhoto::generatedPhoto).collect(Collectors.toList()));
    }
}
