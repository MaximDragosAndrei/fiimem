/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.gentree;

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
public class GenTreeController {
    @RequestMapping(value = "/gentree", method = RequestMethod.GET)
	public ResponseEntity<List<GenTree>> getAllGenTree() {
		List<GenTree> result = GenTreeService.getAllGenTree();
		
		if(result == null)
			return new ResponseEntity<List<GenTree>>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<GenTree>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/gentree/{id}", method = RequestMethod.GET)
	public ResponseEntity<GenTree> getGenTree(@PathVariable int id) {
		GenTree result = GenTreeService.getGenTree(id);

		if(result == null)
			return new ResponseEntity<GenTree>(result, HttpStatus.NOT_FOUND);

		return new ResponseEntity<GenTree>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/gentree/{id}", method = RequestMethod.POST)
	public ResponseEntity<Integer> updateGenTree(@PathVariable("id") int id, @RequestBody GenTree gentree) {
		int result = GenTreeService.updateGenTree(id, gentree);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/gentree/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteGenTree(@PathVariable("id") int id) {
		int result = GenTreeService.deleteGenTree(id);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/gentree", method = RequestMethod.PUT)
	public ResponseEntity<Integer> insertGenTree(@RequestBody GenTree gentree) {
		int result = GenTreeService.insertGenTree(gentree);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
