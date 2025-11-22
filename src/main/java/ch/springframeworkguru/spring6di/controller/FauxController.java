package ch.springframeworkguru.spring6di.controller;

import ch.springframeworkguru.spring6di.service.DatasourceService;
import org.springframework.stereotype.Controller;

@Controller
public class FauxController {
    private final DatasourceService datasourceService;

    public FauxController(DatasourceService datasourceService) {
        this.datasourceService = datasourceService;
    }

    public String getDatasource() {
        return datasourceService.getDatasourceName();
    }
}
