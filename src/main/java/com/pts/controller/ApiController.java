package com.pts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pts.service.ContactService;
import com.pts.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String getProjects() {
        String res;
        try{
            res = objectMapper.writeValueAsString(projectService.listProjects());
        }catch(JsonProcessingException ex) {
            res = "{ err: " + ex.getMessage() + "}";
        }
        return res;
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/projects/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getProjectById(@PathVariable("id") int id) {
        String res;
        try{
            res = objectMapper.writeValueAsString(projectService.getProjectById(id));
        }catch(JsonProcessingException ex) {
            res = "{ err: " + ex.getMessage() + "}";
        }
        return res;
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/projects/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String deleteProjectById(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return "{ \"success\": " + "\"Project with '" + id + "' has been deleted.\"}";
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/projects/{id}/contacts", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getProjectContacts(@PathVariable("id") int id) {
        String res;
        try{
            res = objectMapper.writeValueAsString(contactService.listProjectContacts(id));
        }catch(JsonProcessingException ex) {
            res = "{ err: " + ex.getMessage() + "}";
        }
        return res;
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/contacts", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getContacts() {
        String res;
        try{
            res = objectMapper.writeValueAsString(contactService.listContacts());
        }catch(JsonProcessingException ex) {
            res = "{ err: " + ex.getMessage() + "}";
        }
        return res;
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/api/contacts/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getContactById(@PathVariable("id") int id) {
        String res;
        try{
            res = objectMapper.writeValueAsString(contactService.getContactById(id));
        }catch(JsonProcessingException ex) {
            res = "{ err: " + ex.getMessage() + "}";
        }
        return res;
    }
}
