package june.controller;

import com.google.common.collect.Lists;
import june.controller.form.SubjectForm;
import june.model.Subject;
import june.repository.RecordRepository;
import june.repository.SubjectRepository;
import june.service.CalendarService;
import june.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class DefaultController {
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private ReportService reportService;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        return index(null, null, model);
    }

    @RequestMapping("/{subjectId}")
    public String index(@PathVariable Long subjectId, Integer weekAgo, Map<String, Object> model) {
        List<Subject> subjects = Lists.newArrayList(subjectRepository.findAll());
        if (subjects.size() == 0) {
            model.put("subject", new Subject());
            return "form";
        }
        if (weekAgo == null) weekAgo = 1;
        if (subjectId == null) subjectId = subjects.get(0).getId();

        Subject subject = subjectRepository.findOne(subjectId);
        reportService.analyze(subject);

        model.put("weekAgo", weekAgo);
        model.put("subjectId", subjectId);
        model.put("subjects", subjects);
        model.put("subject", subject);
        model.put("cells", calendarService.show(subjectId, weekAgo));
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
        Subject subject = new Subject();
        if (subjectForm.getSubjectId() != null) subject = subjectRepository.findOne(subjectForm.getSubjectId());
        subject.setSubjectForm(subjectForm);
        subjectRepository.save(subject);
        return "redirect:/" + subject.getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    @ResponseBody
    public Boolean delete(Long subjectId) {
        log.info("delete() subjectId=" + subjectId);
        recordRepository.removeBySubjectId(subjectId);
        subjectRepository.delete(subjectId);
        return true;
    }

    @RequestMapping("/toggle")
    @ResponseBody
    public Boolean toggle(Long subjectId, String day, Integer slot) {
        return calendarService.toggle(subjectId, day, slot);
    }
}
