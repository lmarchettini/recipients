package it.aruba.recipients.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aruba.recipients.dto.CreateRecipientRequest;
import it.aruba.recipients.entity.Recipient;
import it.aruba.recipients.service.RecipientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recipients")
@RequiredArgsConstructor
public class RecipientController {

	private final RecipientService service;

	@PostMapping
	public ResponseEntity<Recipient> create(@Valid @RequestBody CreateRecipientRequest request) {
		Recipient recipient = service.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(recipient);
	}

	@GetMapping
	public ResponseEntity<List<Recipient>> getAll() {
		List<Recipient> recipients = service.getAll();
		return ResponseEntity.ok(recipients);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Recipient> getById(@PathVariable Integer id) {
		Recipient recipient = service.getById(id);
		return ResponseEntity.ok(recipient);
	}
}