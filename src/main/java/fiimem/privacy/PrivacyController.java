/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.privacy;

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
public class PrivacyController {
    @RequestMapping(value = "/privacy", method = RequestMethod.GET)
	public ResponseEntity<List<Privacy>> getAllPrivacy() {
		List<Privacy> result = PrivacyService.getAllPrivacy();
		
		if(result == null)
			return new ResponseEntity<List<Privacy>>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Privacy>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacy/{id}", method = RequestMethod.GET)
	public ResponseEntity<Privacy> getPrivacy(@PathVariable int id) {
		Privacy result = PrivacyService.getPrivacy(id);

		if(result == null)
			return new ResponseEntity<Privacy>(result, HttpStatus.NOT_FOUND);

		return new ResponseEntity<Privacy>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacy/{id}", method = RequestMethod.POST)
	public ResponseEntity<Integer> updatePrivacy(@PathVariable("id") int id, @RequestBody Privacy privacy) {
		int result = PrivacyService.updatePrivacy(id, privacy);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacy/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deletePrivacy(@PathVariable("id") int id) {
		int result = PrivacyService.deletePrivacy(id);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacy", method = RequestMethod.PUT)
	public ResponseEntity<Integer> insertPrivacy(@RequestBody Privacy privacy) {
		int result = PrivacyService.insertPrivacy(privacy);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
