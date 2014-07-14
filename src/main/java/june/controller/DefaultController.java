package june.controller;

import com.google.common.collect.Lists;
import june.controller.form.SubjectForm;
import june.model.Subject;
import june.repository.RecordRepository;
import june.repository.SubjectRepository;
import june.service.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Slf4j
@Controller
public class DefaultController {
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private RecordRepository recordRepository;

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

    @RequestMapping(method = RequestMethod.GET, value = "/{subjectId}/form")
    public String form(@PathVariable Long subjectId, Map<String, Object> model) {
        Subject subject = subjectRepository.findOne(subjectId);
        model.put("subject", subject);
        model.put("subjectId", subjectId);
        return "form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String save(SubjectForm subjectForm, Map<String, Object> model) {
        log.info("save() subjectForm=" + subjectForm);
        Subject subject = subjectForm.createModel();
        subjectRepository.save(subject);
        return "redirect:/" + subject.getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    @ResponseBody
    public String delete(Long subjectId) {
        log.info("delete() subjectId=" + subjectId);
        subjectRepository.delete(subjectId);
        recordRepository.removeBySubjectId(subjectId);
        return "redirect:/";
    }

    @RequestMapping("/toggle")
    @ResponseBody
    public Boolean toggle(Long subjectId, String day, Integer slot) {
        return calendarService.toggle(subjectId, day, slot);
    }
}
