package front.controllers;

import java.io.File;
import java.util.Scanner;
import org.springframework.web.bind.annotation.*;


@RestController
public class GlobalController {

    private void HTMLloader(String fileName) {
    }

    private String getHTML(String fileName) {
        try {
            StringBuilder result = new StringBuilder();
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    result.append(line).append("\n");
                }
                return result.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String getStatus() {
        return "I'm still alive...";
    }

    @RequestMapping(value = "/{fileName}.html", method = RequestMethod.GET)
    public String getFile(@PathVariable String fileName) {
        return getHTML(fileName + ".html");
    }
}
