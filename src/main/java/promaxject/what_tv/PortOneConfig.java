package promaxject.what_tv;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortOneConfig {


    @Value("${imp.v1.api.key}")
    String apiKey;

    @Value("${imp.v1.api.secret}")
    String secretKey;

    @Bean
    public IamportClient iamPortClient() {
        return new IamportClient(apiKey, secretKey);
    }
}
