package inventory_service.inventory_service;

import inventory_service.inventory_service.entities.Product;
import inventory_service.inventory_service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.rmi.server.UID;
import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository)
	{
		return args -> {

			productRepository.save(Product.builder()
					.id(UUID.randomUUID().toString())
					.name("Computer")
					.price(3200)
					.quantity(11)
					.build());


			productRepository.save(Product.builder()
					.id(UUID.randomUUID().toString())
					.name("Printer")
					.price(1299)
					.quantity(10)
					.build());


			productRepository.save(Product.builder()
					.id(UUID.randomUUID().toString())
					.name("Smartphone")
					.price(3200)
					.quantity(11)
					.build());

			productRepository.findAll().forEach(c->{

						System.out.println(c.toString());

			});



		};
	}
}
