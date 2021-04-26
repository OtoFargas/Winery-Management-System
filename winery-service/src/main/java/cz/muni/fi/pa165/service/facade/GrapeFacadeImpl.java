package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.GrapeCreateDTO;
import cz.muni.fi.pa165.dto.GrapeDTO;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.facade.GrapeFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.GrapeService;
import org.dozer.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class implements GrapeFacade interace
 *
 * @author Lukáš Fudor
 */
@Service
@Transactional
public class GrapeFacadeImpl implements GrapeFacade {

    @Inject
    private GrapeService productService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<GrapeDTO> getAllGrapes() {
        return null;
    }

    @Override
    public GrapeDTO getGrapeById(Long id) {
        return null;
    }

    @Override
    public List<GrapeDTO> getGrapesByColor(GrapeColor grapeColor) {
        return null;
    }

    @Override
    public void deleteGrape(Long id) {

    }

    @Override
    public Long createGrape(GrapeCreateDTO grapeCreateDTO) {
        return null;
    }

    @Override
    public void addHarvest(Long harvestID, Long GrapeID) {

    }

    @Override
    public void removeHarvest(Long harvestID, Long GrapeID) {

    }
}
