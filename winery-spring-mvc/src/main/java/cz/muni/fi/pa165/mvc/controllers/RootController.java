package cz.muni.fi.pa165.mvc.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.WineFacade;

/**
 * MVC Controller for root home page.
 *
 * @author Vladimir Visnovsky
 */
@Controller
@RequestMapping("/")
public class RootController {

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
