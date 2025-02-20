package biiling_service.web;

import biiling_service.entities.Bill;
import biiling_service.entities.ProductItem;
import biiling_service.feign.CustomerRestClient;
import biiling_service.feign.ProductRestClient;
import biiling_service.repository.BillRepository;
import biiling_service.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BillRestController {

        @Autowired
        private BillRepository billRepository;
        @Autowired
        private ProductItemRepository productItemRepository;
        @Autowired
        private CustomerRestClient  customerRestClient;
        @Autowired
        private ProductRestClient productRestClient;

        @GetMapping(path = "/bills/{id}")
        public Bill getBill(@PathVariable  Long id)
        {
                Bill bill   = billRepository.findById(id).get();
                bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
                bill.getProductItems().forEach(productItem -> {
                        productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
                });
                return bill;

        }

}
