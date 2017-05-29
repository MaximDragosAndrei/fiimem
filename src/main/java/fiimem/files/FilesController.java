/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.files;


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
public class FilesController {
    @RequestMapping(value = "/files", method = RequestMethod.GET)
	public ResponseEntity<List<File>> getAllFiles() {
		List<File> result = FilesService.getAllFiles();
		
		if(result == null)
			return new ResponseEntity<List<File>>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<File>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
	public ResponseEntity<File> getFiles(@PathVariable int id) {
		File result = FilesService.getFiles(id);

		if(result == null)
			return new ResponseEntity<File>(result, HttpStatus.NOT_FOUND);

		return new ResponseEntity<File>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/files/{id}", method = RequestMethod.POST)
	public ResponseEntity<Integer> updateFiles(@PathVariable("id") int id, @RequestBody File file) {
		int result = FilesService.updateFiles(id, file);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/files/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteFiles(@PathVariable("id") int id) {
		int result = FilesService.deleteFiles(id);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/files", method = RequestMethod.PUT)
	public ResponseEntity<Integer> insertFiles(@RequestBody File file) {
		int result = FilesService.insertFiles(file);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
