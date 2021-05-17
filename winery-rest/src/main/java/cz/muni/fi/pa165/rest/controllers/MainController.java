package cz.muni.fi.pa165.rest.controllers;


import cz.muni.fi.pa165.rest.ApiUris;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Vladimir Visnovsky
 */
@RestController
public class MainController {
    
    final static Logger logger = LoggerFactory.getLogger(MainController.class);
    
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String,String> resourcesMap = new HashMap<>();
        
        resourcesMap.put("feedbacks_uri", ApiUris.ROOT_URI_FEEDBACKS);
        resourcesMap.put("grapes_uri", ApiUris.ROOT_URI_GRAPES);
        resourcesMap.put("harvests_uri", ApiUris.ROOT_URI_HARVESTS);
        resourcesMap.put("wines_uri", ApiUris.ROOT_URI_WINES);
        
        return Collections.unmodifiableMap(resourcesMap);
    }
}
