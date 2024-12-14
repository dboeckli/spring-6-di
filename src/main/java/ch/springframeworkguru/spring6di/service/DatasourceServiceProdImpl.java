package ch.springframeworkguru.spring6di.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class DatasourceServiceProdImpl implements DatasourceService {
    @Override
    public String getDatasourceName() {
        return "prod";
    }
}
