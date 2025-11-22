package ch.springframeworkguru.spring6di.rest;

import ch.springframeworkguru.spring6di.service.DatasourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DatasourceRestController {
    private final DatasourceService datasourceService;

    public DatasourceRestController(DatasourceService datasourceService) {
        this.datasourceService = datasourceService;
    }

    @GetMapping("/datasource")
    public ResponseEntity<String> getDatasource() {
        log.info("Datasource requested");
        return ResponseEntity.ok(datasourceService.getDatasourceName());
    }
}
