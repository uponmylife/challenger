package june.controller;

import com.google.common.collect.Lists;
import june.model.Subject;
import june.repository.SubjectRepository;
import june.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class TemplateController {
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        return index(null, model);
    }

    @RequestMapping("/{subjectId}")
    public String index(@PathVariable Long subjectId, Map<String, Object> model) {
        List<Subject> subjects = Lists.newArrayList(subjectRepository.findAll());
        if (subjectId == null) subjectId = subjects.get(0).getId();
        model.put("subjectId", subjectId);
        model.put("subjects", subjects);
        model.put("cells", calendarService.show(subjectId, 1));
        return "index";
    }

}
