package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.HarvestCreateDTO;
import cz.muni.fi.pa165.dto.HarvestDTO;
import cz.muni.fi.pa165.facade.HarvestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * SpringMVC controller for harvest entities
 *
 * @author Oto Fargas
 */

@Controller
@RequestMapping("/harvest")
public class HarvestController {

    final static Logger log = LoggerFactory.getLogger(HarvestController.class);

    @Autowired
    private HarvestFacade harvestFacade;

    /**
     * TODO
     *
     * @param model to be displayed
     * @return page name
     */
    @GetMapping(value = "/new")
    public String newHarvest(Model model) {
        log.debug("new()");
        model.addAttribute("harvestCreate", new HarvestCreateDTO());
        return "harvest/new";
    }

    /**
     * TODO
     *
     * @param formBean data for harvest creation
     * @param bindingResult
     * @param model
     * @param redirectAttributes attributes for redirect scenario
     * @param uriBuilder sets URI components
     * @return page harvest/new if failed, page harvest/all else
     */
    @PostMapping(value = "/create")
    public String createHarvest(@Valid @ModelAttribute("harvestCreate") HarvestCreateDTO formBean, BindingResult bindingResult,
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
            return "harvest/new";
        }

        Long id = harvestFacade.createHarvest(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Harvest " + id + " was created");
        return "redirect:" + uriBuilder.path("/harvest/list").toUriString();
    }

    /**
     * TODO
     *
     * @param id of the harvest to be viewed
     * @param model data to be displayed
     * @return page name of the view of the harvest
     */
    @GetMapping(value = "/view/{id}")
    public String viewById(@PathVariable long id, Model model) {
        log.debug("viewById({})", id);
        model.addAttribute("harvest", harvestFacade.findHarvestById(id));
        return "harvest/view";
    }

    /**
     * TODO
     *
     * @param year of the harvest to be viewed
     * @param model data to be displayed
     * @return page name of the view of the harvest
     */
    @GetMapping("/view/{name}")
    public String viewByName(@PathVariable Integer year, Model model) {
        log.debug("viewByYear({})", year);
        model.addAttribute("harvest", harvestFacade.findHarvestsByYear(year));
        return "harvest/view";
    }

    /**
     * TODO
     *
     * @param model to be displayed
     * @return page name of all the harvests
     */
    @GetMapping("/list")
    public String listAllHarvests(Model model) {
        model.addAttribute("harvests", harvestFacade.findAllHarvests());
        return "harvest/list";
    }

    /**
     * TODO
     *
     * @param id of the harvest to be removed
     * @param uriBuilder
     * @param redirectAttributes
     * @return page name of all the other harvests
     */
    @PostMapping(value = "/remove/{id}")
    public String remove(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        HarvestDTO harvest = harvestFacade.findHarvestById(id);
        log.debug("remove({})", id);

        try {
            harvestFacade.removeHarvest(id);
            redirectAttributes.addFlashAttribute("alert_success", "Harvest \"" + harvest.getId() + ":" + harvest.getHarvestYear()
                                                + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Harvest \"" + harvest.getId() + ":" + harvest.getHarvestYear()
                                                + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/harvest/list").toUriString();
    }
}
