package it.aruba.recipients.validator;

import org.springframework.stereotype.Component;

import it.aruba.recipients.utils.ValidityStatus;

@Component
public class DigitalAddressValidator {

	/*
	 * per semplificare e poter effettuare i test ho fatto una validazione
	 * dell'indirizo digitale manuale, in un caso reale, come da modello
	 * architetturale avrei usato un rest template per andare a richiamare il servizio
	 * esterno di OPENAPI ( GET https://pec-openapi.it/domini_pec/pec.it) per
	 * validare il dominio
	 */

	public ValidityStatus validate(String email) {

		if (email == null || !email.contains("@")) {
			return ValidityStatus.INVALID;
		}

		if (!email.endsWith("@pec.it")) {
			return ValidityStatus.INVALID;
		}

		return ValidityStatus.VALID;
	}
}