package mapfood.config;

import com.google.maps.GeoApiContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleMapServicesConfig {

    private final Logger logger = LoggerFactory.getLogger(GoogleMapServicesConfig.class);

    @Bean
    public GeoApiContext geoApiContext(@Value("${integration.google-api.secret-key}") String secretKey) {

        logger.info("Building geoApiContext with key: {}", secretKey);

        return new GeoApiContext.Builder()
                .apiKey(secretKey)
                .build();
    }

}
