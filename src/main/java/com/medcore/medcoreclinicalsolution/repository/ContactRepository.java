package com.medcore.medcoreclinicalsolution.repository;

import com.medcore.medcoreclinicalsolution.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    // Count all contacts
    long count();

    // Count contacts by status (true = replied, false = pending)
    long countByStatus(boolean status);
}
