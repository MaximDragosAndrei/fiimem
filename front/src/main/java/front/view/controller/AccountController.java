package front.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by andy on 19.06.2017.
 */
@Controller
public class AccountController {
    @RequestMapping(value="/myprofile", method= RequestMethod.GET)
    public ModelAndView myProfile(Model model, HttpServletRequest req){
        return new ModelAndView("/myprofile");
    }
    @RequestMapping(value="/myphotos", method= RequestMethod.GET)
    public ModelAndView myPhotos(Model model, HttpServletRequest req){
        return new ModelAndView("/myphotos");
    }
    @RequestMapping(value="/mydocuments", method= RequestMethod.GET)
    public ModelAndView myDocuments(Model model, HttpServletRequest req){
        return new ModelAndView("/mydocuments");
    }
    @RequestMapping(value="/myvideos", method= RequestMethod.GET)
    public ModelAndView myVideos(Model model, HttpServletRequest req){
        return new ModelAndView("/myvideos");
    }
}
