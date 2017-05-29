/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.members_tags;

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
public class MembersTagsController {
 @RequestMapping(value = "/memberstags", method = RequestMethod.GET)
	public ResponseEntity<List<MemberTag>> getAllMembersTags() {
		List<MemberTag> result = MembersTagsService.getAllMembersTags();
		
		if(result == null)
			return new ResponseEntity<List<MemberTag>>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<MemberTag>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/memberstags/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<MemberTag>> getMembersTags(@PathVariable int id) {
		List<MemberTag> result = MembersTagsService.getMembersTags(id);

		if(result == null)
			return new ResponseEntity<List<MemberTag>>(result, HttpStatus.NOT_FOUND);

		return new ResponseEntity<List<MemberTag>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/memberstags/{id}", method = RequestMethod.POST)
	public ResponseEntity<Integer> updateMembersTags(@PathVariable("id") int id, @RequestBody MemberTag membertag) {
		int result = MembersTagsService.updateMembersTags(id, membertag);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/memberstags/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteMembersTags(@PathVariable("id") int id) {
		int result = MembersTagsService.deleteMembersTags(id);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/memberstags", method = RequestMethod.PUT)
	public ResponseEntity<Integer> insertMembersTags(@RequestBody MemberTag membertag) {
		int result = MembersTagsService.insertMembersTags(membertag);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}   
}
