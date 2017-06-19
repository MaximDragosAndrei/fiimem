package front;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

public class TolerantRestTemplate extends RestTemplate {
    public TolerantRestTemplate() {
        setupErrorHandler();
    }

    public TolerantRestTemplate(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
        setupErrorHandler();
    }

    public TolerantRestTemplate(List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters);
        setupErrorHandler();
    }

    private void setupErrorHandler(){
        this.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

            }
        });
    }
}
