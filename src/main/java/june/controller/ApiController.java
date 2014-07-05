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
    private CalendarService calendarService;

    @RequestMapping("/toggle")
    @ResponseBody
    public Boolean toggle(Long subjectId, String day, Integer slot) {
        return calendarService.toggle(subjectId, day, slot);
    }
}
