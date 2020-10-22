package hungthinh.htid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController2 {
    @GetMapping("/user2")
    public String user() {return "user/index";}

}
