/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.documente;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asus
 */
@RequestMapping(value = "/model")
@RestController
public class DocumenteController {
    @RequestMapping(value = "/documente", method = RequestMethod.GET)
	public ResponseEntity<List<Document>> getAllDocumente() {
		List<Document> result = DocumenteService.getAllDocumente();
		
		if(result == null)
			return new ResponseEntity<List<Document>>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Document>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/documente/{id}", method = RequestMethod.GET)
	public ResponseEntity<Document> getDocumente(@PathVariable int id) {
		Document result = DocumenteService.getDocumete(id);

		if(result == null)
			return new ResponseEntity<Document>(result, HttpStatus.NOT_FOUND);

		return new ResponseEntity<Document>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/documente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteDocumente(@PathVariable("id") int id) {
		int result = DocumenteService.deleteDocumente(id);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }
    
    
}
