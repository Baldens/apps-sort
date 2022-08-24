package baldens.halliburton.route.http;

import baldens.halliburton.model.http.TextSort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Main {

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }
}
