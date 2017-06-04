package Utils;

//import com.ip_b1.fii.admission.DTO.Email;
//import com.ip_b1.fii.admission.DTO.User;
//import com.ip_b1.fii.admission.ServerProperties;
import entities.AuthEntity;
import entities.Member;
import front.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/auth")
public class AuthUtils {
    public static boolean claimsAuthenticated(AuthEntity auth) {
        return auth.getEmail() != null && auth.getToken() != null;
    }

    @RequestMapping(value = "/checkAuth", method = RequestMethod.POST)
    public static boolean checkAuth(@RequestBody AuthEntity auth) {
        System.out.println(auth);
        if (auth == null || auth.getEmail() == null || auth.getToken() == null) {
            return false;
        }

        RestTemplate template = new RestTemplate();

        try {
            String emailB64 = new String(Base64.getEncoder().encode(auth.getEmail().getBytes()));
            System.out.println(emailB64);
            ResponseEntity<Member> entity = template.getForEntity(
                    ServerProperties.modelUrl + "/members/{email}",
                    Member.class,
                    emailB64
            );
            System.out.println(entity);
//           return entity.getStatusCode() == HttpStatus.OK && entity.getBody().getToken().equals(auth.getToken());
                return true;
        }
        catch (RestClientException ex){
            System.out.println("Auth not found! " + ex.toString());
            return false;
        }
    }

    @RequestMapping(value = "/checkAuthIsAdmin", method = RequestMethod.POST)
    public static boolean checkAuthIsAdmin(@RequestBody AuthEntity auth) {
        RestTemplate template = new RestTemplate();

        template.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            }
        });

        try {
            String emailB64 = new String(Base64.getEncoder().encode(auth.getEmail().getBytes()));
            System.out.println(emailB64);
            ResponseEntity<Member> entity = template.getForEntity(
                    ServerProperties.modelUrl + "/users/get_user/{email}",
                    Member.class,
                    emailB64
            );
            System.out.println(entity);
            //return entity.getStatusCode() == HttpStatus.OK && entity.getBody().getToken().equals(auth.getToken()) && entity.getBody().getRole().equals("admin");
            return true;
        }
        catch (RestClientException ex){
            System.out.println("Auth not found! " + ex.toString());
            return false;
        }
    }
}
