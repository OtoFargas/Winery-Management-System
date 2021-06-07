package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.HarvestDTO;
import cz.muni.fi.pa165.dto.WineCreateDTO;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import cz.muni.fi.pa165.facade.HarvestFacade;
import cz.muni.fi.pa165.facade.WineFacade;
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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * SpringMVC controller for wine entities
 *
 * @author Oto Fargas
 */

@Controller
@RequestMapping("/admin/wine")
public class WineController {

    final static Logger log = LoggerFactory.getLogger(WineController.class);

    @Autowired
    private WineFacade wineFacade;

    @Autowired
    private HarvestFacade harvestFacade;


    /**
     * Redirects to ../new page containing the form for the creation
     * of new wine.
     *
     * @param model page data
     * @return page name
     */
    @GetMapping("/new")
    public String newWine(Model model) {
        log.debug("new()");
        model.addAttribute("wineCreate", new WineCreateDTO());
        return "wine/new";
    }

    /**
     * Creates a new wine based on the information from the formBean.
     *
     * @param formBean           data from the form
     * @param bindingResult      -
     * @param model              page data
     * @param redirectAttributes attributes for redirect scenario
     * @param uriBuilder         sets URI components
     * @return                   page name
     */
    @PostMapping("/create")
    public String createWine(@Valid @ModelAttribute("wineCreate") WineCreateDTO formBean, BindingResult bindingResult,
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
            return "wine/new";
        }

        try {
            formBean.setWineYear(harvestFacade.findHarvestById(formBean.getHarvestIDs().iterator().next()).getHarvestYear());
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("alert_danger","No harvest selected.");
            return "redirect:" + uriBuilder.path("/admin/wine/new").encode().toUriString();
        }

        Long id;
        try {
            id = wineFacade.createWine(formBean);
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("alert_danger", "Wine with given name already exists.");
            return "redirect:" + uriBuilder.path("/admin/wine/new").toUriString();
        }

        redirectAttributes.addFlashAttribute("alert_success", "Wine " + id + " was created");
        return "redirect:" + uriBuilder.path("/admin/wine/list").toUriString();
    }

    /**
     * Redirects to ../view page of the wine with the given ID.
     *
     * @param id    of the wine to be viewed
     * @param model page data
     * @return      page name
     */
    @GetMapping("/view/{id}")
    public String viewById(@PathVariable long id, Model model) {
        log.debug("viewById({})", id);
        model.addAttribute("wine", wineFacade.findWineById(id));
        return "wine/view";
    }


    /**
     * Redirects to the ../viewByName page of the wine with given name.
     *
     * @param name  of the wine to be viewed
     * @param model page data
     * @return      page name
     */
    @GetMapping("/viewByName/{name}")
    public String viewByName(@PathVariable String name, Model model) {
        log.debug("viewByName({})", name);
        model.addAttribute("wine", wineFacade.findWineByName(name));
        return "wine/view";
    }

    /**
     * Redirects to ../wine/list page with all the wines.
     *
     * @param model page data
     * @return      page name
     */
    @GetMapping("/list")
    public String listAllWines(Model model) {
        model.addAttribute("wines", wineFacade.findAllWines());
        return "wine/list";
    }

    /**
     * Removes wine with given ID.
     *
     * @param id                 of the wine to be removed
     * @param uriBuilder         sets URI components
     * @param redirectAttributes attributes for redirect scenario
     * @return                   page name
     */
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        WineDTO wine = wineFacade.findWineById(id);
        log.debug("remove({})", id);

        try {
            wineFacade.removeWine(id);
            redirectAttributes.addFlashAttribute("alert_success", "Wine " + wine.getId() + ": \"" + wine.getName()
                                                + "\" was deleted.");
        } catch (Exception ex) {
            Long harvestId = wine.getHarvests().iterator().next().getId();
            redirectAttributes.addFlashAttribute("alert_danger", "Grape " + wine.getId() + ": \"" + wine.getName()
                    + "\" cannot be deleted. Delete harvest with ID: " + harvestId + " first.");
            return "redirect:" + uriBuilder.path("/admin/wine/view/{id}").buildAndExpand(id).encode().toUriString();
        }
        return "redirect:" + uriBuilder.path("/admin/wine/list").toUriString();
    }

    @ModelAttribute("taste")
    public Taste[] tastes() {
        log.debug("tastes()");
        return Taste.values();
    }

    @ModelAttribute("colors")
    public WineColor[] colors() {
        log.debug("colors()");
        return WineColor.values();
    }

    @ModelAttribute("ingredients")
    public Ingredient[] ingredients() {
        log.debug("ingredients()");
        return Ingredient.values();
    }

    @ModelAttribute("harvests")
    public HarvestDTO[] harvests() {
        log.debug("harvests()");

        List<HarvestDTO> availableHarvests = new ArrayList<>();
        List<HarvestDTO> allHarvests = harvestFacade.findAllHarvests();

        for (HarvestDTO harvest : allHarvests) {
            if (harvest.getWine() == null) {
                availableHarvests.add(harvest);
            }
        }

        return availableHarvests.toArray(new HarvestDTO[0]);
    }
}
