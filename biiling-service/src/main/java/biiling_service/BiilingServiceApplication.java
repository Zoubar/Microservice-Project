package biiling_service;

import biiling_service.entities.Bill;
import biiling_service.entities.ProductItem;
import biiling_service.feign.CustomerRestClient;
import biiling_service.feign.ProductRestClient;
import biiling_service.model.Customer;
import biiling_service.model.Product;
import biiling_service.repository.BillRepository;
import biiling_service.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BiilingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiilingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BillRepository billRepository, ProductItemRepository productItemRepository,
										ProductRestClient productRestClient
			, CustomerRestClient customerRestClient) {
		return  args ->
		{

			Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
			Collection<Product> products = productRestClient.getAllProducts().getContent();

			customers.forEach(customer -> {
				Bill bill = Bill.builder().billingDate(new Date()).customerId(customer.getId()).build();
				billRepository.save(bill);

				products.forEach(product -> {

					ProductItem productItem = ProductItem.builder()
							.bill(bill)
							.productId(product.getId())
							.quantity(new Random().nextInt(10))
							.unitPrice(product.getPrice())
							.build();
					productItemRepository.save(productItem);

				});
			});
		};
	}
}
