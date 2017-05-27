/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.privacy_files;

import java.util.ArrayList;
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
public class PrivacyFilesController {
    @RequestMapping(value = "/privacyfiles", method = RequestMethod.GET)
	public ResponseEntity<List<PrivacyFile>> getAllPrivacyFiles() {
		List<PrivacyFile> result = PrivacyFilesService.getAllPrivacyFiles();
		
		if(result == null)
			return new ResponseEntity<List<PrivacyFile>>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<PrivacyFile>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacyfiles/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<PrivacyFile>> getPrivacyFiles(@PathVariable int id) {
		ArrayList<PrivacyFile> result = PrivacyFilesService.getPrivacyFiles(id);

		if(result == null)
			return new ResponseEntity<ArrayList<PrivacyFile>>(result, HttpStatus.NOT_FOUND);

		return new ResponseEntity<ArrayList<PrivacyFile>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacyfiles/{id}", method = RequestMethod.POST)
	public ResponseEntity<Integer> updatePrivacyFiles(@PathVariable("id") int id, @RequestBody PrivacyFile pfile) {
		int result = PrivacyFilesService.updatePrivacyFiles(id, pfile);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacyfiles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteTags(@PathVariable("id") int id) {
		int result = PrivacyFilesService.deletePrivacyFiles(id);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/privacyfiles", method = RequestMethod.PUT)
	public ResponseEntity<Integer> insertPrivacyFiles(@RequestBody PrivacyFile pfile) {
		int result = PrivacyFilesService.insertPrivacyFiles(pfile);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
