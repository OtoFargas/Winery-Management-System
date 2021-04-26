package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.dto.WineCreateDTO;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import cz.muni.fi.pa165.facade.WineFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.FeedbackService;
import cz.muni.fi.pa165.service.HarvestService;
import cz.muni.fi.pa165.service.WineService;
import org.dozer.inject.Inject;
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

    @Inject
    private WineService wineService;

    @Inject
    private HarvestService harvestService;

    @Inject
    private FeedbackService feedbackService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createWine(WineCreateDTO wineCreateDTO) {
        Wine wine = new Wine();
        wine.setName(wineCreateDTO.getName());
        wineService.createWine(wine);
        return wine.getId();
    }

    @Override
    public WineDTO getWineById(Long id) {
        Wine wine = wineService.findWineById(id);
        return (wine == null) ? null : beanMappingService.mapTo(wine, WineDTO.class);
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
