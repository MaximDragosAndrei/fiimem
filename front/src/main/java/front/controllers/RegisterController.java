/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.controllers;
//import com.google.common.base.Charsets;
//import com.google.common.io.Resources;

import entities.Member;
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
public class RegisterController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean register(@RequestParam Map<String, String> body) {
        RestTemplate template = new RestTemplate();
        try {
            Member member = new Member();
            member.setAddress(body.get("address"));
            member.setBithdate(body.get("birthdate"));
            member.setEmail(body.get("email"));
            member.setFirstname(body.get("firstname"));
            member.setPassword(body.get("password"));
            member.setPhone(Integer.parseInt(body.get("phone")));
            member.setSurname(body.get("surname"));
            member.setUsername(body.get("username"));
            template.put(ServerProperties.modelUrl + "/members", member);
        } catch (Exception ex) {
            System.out.printf("[error][front][register] %s\n", ex.getMessage());
            return false;
        }
        return true;
    }

}
