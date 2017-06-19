package front.view.controller;

import front.ServerProperties;
import front.TolerantRestTemplate;
import front.dto.Login;
import front.dto.LoginDetails;
import front.dto.RoleEntity;
import front.dto.SessionIdentifier;
import front.view.Model.Member;
import front.view.Model.SignUpResponse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import validator.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Map;

@Controller
public class AuthenticationController {
    private IValidator validator = new Validator();


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView getLoginForm(@RequestParam(value = "error", required = false) String error,
                              Model model, HttpServletRequest req, HttpServletResponse rep) throws IOException {
        return new ModelAndView("/login");

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest req, HttpServletResponse res, Model model) throws URISyntaxException, IOException {

        Map<String, String[]> params = req.getParameterMap();
        if (!validator.isValid(params)) {
            model.addAttribute("inv", "Invalid email");
            return new ModelAndView("/login");
        }
        RestTemplate rt = new RestTemplate();
        Map<String, String> singleValueParams = Mapper.changeToSingle(params);

        rt.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            }
        });
        Login login = new Login();
        login.setUsername(singleValueParams.get("email"));
        login.setPassword(singleValueParams.get("password"));

        ResponseEntity<SessionIdentifier> identifier = rt.postForEntity(
                ServerProperties.backUrl + "/check", login,
                SessionIdentifier.class);

        SessionIdentifier si = identifier.getBody();


        System.out.println(si.isSuccess());
        System.out.println(si.getFailureReason());
        if (!si.isSuccess()) {
            model.addAttribute("failure", si.getFailureReason());
            return new ModelAndView("/login");
        }

        Cookie cookie1 = new Cookie("user-name", singleValueParams.get("email"));
//        Cookie cookie2 = new Cookie("user-token", si.getToken());
        cookie1.setSecure(true);
//        cookie2.setSecure(true);

//        ResponseEntity<RoleEntity> roleResponse = rt.getForEntity(ServerProperties.backUrl + "/get_role/{emailB64}",
//                RoleEntity.class, new String(Base64.getEncoder().encode(singleValueParams.get("email").getBytes())));

        res.addCookie(cookie1);
//        res.addCookie(cookie2);
//        if (roleResponse.getBody().getRole().equals("user")) {
            return new ModelAndView("redirect:/myprofile");
//        } else {
//            return new ModelAndView("redirect:/dashboard_admin");
//        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getAuthenticationForm(HttpServletResponse res) {
        return new ModelAndView("/register");
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView register(@RequestParam Map<String, String> body) {
        RestTemplate template = new TolerantRestTemplate();
        //RestTemplate template = new RestTemplate();
        try {
            Member member = new Member();
            member.setAddress(body.get("address"));
            member.setBithdate(body.get("birthdate"));
            member.setEmail(body.get("email"));
            member.setFirstname(body.get("firstname"));
            member.setPassword(body.get("password"));
            member.setPhone(body.get("phone"));
            member.setSurname(body.get("surname"));
            member.setUsername(body.get("username"));
            template.put(ServerProperties.backUrl + "/members", member);
        } catch (Exception ex) {
            System.out.printf("[error][front][register] %s\n", ex.getMessage());
            return null;
        }
        return new ModelAndView("/register");
    }
//    public ModelAndView createAccount(String error, Model model, HttpServletRequest req, HttpServletResponse rep) {
//
//        Map<String, String[]> params = req.getParameterMap();
//
//        Map<String, String> singleValueParams;
//        if (!validator.isValid(params)) {
//            model.addAttribute("inv", "Invalid email");
//            return new ModelAndView("/register");
//        }
//
//        try {
//            singleValueParams = Mapper.changeToSingle(params);
//        } catch (IllegalArgumentException ex) {
//            System.out.printf("[error][front][register] %s\n", ex.getMessage());
//            return null;
//        }
//
//        if (!singleValueParams.get("password").equals(singleValueParams.get("confirm"))) {
//            model.addAttribute("match", "These passwords don't match. Try again?");
//            return new ModelAndView("/register");
//        }
//        RestTemplate rt = new RestTemplate();
//        Member member = new Member();
//        try {
//            member.setAddress(singleValueParams.get("address"));
//            member.setBithdate(singleValueParams.get("birthdate"));
//            member.setEmail(singleValueParams.get("email"));
//            member.setFirstname(singleValueParams.get("firstname"));
//            member.setPassword(singleValueParams.get("password"));
//            member.setPhone(singleValueParams.get("phone"));
//            member.setSurname(singleValueParams.get("surname"));
//            member.setUsername(singleValueParams.get("username"));
//            rt.put(ServerProperties.backUrl + "/register", member);
//        }catch (Exception ex){
//            System.out.printf("[error][front][register] %s\n", ex.getMessage());
//            return null;
//        }
//        return new ModelAndView("/register");
//
////
////        if (!responseSignUp.getBody().isSuccess()) {
////            model.addAttribute("error", responseSignUp.getBody().getFailureReason());
////            return new ModelAndView("/register");
////        } else {
////            try {
////                rep.sendRedirect("/login");
////            } catch (IOException ex) {
////                System.out.printf("[error][front][register] %s\n", ex.getMessage());
////            }
////            return null;
////        }
//    }


    @RequestMapping(value = "/disconnect", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath("/");
            response.addCookie(cookie);

        }
        return new ModelAndView("redirect:/login");
    }
}