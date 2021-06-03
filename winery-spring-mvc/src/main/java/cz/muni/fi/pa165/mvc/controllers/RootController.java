package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.FeedbackCreateDTO;
import cz.muni.fi.pa165.dto.FeedbackDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.WineBuyDTO;
import cz.muni.fi.pa165.exceptions.WineryServiceException;
import cz.muni.fi.pa165.facade.FeedbackFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.facade.WineFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Set;

/**
 * MVC Controller for root home page.
 *
 * @author Vladimir Visnovsky
 */
@Controller
@RequestMapping("/")
public class RootController {

    final static Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private WineFacade wineFacade;

    /**
     * Redirects to the home page containing all the wines.
     *
     * @param model page data
     * @return      page name
     */
    @GetMapping("/")
    public String admin(Model model, HttpSession session) {
        model.addAttribute("wines", wineFacade.findAllWines());

        UserDTO user = (UserDTO) session.getAttribute("authenticatedUser");
        if (user == null) {
            return "home";
        }

        return user.isAdmin() ? "redirect:/admin" : "redirect:/user";
    }

    /**
     * Redirects to ../about page.
     *
     * @return page name
     */
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
