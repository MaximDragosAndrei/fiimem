/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.filme;
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
public class FilmeController {
     @RequestMapping(value = "/filme", method = RequestMethod.GET)
	public ResponseEntity<List<Film>> getAllFilme() {
		List<Film> result = FilmeService.getAllFilme();
		
		if(result == null)
			return new ResponseEntity<List<Film>>(result, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Film>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/filme/{id}", method = RequestMethod.GET)
	public ResponseEntity<Film> getFilme(@PathVariable int id) {
		Film result = FilmeService.getFilme(id);

		if(result == null)
			return new ResponseEntity<Film>(result, HttpStatus.NOT_FOUND);

		return new ResponseEntity<Film>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/filme/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteFilme(@PathVariable("id") int id) {
		int result = FilmeService.deleteFilme(id);
		if(result == 0)
			return new ResponseEntity<Integer>(result, HttpStatus.NOT_MODIFIED);
		else
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }
    
}
