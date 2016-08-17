package com.pts.service;

import com.pts.model.Contact;
import com.pts.model.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taronpetrosyan on 8/15/16.
 */
public class ContactService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ContactRowMapper contactRowMapper;

    public List<Contact> listContacts() {
        String select = "SELECT * FROM Contacts";
        return jdbcTemplate.query(select, contactRowMapper);
    }

    public List<Contact> listProjectContacts(int projectId) {
        String select = "SELECT * FROM Contacts where project_id=" + projectId;
        return jdbcTemplate.query(select, contactRowMapper);
    }

    public Contact getContactById(int id) {
        String select = "SELECT * FROM Contacts WHERE id=" + id;
        return jdbcTemplate.query(select, contactRowMapper).get(0);
    }

    public void addContact(Contact contact) {
        String insert = "INSERT INTO Contacts (project_id, contact, email, phone) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(insert, contact.getProjectId(), contact.getContact(), contact.getEmail(), contact.getPhone());
    }

    public void editContact(Contact contact) {
        String update = "UPDATE Contacts SET project_id=" + contact.getProjectId() +
                ", contact='" + contact.getContact() + "', email='" + contact.getEmail() +
                "', phone='" + contact.getPhone() + "' WHERE id=" + contact.getId();
        jdbcTemplate.update(update);
    }

    public void deleteContact(int id) {
        String delete = "DELETE FROM Contacts WHERE id=" + id;
        jdbcTemplate.execute(delete);
    }
}
