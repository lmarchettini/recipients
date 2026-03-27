package it.aruba.recipients.entity;

import it.aruba.recipients.utils.ValidityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipients")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Recipient {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	private String surname;
	private String digitalAddress;
	@Enumerated(EnumType.STRING)
	private ValidityStatus validityStatus;
}