/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template Tag, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.tags;

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
public class TagsController {
     @RequestMapping(value = "/tags", method = RequestMethod.GET)
	public ResponseEntity<List<Tag>> getAllTags() {
		List<Tag> result = TagsService.getAllTags();
		
		if(result == null)
			return new ResponseEntity<List<Tag>>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Tag>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/tags/{id}", method = RequestMethod.GET)
	public ResponseEntity<Tag> getTags(@PathVariable int id) {
		Tag result = TagsService.getTags(id);

		if(result == null)
			return new ResponseEntity<Tag>(result, HttpStatus.NOT_FOUND);

		return new ResponseEntity<Tag>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/tags/{id}", method = RequestMethod.POST)
	public ResponseEntity<Integer> updateTags(@PathVariable("id") int id, @RequestBody Tag tag) {
		int result = TagsService.updateTags(id, tag);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/tags/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteTags(@PathVariable("id") int id) {
		int result = TagsService.deleteTags(id);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/tags", method = RequestMethod.PUT)
	public ResponseEntity<Integer> insertTags(@RequestBody Tag tag) {
		int result = TagsService.insertTags(tag);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
