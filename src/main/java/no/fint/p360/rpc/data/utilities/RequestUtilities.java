package no.fint.p360.rpc.data.utilities;

import no.fint.p360.AdapterProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.ws.BindingProvider;
import java.util.Map;

@Component
public class RequestUtilities {

    /*@Autowired
    private AdapterProps appProps;

    public void addAuthentication(Map<String, Object> map) {
        map.put(BindingProvider.USERNAME_PROPERTY, appProps.getP360User());
        map.put(BindingProvider.PASSWORD_PROPERTY, appProps.getP360Password());
    }

    public void setEndpointAddress(Map<String, Object> map, String service) {
        map.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                String.format("%s/SI.WS.Core/SIF/%s.svc", appProps.getEndpointBaseUrl(), service));
    }*/
}
