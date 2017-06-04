/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.controllers;

import entities.Member;
import front.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author andy
 */
@RestController
public class RegisterController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Member> register(@RequestBody Member member) {
        System.out.println("New user!");
        RestTemplate template = new RestTemplate();
        ResponseEntity<Member> rs;
        rs = template.getForEntity(ServerProperties.modelUrl + "/members", Member.class);
        return rs;
    }
}
