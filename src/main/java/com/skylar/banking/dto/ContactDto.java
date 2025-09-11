package com.skylar.banking.dto;

import com.skylar.banking.models.Contact;
import com.skylar.banking.models.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ContactDto {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String iban;

    private Integer userId;

    public static ContactDto fromEntity(Contact contact) {
        return ContactDto.builder()
                .id(contact.getId())
                .firstname(contact.getFirstName())
                .lastname(contact.getLastName())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();
    }

    public static Contact toEntity(ContactDto contact) {
        return Contact.builder()
                .id(contact.getId())
                .firstName(contact.getFirstname())
                .lastName(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .user(
                        User.builder()
                                .id(contact.getUserId())
                                .build()
                )
                .build();
    }
}
