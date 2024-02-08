package msa.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    @Autowired
    private JwtHelper jwtHelper;
    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            String getHeader = exchange.getRequest().getHeaders().get("Authorization").get(0);
            String token = getHeader.substring(7);
            String index = jwtHelper.getEmailFromJwtToken(token);

//            Consumer<HttpHeaders> headers = new Consumer<HttpHeaders>() {
//                @Override
//                public void accept(HttpHeaders httpHeaders) {
//                    httpHeaders.add("Authorization", getHeader);
//                    httpHeaders.add("Authorization-Id", index);
//                }
//            };

            //exchange.getRequest().mutate().headers(headers).build();
            exchange.getRequest().mutate().header("Authorization-Id", index).build();

            return  chain.filter(exchange);
        });
    }

    public static class Config{

    }
}
