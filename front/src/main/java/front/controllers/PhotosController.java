/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.controllers;

import entities.File;
import java.util.Map;
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
public class PhotosController {
    @RequestMapping(value = "/uploadPhotos", method = RequestMethod.POST)
    public boolean register(@RequestParam Map<String, String> body) {
        RestTemplate template = new RestTemplate();
        try {
            File file =new File();
        }catch (Exception ex){
            System.out.printf("[error][front][register] %s\n", ex.getMessage());
            return false;
        }
        return true;
    }
}
