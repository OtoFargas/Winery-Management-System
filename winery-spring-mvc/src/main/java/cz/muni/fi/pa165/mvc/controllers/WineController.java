package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.WineBuyDTO;
import cz.muni.fi.pa165.dto.WineCreateDTO;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * SpringMVC controller for wine entities
 *
 * @author Oto Fargas
 */

@Controller
@RequestMapping("/wine")
public class WineController {

    final static Logger log = LoggerFactory.getLogger(WineController.class);

    @Autowired
    private WineFacade wineFacade;

    /**
     * TODO
     *
     * @param model to be displayed
     * @return page name
     */
    @GetMapping(value = "/new")
    public String newWine(Model model) {
        log.debug("new()");
        model.addAttribute("wineCreate", new WineCreateDTO());
        return "wine/new";
    }

    /**
     * TODO
     *
     * @param formBean data for wine creation
     * @param bindingResult
     * @param model
     * @param redirectAttributes attributes for redirect scenario
     * @param uriBuilder sets URI components
     * @return page wine/new if failed, page wine/all else
     */
    @PostMapping(value = "/create")
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

        Long id = wineFacade.createWine(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Wine " + id + " was created");
        return "redirect:" + uriBuilder.path("/wine/list").toUriString();
    }

    /**
     * TODO
     *
     * @param id of the wine to be viewed
     * @param model data to be displayed
     * @return page name of the view of the wine
     */
    @GetMapping(value = "/view/{id}")
    public String viewById(@PathVariable long id, Model model) {
        log.debug("viewById({})", id);
        model.addAttribute("wine", wineFacade.findWineById(id));
        return "wine/view";
    }

    /**
     * TODO
     *
     * @param name  of the wine to be viewed
     * @param model data to be displayed
     * @return page name of the view of the wine
     */
    @GetMapping("/viewByName/{name}")
    public String viewByName(@PathVariable String name, Model model) {
        log.debug("viewByName({})", name);
        model.addAttribute("wine", wineFacade.findWineByName(name));
        return "wine/view";
    }

    /**
     * TODO
     *
     * @param model to be displayed
     * @return page name of all the wines
     */
    @GetMapping("/list")
    public String listAllWines(Model model) {
        model.addAttribute("wines", wineFacade.findAllWines());
        return "wine/list";
    }

    /**
     * TODO
     *
     * @param id of the wine to be removed
     * @param uriBuilder
     * @param redirectAttributes
     * @return page name of all the other wines
     */
    @GetMapping(value = "/remove/{id}")
    public String remove(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        WineDTO wine = wineFacade.findWineById(id);
        log.debug("remove({})", id);

        try {
            wineFacade.removeWine(id);
            redirectAttributes.addFlashAttribute("alert_success", "Wine " + wine.getId() + ": \"" + wine.getName()
                                                + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Wine " + wine.getId() + ": \"" + wine.getName()
                                                + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/wine/list").toUriString();
    }

    /**
     * TODO
     *
     * @param id of the wine to be added to
     * @param harvestid of the harvest to be added
     * @param uriBuilder
     * @param redirectAttributes
     * @return page name of the view of the wine
     */
    @PostMapping("/addHarvest/{id}/{harvestid}")
    public String addHarvest(@PathVariable long id, @PathVariable long harvestid,
                             UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            wineFacade.addHarvest(harvestid, id);
            redirectAttributes.addFlashAttribute("alert_success", "Harvest number " + harvestid
                    + " was added to the wine number" + id + ".");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Harvest number " + harvestid
                    + " was NOT added to the wine number" + id + "." + e.getMessage());
        }

        return "redirect:" + uriBuilder.path("/wine/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    /**
     * TODO
     *
     * @param id of the wine to be added to
     * @param feedbackid of the feedback to be added
     * @param uriBuilder
     * @param redirectAttributes
     * @return page name of the view of the wine
     */
    @PostMapping("/addFeedback/{id}/{feedbackid}")
    public String addFeedback(@PathVariable long id, @PathVariable long feedbackid,
                             UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            wineFacade.addFeedback(feedbackid, id);
            redirectAttributes.addFlashAttribute("alert_success", "Feedback number " + feedbackid
                    + " was added to the wine number" + id + ".");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Feedback number " + feedbackid
                    + " was NOT added to the wine number" + id + "." + e.getMessage());
        }

        return "redirect:" + uriBuilder.path("/wine/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    /**
     * TODO
     *
     * @param id of the wine to be sold
     * @param amount of wine to be sold
     * @param uriBuilder
     * @param redirectAttributes
     * @return page name of the view of the wine
     */
    @PutMapping("/sellWine/{id}/{amount}")
    public String sellWine(@PathVariable long id, @PathVariable Integer amount,
                              UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {

        try {
            WineBuyDTO wineBuyDTO = new WineBuyDTO();
            wineBuyDTO.setId(id);
            wineBuyDTO.setAmount(amount);

            wineFacade.sellWine(wineBuyDTO);
            redirectAttributes.addFlashAttribute("alert_success", "Wine with id " + id
                    + " was sold. Amount: " + amount);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Wine with id " + id
                    + " was not sold. " + e.getMessage());
        }

        return "redirect:" + uriBuilder.path("/wine/view/{id}").buildAndExpand(id).encode().toUriString();
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
}
