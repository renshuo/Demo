package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sren on 16-11-3.
 */
@Controller
public class MainPage {


    @RequestMapping("/main")
    public String index(ModelMap map){
        map.addAttribute("taskId", "abcd");
        return "main/index";
    }
}
