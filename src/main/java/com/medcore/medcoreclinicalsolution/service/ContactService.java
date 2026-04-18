package com.medcore.medcoreclinicalsolution.service;

import com.medcore.medcoreclinicalsolution.dto.ContactDTO;
import com.medcore.medcoreclinicalsolution.exception.ContactNotFoundException;
import com.medcore.medcoreclinicalsolution.model.Contact;
import com.medcore.medcoreclinicalsolution.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;


    public Contact createContact(ContactDTO contactDTO) {
        Contact contact = modelMapper.map(contactDTO, Contact.class);
        return contactRepository.save(contact);
    }

    public Contact findById(UUID id) {
        return contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Contact updateContact(UUID id, ContactDTO contactDTO) {
        Contact existingContact = contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));
        modelMapper.map(contactDTO, existingContact);

        return contactRepository.save(existingContact);
    }


    public String deleteContact(UUID id){
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));

        contactRepository.delete(contact);

        return "The Contact id: "+ id + " is deleted successfully.";
    }

    public long countAll() {
        return contactRepository.count();
    }

    // Count by replied or pending status
    public long countByStatus(boolean status) {
        return contactRepository.countByStatus(status);
    }
}
