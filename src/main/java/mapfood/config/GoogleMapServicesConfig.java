package mapfood.config;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleMapServicesConfig {

    @Bean
    public GeoApiContext geoApiContext(@Value("integration.google-api.secret-key") String secretKey) {
        return new GeoApiContext.Builder()
                .apiKey(secretKey)
                .build();
    }

}
