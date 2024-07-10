package pe.edu.unfv.besttraveludemy.infraestructure.helper;

import org.springframework.stereotype.Component;

import pe.edu.unfv.besttraveludemy.util.exceptions.ForbiddenCustomerException;

@Component
public class BlackListHelper {

	public void isInBlackListCustomer(String customerId) {
		if (customerId.equals("BBMB771012HMCRR022")) {
			throw new ForbiddenCustomerException();
		}
	}
}
