package companyexp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import companyexp.entities.PaymentMode;

public interface PaymentModeRepository extends JpaRepository<PaymentMode, String> {

}
