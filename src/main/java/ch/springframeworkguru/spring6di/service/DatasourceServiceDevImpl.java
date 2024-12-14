package ch.springframeworkguru.spring6di.service;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "default"})
@Primary
public class DatasourceServiceDevImpl implements DatasourceService {

    @Override
    public String getDatasourceName() {
        return "dev";
    }

}
