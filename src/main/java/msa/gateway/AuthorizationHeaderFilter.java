package msa.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {
    @Override
    public GatewayFilter apply(NameConfig config) {
        return null;
    }
}
