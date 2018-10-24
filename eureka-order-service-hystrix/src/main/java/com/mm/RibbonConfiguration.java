package com.mm;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "custom",configuration = RibbonConfiguration.class)
public class RibbonConfiguration {
    @Configuration
    protected static class FooConfiguration {
        @Bean
        public ZonePreferenceServerListFilter serverListFilter() {
            ZonePreferenceServerListFilter filter = new ZonePreferenceServerListFilter();
            filter.setZone("myTestZone");
            return filter;
        }

        @Bean
        public IPing ribbonPing() {
            return new PingUrl();
        }
    }
}
