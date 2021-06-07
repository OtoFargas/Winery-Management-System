package cz.muni.fi.pa165.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vladimir Visnovsky
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * Redirects to ../admin/home page.
     * @return page name
     */
    @GetMapping({"/", ""})
    public String admin() {
        return "admin/home";
    }
}
