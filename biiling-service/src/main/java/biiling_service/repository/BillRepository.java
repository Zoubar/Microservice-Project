package biiling_service.repository;

import biiling_service.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BillRepository  extends JpaRepository<Bill, Long> {


}
