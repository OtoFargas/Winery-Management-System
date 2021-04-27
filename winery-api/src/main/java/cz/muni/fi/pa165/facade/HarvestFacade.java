package cz.muni.fi.pa165.facade;

import java.util.List;

import cz.muni.fi.pa165.dto.HarvestDTO;

/**
 * @author Vladimir Visnovsky
 */
public interface HarvestFacade {
    /**
     * Create new harvest
     * @param harvestDTO to be created
     */
    void createHarvest(HarvestDTO harvestDTO);

    /**
     * Find all harvests
     * @return list of all DTO harvests
     */
    List<HarvestDTO> findAllHarvests();

    /**
     * Find required harvest by it's id
     * @param id of desired harvest
     * @return found DTO harvest
     */
    HarvestDTO findHarvestById(Long id);

    /**
     * @param year of desired harvest
     * @return list of found DTO harvests
     */
    List<HarvestDTO> findHarvestByYear(Integer year);

    /**
     * @param harvestDTO to be updated
     */
    void updateHarvest(HarvestDTO harvestDTO);

    /**
     * @param id of harvest to be deleted
     */
    void removeHarvest(Long id);
}
