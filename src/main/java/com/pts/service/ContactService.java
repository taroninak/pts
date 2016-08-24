package com.pts.service;

import com.pts.helper.SimplePreparedStatementCreator;
import com.pts.model.Contact;
import com.pts.model.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.List;

/**
 * Created by taronpetrosyan on 8/15/16.
 */
public class ContactService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ContactRowMapper contactRowMapper;


    @Autowired
    private GeneratedKeyHolder generatedKeyHolder;

    @Autowired
    private SimplePreparedStatementCreator simplePreparedStatementCreator;

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

    public int addContact(Contact contact) {
        String insert = "INSERT INTO Contacts (project_id, contact, email, phone) VALUES('" +
                contact.getProjectId() + "','" + contact.getContact() + "','" + contact.getEmail() +
                "','" + contact.getPhone() + "')";
        simplePreparedStatementCreator.setSql(insert);
        jdbcTemplate.update(simplePreparedStatementCreator, generatedKeyHolder);
        return generatedKeyHolder.getKey().intValue();
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
