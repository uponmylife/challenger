package june.controller;

import june.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
    @Autowired
    private CalendarService tableBuilder;

    @RequestMapping("/toggle/{day}/{slot}")
    @ResponseBody
    public Boolean toggle(@PathVariable String day, @PathVariable Integer slot) {
        return tableBuilder.toggle(day, slot);
    }
}
