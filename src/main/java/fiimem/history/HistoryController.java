/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.history;

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
public class HistoryController {
    @RequestMapping(value = "/history", method = RequestMethod.GET)
	public ResponseEntity<List<History>> getAllHistory() {
		List<History> result = HistoryService.getAllHistory();
		
		if(result == null)
			return new ResponseEntity<List<History>>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<History>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/history/{id}", method = RequestMethod.GET)
	public ResponseEntity<History> getHistory(@PathVariable int id) {
		History result = HistoryService.getHistory(id);

		if(result == null)
			return new ResponseEntity<History>(result, HttpStatus.NOT_FOUND);

		return new ResponseEntity<History>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/history/{id}", method = RequestMethod.POST)
	public ResponseEntity<Integer> updateHistory(@PathVariable("id") int id, @RequestBody History history) {
		int result = HistoryService.updateHistory(id, history);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/history/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteHistory(@PathVariable("id") int id) {
		int result = HistoryService.deleteHistory(id);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/history", method = RequestMethod.PUT)
	public ResponseEntity<Integer> insertHistory(@RequestBody History history) {
		int result = HistoryService.insertHistory(history);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
