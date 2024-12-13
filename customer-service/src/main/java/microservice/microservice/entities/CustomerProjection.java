package microservice.microservice.entities;


import org.springframework.data.rest.core.config.Projection;

@Projection(name = "all" , types = Customer.class)
public interface CustomerProjection {
    // Dans cette class on peut récupérer ce qu'on veut
    // http://localhost:9001/customers/1?projection=all
    Long getId();
        String getName();
        String getEmail();

}
