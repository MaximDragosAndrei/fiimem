/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.controllers;

import entities.LoginDetails;
import front.ServerProperties;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author andy
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@RequestParam Map<String, String> body) {
        RestTemplate template = new RestTemplate();
        try {
            LoginDetails login = new LoginDetails();
            login.setPassword(body.get("password"));
            login.setEmail(body.get("email"));
            template.put(ServerProperties.modelUrl + "/check", login);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String read(@RequestParam Map<String, String> body) {
        return body.toString();
    }*/
}
