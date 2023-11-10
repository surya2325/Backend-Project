package companyexp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import companyexp.entities.PaymentMode;
import companyexp.repository.PaymentModeRepository;

@RestController
@CrossOrigin("**")
public class PaymentModeController {

	@Autowired
	private PaymentModeRepository paymentmoderepo;

	@GetMapping("/paymentmodes")
	public List<PaymentMode> getAllPaymentmodes() {
		return paymentmoderepo.findAll();
	}

	@PostMapping("/addpayment")
	public String addPaymentMode(@RequestBody PaymentMode newPaymentMode) {
		paymentmoderepo.save(newPaymentMode);
		return "PaymentMode added successfully";
	}

	@PutMapping("/updatepayment/{paymentname}")
	public PaymentMode updatePaymentname(@RequestParam("paymentcode") String paymentcode,
			@PathVariable("paymentname") String paymentname) {
		Optional<PaymentMode> optionalPaymentMode = paymentmoderepo.findById(paymentcode);
		if (optionalPaymentMode.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "paymentcode not found");
		}
		PaymentMode paymentmode = optionalPaymentMode.get();
		paymentmode.setPaymentName(paymentname);
		return paymentmoderepo.save(paymentmode);

	}

	@DeleteMapping("/deletepaymentmode")
	public String deletePaymentMode(@RequestParam("paymentCode") String paymentcode) {
		Optional<PaymentMode> optionalPaymentMode = paymentmoderepo.findById(paymentcode);
		if (optionalPaymentMode.isPresent()) {
			paymentmoderepo.deleteById(paymentcode);
			return "Paymentmode deleted";
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "paymentcode not found");
	}

}
