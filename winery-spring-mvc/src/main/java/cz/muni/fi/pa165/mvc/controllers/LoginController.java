package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author Oto Fargas
 */
@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserFacade userFacade;

    /**
     * GET method for user login.
     *
     * @param model   page data
     * @param session to check authenticatedUser
     * @return        page name
     */
    @GetMapping(value = "login")
    public String getLogin(Model model, HttpSession session) {
        if (session.getAttribute("authenticatedUser") != null) {
            return "redirect:/";
        }

        model.addAttribute("user", new UserAuthenticateDTO());
        return "/auth/login";
    }

    /**
     * POST method for user login.
     *
     * @param session             to set the authenticatedUser
     * @param userAuthDTO         transfers data from login form
     * @param bindingResult       -
     * @param redirectAttributes  attributes for redirect scenario
     * @return                    page name
     */
    @PostMapping(value = "login")
    public String postLogin(HttpSession session,
                            @Valid @ModelAttribute("user") UserAuthenticateDTO userAuthDTO,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        UserDTO userDTO = userFacade.findUserByUserName(userAuthDTO.getUserName());

        if (userDTO == null) {
            redirectAttributes.addFlashAttribute("login_failure", "User with this user name does not exist");
            return "redirect:/auth/login";
        }
        userAuthDTO.setId(userDTO.getId());

        if (!userFacade.authenticate(userAuthDTO)) {
            redirectAttributes.addFlashAttribute("login_failure", "Invalid password");
            return "redirect:/auth/login";
        }

        userDTO.setAdmin(userAuthDTO.getUserName().equals("admin"));
        
        session.setAttribute("authenticatedUser", userDTO);
        return userDTO.isAdmin() ? "redirect:/admin" : "redirect:/";
    }

    /**
     * Logouts the active user (authenticatedUser).
     *
     * @param session to check and remove authenticatedUser
     * @return        page name
     */
    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("authenticatedUser") == null) {
            return "redirect:/";
        }

        session.removeAttribute("authenticatedUser");
        return "redirect:/auth/login";
    }
}
