package application;


import application.dto.Customer;
import application.dto.ProfilePhoto;
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

    public List<Customer> searchCustomers(){
        return customerReadService.find().stream().map(Customer::fromDomain).collect(Collectors.toList());
    }

    public Customer getCustomer(String customerId){
        return Customer.fromDomain(customerReadService.findById(customerId));

    }

    public void persistProfilePhoto(String customerId, ProfilePhoto dto){
        profilePhotoCreateService.save(customerId, dto.toDomain());
    }
}
