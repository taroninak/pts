package com.pts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pts.model.Contact;
import com.pts.model.Project;
import com.pts.model.ProjectStatus;
import com.pts.service.ContactService;
import com.pts.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by taronpetrosyan on 8/16/16.
 */
@Controller
public class ApiController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/projects", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> listProjects() {
        String res;
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(projectService.listProjects()));
        }catch(JsonProcessingException ex) {
            ///TODO log exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/projects/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> getProject(@PathVariable("id") int id) {
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(projectService.getProjectById(id)));
        }catch(JsonProcessingException ex) {
            ///TODO log exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/projects", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> postProject(@RequestBody String json) {
        Project project;
        try{
            JsonNode node = objectMapper.readTree(json);
            if(!node.has("title") || !node.has("status")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            project = new Project(null, node.get("title").asText(), ProjectStatus.valueOf(node.get("status").asText()));
        }catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        int id  = projectService.addProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/projects/" + id)).body("{ \"id\":" + id +" }");
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/projects/{id}", method = RequestMethod.PATCH, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> patchProject(@PathVariable("id") int id, @RequestBody String json) {
        Project project;
        try{
            JsonNode node = objectMapper.readTree(json);
            if(!node.has("title") || !node.has("status")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            project = new Project(id, node.get("title").asText(), ProjectStatus.valueOf(node.get("status").asText()));
        }catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        projectService.editProject(project);
        return ResponseEntity.ok(json);
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/projects/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/projects/{id}/contacts", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> listProjectContacts(@PathVariable("id") int id) {
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(contactService.listProjectContacts(id)));
        }catch(JsonProcessingException ex) {
            ///TODO log exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/contacts", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> listContacts() {
        try{
            return  ResponseEntity.ok(objectMapper.writeValueAsString(contactService.listContacts()));
        }catch(JsonProcessingException ex) {
            ///TODO log exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/contacts/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> getContact(@PathVariable("id") int id) {
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(contactService.getContactById(id)));
        }catch(JsonProcessingException ex) {
            ///TODO log exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/contacts", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> postContact(@RequestBody String json) {
        Contact contact;
        try{
            JsonNode node = objectMapper.readTree(json);
            if(!node.has("projectId") || !node.has("contact") || !node.has("email")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            contact = new Contact(null, node.get("projectId").asInt(), node.get("contact").asText(), node.get("email").asText(), node.get("phone").asText());
        }catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        int id = contactService.addContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/contacts" + id)).body("{ \"id\":" + id +" }");
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/contacts/{id}", method = RequestMethod.PATCH, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> patchContact(@PathVariable("id") int id, @RequestBody String json) {
        Contact contact;
        try{
            JsonNode node = objectMapper.readTree(json);
            if(!node.has("projectId") || !node.has("contact") || !node.has("email")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if(node.has("phone")) {
                contact = new Contact(id, node.get("projectId").asInt(), node.get("contact").asText(), node.get("email").asText(), node.get("phone").asText());
            } else {
                contact = new Contact(id, node.get("projectId").asInt(), node.get("contact").asText(), node.get("email").asText());
            }

        }catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        contactService.editContact(contact);
        return ResponseEntity.ok(json);
    }


    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/contacts/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> deleteContact(@PathVariable("id") int id) {
        contactService.deleteContact(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
