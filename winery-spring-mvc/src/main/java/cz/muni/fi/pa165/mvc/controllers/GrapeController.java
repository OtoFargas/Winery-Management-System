package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.GrapeCreateDTO;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.facade.GrapeFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * SpringMVC controller for grape entities
 *
 * @author Lukáš Fudor
 */

@Controller
@RequestMapping("/grape")
public class GrapeController {

    final static Logger log = LoggerFactory.getLogger(GrapeController.class);

    @Autowired
    private GrapeFacade grapeFacade;

    /**
     * TODO
     *
     * @param model to be displayed
     * @return page name
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newGrape(Model model) {
        log.debug("new()");
        model.addAttribute("grapeCreate", new GrapeCreateDTO());
        return "grape/new";
    }

    /**
     * TODO
     *
     * @param formBean data for grape creation
     * @param bindingResult
     * @param model
     * @param redirectAttributes attributes for redirect scenario
     * @param uriBuilder sets URI components
     * @return page grape/new if failed, page grape/all else
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createGrape(@Valid @ModelAttribute("grapeCreate") GrapeCreateDTO formBean, BindingResult bindingResult,
                              Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        log.debug("create(formBean={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "grape/new";
        }

        Long id = grapeFacade.createGrape(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Grape " + id + " was created");
        return "redirect:" + uriBuilder.path("/grape/list").toUriString();
    }

    /**
     * TODO
     *
     * @param id of the grape to be viewed
     * @param model data to be displayed
     * @return page name
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewById(@PathVariable long id, Model model) {
        log.debug("viewById({})", id);
        model.addAttribute("grape", grapeFacade.findGrapeById(id));
        return "grape/view";
    }

    /**
     * TODO
     *
     * @param name of the grape to be viewed
     * @param model data to be displayed
     * @return page name
     */
    @RequestMapping(value = "/view/{name}", method = RequestMethod.GET)
    public String viewByName(@PathVariable String name, Model model) {
        log.debug("viewByName({})", name);
        model.addAttribute("grape", grapeFacade.findGrapeByName(name));
        return "grape/view";
    }

    /**
     * TODO
     *
     * @param model to be displayed
     * @return page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAllGrapes(Model model) {
        model.addAttribute("grapes", grapeFacade.findAllGrapes());
        return "grape/list";
    }

    /**
     * TODO
     *
     * @param model to be displayed
     * @param color to be filtered by
     * @return page name
     */
    @RequestMapping(value = "/list/{color}", method = RequestMethod.GET)
    public String listGrapesWithColor(Model model,@PathVariable GrapeColor color) {
        model.addAttribute("grapes", grapeFacade.findGrapesByColor(color));
        return "grape/list";
    }

    /**
     * TODO
     *
     * @param id of the grape to be removed
     * @param uriBuilder
     * @param redirectAttributes
     * @return page name
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        GrapeDTO grape = grapeFacade.findGrapeById(id);
        log.debug("remove({})", id);

        try {
            grapeFacade.removeGrape(id);
            redirectAttributes.addFlashAttribute("alert_success", "Grape \"" + grape.getId() + ":" + grape.getName()
                                                + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Grape \"" + grape.getName() + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/grape/list").toUriString();
    }
}
