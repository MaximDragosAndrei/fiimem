/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.tags_files;

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
public class TagsFilesController {
    @RequestMapping(value = "/tagsfiles", method = RequestMethod.GET)
	public ResponseEntity<List<TagFile>> getAllTagsFiles() {
		List<TagFile> result = TagsFilesService.getAllTagsFiles();
		
		if(result == null)
			return new ResponseEntity<List<TagFile>>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<TagFile>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/tagsfiles/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<TagFile>> getTagsFiles(@PathVariable int id) {
		ArrayList<TagFile> result = TagsFilesService.getTagsFiles(id);

		if(result == null)
			return new ResponseEntity<ArrayList<TagFile>>(result, HttpStatus.NOT_FOUND);

		return new ResponseEntity<ArrayList<TagFile>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/tagsfiles/{id}", method = RequestMethod.POST)
	public ResponseEntity<Integer> updatePrivacyFiles(@PathVariable("id") int id, @RequestBody TagFile tfile) {
		int result = TagsFilesService.updateTagsFiles(id, tfile);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/tagsfiles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteTags(@PathVariable("id") int id) {
		int result = TagsFilesService.deleteTagsFiles(id);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/tagsfiles", method = RequestMethod.PUT)
	public ResponseEntity<Integer> insertPrivacyFiles(@RequestBody TagFile tfile) {
		int result = TagsFilesService.insertTagsFiles(tfile);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
