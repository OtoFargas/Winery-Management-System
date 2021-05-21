package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.GrapeCreateDTO;
import cz.muni.fi.pa165.dto.GrapeDiseaseDTO;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.dto.HarvestDTO;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.facade.GrapeFacade;
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

    @Autowired
    private HarvestFacade harvestFacade;

    /**
     * TODO
     *
     * @param model to be displayed
     * @return page name
     */
    @GetMapping("/new")
    public String newGrape(Model model) {
        log.debug("new()");
        model.addAttribute("grapeCreate", new GrapeCreateDTO());
        return "grape/new";
    }

    /**
     * TODO
     *
     * @param formBean           data for grape creation
     * @param bindingResult
     * @param model
     * @param redirectAttributes attributes for redirect scenario
     * @param uriBuilder         sets URI components
     * @return page grape/new if failed, page grape/list else
     */
    @PostMapping("/create")
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
     * @param id    of the grape to be viewed
     * @param model data to be displayed
     * @return page name of the view of the grape
     */
    @GetMapping("/view/{id}")
    public String viewById(@PathVariable long id, Model model) {
        log.debug("viewById({})", id);
        model.addAttribute("grape", grapeFacade.findGrapeById(id));
        model.addAttribute("setDisease", new GrapeDiseaseDTO());
        return "grape/view";
    }

    /**
     * TODO
     *
     * @param name  of the grape to be viewed
     * @param model data to be displayed
     * @return page name of the view of the grape
     */
    @GetMapping("/viewByName/{name}")
    public String viewByName(@PathVariable String name, Model model) {
        log.debug("viewByName({})", name);
        model.addAttribute("grape", grapeFacade.findGrapeByName(name));
        return "grape/view";
    }

    /**
     * TODO
     *
     * @param model to be displayed
     * @return page name of all the grapes
     */
    @GetMapping("/list")
    public String listAllGrapes(Model model) {
        model.addAttribute("grapes", grapeFacade.findAllGrapes());
        return "grape/list";
    }

    /**
     * TODO
     *
     * @param model to be displayed
     * @param color to be filtered by
     * @return page name of all the grapes
     */
    @GetMapping("/list/{color}")
    public String listGrapesWithColor(Model model, @PathVariable GrapeColor color) {
        model.addAttribute("grapes", grapeFacade.findGrapesByColor(color));
        return "grape/list";
    }

    /**
     * TODO
     *
     * @param id of the grape to be removed
     * @param uriBuilder
     * @param redirectAttributes
     * @return page name of all the other grapes
     */
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        GrapeDTO grape = grapeFacade.findGrapeById(id);
        log.debug("remove({})", id);

        try {
            grapeFacade.removeGrape(id);
            redirectAttributes.addFlashAttribute("alert_success", "Grape " + grape.getId() + ": \"" + grape.getName()
                                                + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Grape " + grape.getId() + ": \"" + grape.getName()
                                                + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/grape/list").toUriString();
    }

    /**
     * TODO
     *
     * @param id of the grape to be added to
     * @param harvestid of the harvest to be added
     * @param uriBuilder
     * @param redirectAttributes
     * @return page name of the view of the grape
     */
    @PostMapping("/addHarvest/{id}/{harvestid}")
    public String addHarvest(@PathVariable long id, @PathVariable long harvestid,
                             UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            grapeFacade.addHarvest(harvestid, id);
            redirectAttributes.addFlashAttribute("alert_success", "Harvest number " + harvestid
                    + " was added to the grape number" + id + ".");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Harvest number " + harvestid
                    + " was NOT added to the grape number" + id + "." + e.getMessage());
        }

        return "redirect:" + uriBuilder.path("/grape/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    /**
     * TODO
     *
     * @param id of the grape to be cured
     * @param uriBuilder
     * @param redirectAttributes
     * @return page name of the view of the grape
     */
    @GetMapping("/cureAllDiseases/{id}")
    public String cureAll(@PathVariable long id, UriComponentsBuilder uriBuilder,
                          RedirectAttributes redirectAttributes) {
        try {
            grapeFacade.cureAllDiseases(id);
            redirectAttributes.addFlashAttribute("alert_success", "Grape number was cured of all the diseases.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Grape number " + id
                                                + " was not cured. " + e.getMessage());
        }

        return "redirect:" + uriBuilder.path("/grape/view/{id}").buildAndExpand(id).encode().toUriString();
    }


    @PostMapping(value = "/setDisease/{id}", params = "cure")
    public String cureDisease(@Valid @ModelAttribute("setDisease") GrapeDiseaseDTO formBean,
                              BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                              UriComponentsBuilder uriBuilder, @PathVariable long id) {

        log.debug("cureGrape(formBean={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            redirectAttributes.addFlashAttribute("alert_danger", "Grape " + id + " could not have been cured.");
            return "redirect:" + uriBuilder.path("/grape/view/{id}").buildAndExpand(id).encode().toUriString();
        }
        formBean.setId(id);
        grapeFacade.cureDisease(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Grape " + id + " was cured");
        return "redirect:" + uriBuilder.path("/grape/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @PostMapping(value = "/setDisease/{id}", params = "add")
    public String addDisease(@Valid @ModelAttribute("setDisease") GrapeDiseaseDTO formBean,
                              BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                              UriComponentsBuilder uriBuilder, @PathVariable long id) {

        log.debug("cureGrape(formBean={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            redirectAttributes.addFlashAttribute("alert_danger", "Disease " + formBean.getDisease()
                                                + "could not have been added to grape with ID: " + id + ".");
            return "redirect:" + uriBuilder.path("/grape/view/{id}").buildAndExpand(id).encode().toUriString();
        }
        formBean.setId(id);
        grapeFacade.addDisease(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Disease " + formBean.getDisease()
                                            + "was added to grape with ID: " + id + ".");
        return "redirect:" + uriBuilder.path("/grape/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @ModelAttribute("colors")
    public GrapeColor[] colors() {
        log.debug("colors()");
        return GrapeColor.values();
    }

    @ModelAttribute("diseases")
    public Disease[] diseases() {
        log.debug("diseases()");
        return Disease.values();
    }

    @ModelAttribute("harvests")
    public HarvestDTO[] harvests() {
        log.debug("harvests()");
        return harvestFacade.findAllHarvests().toArray(new HarvestDTO[0]);
    }
}
