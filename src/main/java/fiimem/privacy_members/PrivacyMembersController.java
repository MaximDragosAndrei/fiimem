/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.privacy_members;

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
public class PrivacyMembersController {
    @RequestMapping(value = "/privacymembers", method = RequestMethod.GET)
	public ResponseEntity<List<PrivacyMember>> getAllPrivacyMembers() {
		List<PrivacyMember> result = PrivacyMembersService.getAllPrivacyMembers();
		
		if(result == null)
			return new ResponseEntity<List<PrivacyMember>>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<PrivacyMember>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacymembers/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PrivacyMember>> getPrivacyMembers(@PathVariable int id) {
		List<PrivacyMember> result = PrivacyMembersService.getPrivacyMembers(id);

		if(result == null)
			return new ResponseEntity<List<PrivacyMember>>(result, HttpStatus.NOT_FOUND);

		return new ResponseEntity<List<PrivacyMember>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacymembers/{id}", method = RequestMethod.POST)
	public ResponseEntity<Integer> updatePrivacyMembers(@PathVariable("id") int id, @RequestBody PrivacyMember privacyMember) {
		int result = PrivacyMembersService.updatePrivacyMembers(id, privacyMember);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacymembers/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deletePrivacyMembers(@PathVariable("id") int id) {
		int result = PrivacyMembersService.deletePrivacyMembers(id);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacymembers", method = RequestMethod.PUT)
	public ResponseEntity<Integer> insertPrivacyMembers(@RequestBody PrivacyMember privacyMember) {
		int result = PrivacyMembersService.insertPrivacyMembers(privacyMember);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
