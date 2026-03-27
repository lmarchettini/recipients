package it.aruba.recipients.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.aruba.recipients.dto.CreateRecipientRequest;
import it.aruba.recipients.entity.Recipient;
import it.aruba.recipients.exception.DuplicateResourceException;
import it.aruba.recipients.exception.ResourceNotFoundException;
import it.aruba.recipients.repository.RecipientRepository;
import it.aruba.recipients.utils.ValidityStatus;
import it.aruba.recipients.validator.DigitalAddressValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipientService {

	private final RecipientRepository repository;
	private final DigitalAddressValidator validator;

	public Recipient create(CreateRecipientRequest request) {

		if (repository.findByDigitalAddress(request.getDigitalAddress()).isPresent()) {
		    throw new DuplicateResourceException("Recipient already exists");
		}

		ValidityStatus status = validator.validate(request.getDigitalAddress());

		Recipient recipient = Recipient.builder().name(request.getName()).surname(request.getSurname())
				.digitalAddress(request.getDigitalAddress()).validityStatus(status).build();

		return repository.save(recipient);
	}

	public List<Recipient> getAll() {
		return repository.findAll();
	}

	public Recipient getById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipient not found"));
	}
}