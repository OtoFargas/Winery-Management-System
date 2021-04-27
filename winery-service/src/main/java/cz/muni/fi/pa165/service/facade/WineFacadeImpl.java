package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.WineCreateDTO;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.facade.WineFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.FeedbackService;
import cz.muni.fi.pa165.service.GrapeService;
import cz.muni.fi.pa165.service.HarvestService;
import cz.muni.fi.pa165.service.WineService;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements WineFacade interface
 *
 * @author Lukáš Fudor
 */
@Service
@Transactional
public class WineFacadeImpl implements WineFacade {

    private final WineService wineService;
    private final HarvestService harvestService;
    private final FeedbackService feedbackService;

    private final BeanMappingService beanMappingService;

    @Inject
    public WineFacadeImpl(WineService wineService, FeedbackService feedbackService, HarvestService harvestService, BeanMappingService beanMappingService) {
        this.wineService = wineService;
        this.harvestService = harvestService;
        this.feedbackService = feedbackService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public Long createWine(WineCreateDTO wineCreateDTO) {
        Wine wine = new Wine();
        wine.setName(wineCreateDTO.getName());
        wine.setType(wineCreateDTO.getType());
        wine.setStocked(wineCreateDTO.getStocked());
        wine.setSold(wineCreateDTO.getSold());
        wine.setIngredients(wineCreateDTO.getIngredients());
        
        wineService.createWine(wine);
        return wine.getId();
    }

    @Override
    public WineDTO getWineById(Long id) {
        Wine wine = wineService.findWineById(id);
        return (wine == null) ? null : beanMappingService.mapTo(wine, WineDTO.class);
    }

    @Override
    public void updateWine(WineDTO wineDTO) {
        Wine wine = beanMappingService.mapTo(wineDTO, Wine.class);
        wineService.updateWine(wine);
    }

    @Override
    public void deleteWine(Long wineID) {
        wineService.removeWine(wineService.findWineById(wineID));
    }

    @Override
    public List<WineDTO> getAllWines() {
        return beanMappingService.mapTo(wineService.findAllWines(), WineDTO.class);
    }

    @Override
    public void sellWine(Long wineID, Integer amount) {
        Wine wine = wineService.findWineById(wineID);
        wineService.sellWine(wine, amount);
    }

    @Override
    public void addFeedback(Long feedbackID, Long wineID) {
        Feedback feedback = feedbackService.findFeedbackById(feedbackID);
        Wine wine = wineService.findWineById(wineID);
        wineService.addFeedbackToWine(wine, feedback);
    }

    @Override
    public void addHarvest(Long harvestID, Long wineID) {
        Harvest harvest = harvestService.findHarvestById(harvestID);
        Wine wine = wineService.findWineById(wineID);
        wineService.addHarvestToWine(wine, harvest);
    }
}
