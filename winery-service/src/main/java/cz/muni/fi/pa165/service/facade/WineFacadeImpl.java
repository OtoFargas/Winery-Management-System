package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.WineBuyDTO;
import cz.muni.fi.pa165.dto.WineCreateDTO;
import cz.muni.fi.pa165.dto.WineDTO;
import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.facade.WineFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.FeedbackService;
import cz.muni.fi.pa165.service.HarvestService;
import cz.muni.fi.pa165.service.WineService;
import javax.inject.Inject;
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
        Wine wine = beanMappingService.mapTo(wineCreateDTO, Wine.class);
        wineService.createWine(wine);

        for (Long id : wineCreateDTO.getHarvestIDs()) {
            Harvest harvestById = harvestService.findHarvestById(id);
            harvestById.setWine(wine);
            wine.addHarvest(harvestById);

        }

        return wine.getId();
    }

    @Override
    public WineDTO findWineById(Long id) {
        Wine wine = wineService.findWineById(id);
        return (wine == null) ? null : beanMappingService.mapTo(wine, WineDTO.class);
    }

    @Override
    public List<WineDTO> findAllWines() {
        return beanMappingService.mapTo(wineService.findAllWines(), WineDTO.class);
    }

    @Override
    public WineDTO findWineByName(String name) {
        Wine wine = wineService.findWineByName(name);
        return (wine == null) ? null : beanMappingService.mapTo(wine, WineDTO.class);
    }

    @Override
    public void updateWine(WineDTO wineDTO) {
        Wine wine = beanMappingService.mapTo(wineDTO, Wine.class);
        wineService.updateWine(wine);
    }

    @Override
    public void removeWine(Long wineID) {
        wineService.removeWine(wineService.findWineById(wineID));
    }

    @Override
    public void sellWine(WineBuyDTO wineBuyDTO) {
        Wine wine = wineService.findWineById(wineBuyDTO.getId());
        wineService.sellWine(wine, wineBuyDTO.getAmount());
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
