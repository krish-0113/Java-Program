package com.smartcontactmanager.SmartContactManger.repository;

import com.smartcontactmanager.SmartContactManger.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {

}
