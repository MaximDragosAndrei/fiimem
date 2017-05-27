package fiimem.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class GlobalController {

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String getVersion() {
		// TODO -> statistici despre db-uri
		return "I'm still alive...";
	}

}