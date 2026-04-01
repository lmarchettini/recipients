package it.aruba.recipients.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.aruba.recipients.entity.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, Integer> {
	Optional<Recipient> findByDigitalAddress(String digitalAddress);
}