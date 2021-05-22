package cz.muni.fi.pa165.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Vladimir Visnovsky
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    final static Logger log = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin/home";
    }
}
