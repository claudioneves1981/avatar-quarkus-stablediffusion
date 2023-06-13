package application;


import application.dto.CustomerDTO;
import application.dto.ProfilePhotoDTO;
import domain.services.CustomerReadService;
import domain.services.ProfilePhotoCreateService;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ApplicationService {

    private final CustomerReadService customerReadService;

    private final ProfilePhotoCreateService profilePhotoCreateService;

    public ApplicationService(CustomerReadService customerReadService,
                              ProfilePhotoCreateService profilePhotoCreateService){
        this.customerReadService = customerReadService;
        this.profilePhotoCreateService = profilePhotoCreateService;

    }

    public List<CustomerDTO> searchCustomers(){
        return customerReadService.find().stream().map(CustomerDTO::fromDomain).collect(Collectors.toList());
    }

    public CustomerDTO getCustomer(String customerId){
        return CustomerDTO.fromDomain(customerReadService.findById(customerId));

    }

    public void persistProfilePhoto(String customerId, ProfilePhotoDTO dto){
        profilePhotoCreateService.save(customerId, dto.toDomain());
    }
}
