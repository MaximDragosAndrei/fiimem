package front.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by andy on 18.06.2017.
 */

@Controller
public class AboutUsController {
    @RequestMapping(value="/aboutus", method= RequestMethod.GET)
    public ModelAndView aboutUs(Model model, HttpServletRequest req){
        return new ModelAndView("/aboutus");
    }
}
