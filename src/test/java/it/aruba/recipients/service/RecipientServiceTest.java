package it.aruba.recipients.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.aruba.recipients.dto.CreateRecipientRequest;
import it.aruba.recipients.entity.Recipient;
import it.aruba.recipients.repository.RecipientRepository;
import it.aruba.recipients.utils.ValidityStatus;
import it.aruba.recipients.validator.DigitalAddressValidator;

class RecipientServiceTest {

    @Mock
    private RecipientRepository repository;

    @Mock
    private DigitalAddressValidator validator;

    @InjectMocks
    private RecipientService service;

    public RecipientServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateValidRecipient() {

        CreateRecipientRequest request = new CreateRecipientRequest();
        request.setName("Mario");
        request.setSurname("Rossi");
        request.setDigitalAddress("mario@pec.it");

        when(repository.findByDigitalAddress(any())).thenReturn(Optional.empty());
        when(validator.validate(any())).thenReturn(ValidityStatus.VALID);

        Recipient saved = new Recipient();
        saved.setId(123);

        when(repository.save(any())).thenReturn(saved);

        Recipient result = service.create(request);

        assertNotNull(result);
        verify(repository).save(any());
    }

    @Test
    void shouldThrowIfDuplicateRecipient() {

        CreateRecipientRequest request = new CreateRecipientRequest();
        request.setDigitalAddress("mario@pec.it");

        when(repository.findByDigitalAddress(any()))
                .thenReturn(Optional.of(new Recipient()));

        assertThrows(RuntimeException.class, () -> {
            service.create(request);
        });
    }
    
    @Test
    void shouldThrowWhenAddressInvalid() {

        CreateRecipientRequest request = new CreateRecipientRequest();
        request.setDigitalAddress("invalid");

        when(repository.findByDigitalAddress(any()))
            .thenReturn(Optional.empty());

        when(validator.validate(any()))
            .thenReturn(ValidityStatus.INVALID);

        assertThrows(RuntimeException.class,
            () -> service.create(request));
    }
}