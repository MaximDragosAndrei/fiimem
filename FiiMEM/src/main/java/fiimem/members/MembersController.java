/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.members;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andy
 */
@RequestMapping(value = "/model")
@RestController
public class MembersController {

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> result = MembersService.getAllMembers();

        if (result == null) {
            return new ResponseEntity<List<Member>>(result, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Member>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/members/{id}", method = RequestMethod.GET)
    public ResponseEntity<Member> getMembers(@PathVariable int id) {
        Member result = MembersService.getMembers(id);

        if (result == null) {
            return new ResponseEntity<Member>(result, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Member>(result, HttpStatus.OK);
    }
    
    /*@RequestMapping(value = "/members/checkPassword", method = RequestMethod.PUT)
    public ResponseEntity<Integer> checkPassword(@RequestBody LoginDetails login ) {
        Integer result = MembersService.checkPassword(login);
        if (result == 0) {
            return new ResponseEntity<Integer>(result, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }*/

    @RequestMapping(value = "/members/{id}", method = RequestMethod.POST)
    public ResponseEntity<Integer> updateMembers(@PathVariable("id") int id, @RequestBody Member member) {
        int result = MembersService.updateMembers(id, member);
        if (result == 0) {
            return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
        } else {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }
    }

//    /**
//     *
//     * @return
//     */
//    @RequestMapping(value = "/members/updatepassword", method = RequestMethod.GET)
//    public ResponseEntity<Integer> updatePasswordMembers() {
//        int result = MembersService.updatePassword();
//        if (result == 0) {
//            return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
//        } else {
//            return new ResponseEntity<Integer>(result, HttpStatus.OK);
//        }
//    }

    @RequestMapping(value = "/members/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteMembers(@PathVariable("id") int id) {
        int result = MembersService.deleteMembers(id);
        if (result == 0) {
            return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
        } else {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/members", method = RequestMethod.PUT)
    public ResponseEntity<Integer> insertMembers(@RequestBody Member member) {
        int result = MembersService.insertMembers(member);
        if (result == 0) {
            return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
        } else {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }
    }

}
