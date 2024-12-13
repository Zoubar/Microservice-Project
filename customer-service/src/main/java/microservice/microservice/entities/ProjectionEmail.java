package microservice.microservice.entities;


import org.springframework.data.rest.core.config.Projection;

@Projection(name = "email" , types = Customer.class)
public interface ProjectionEmail {

    //Ici c'est on veut appeler just email qui se trouve dedans cette projection
    // http://localhost:9001/customers/1?projection=email

    String getEmail();

}
