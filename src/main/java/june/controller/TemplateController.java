package june.controller;

import june.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class TemplateController {
    @Autowired
    private CalendarService tableBuilder;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("cells", tableBuilder.show(1));
        return "index";
    }

}
