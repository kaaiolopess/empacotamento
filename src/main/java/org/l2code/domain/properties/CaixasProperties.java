package org.l2code.domain.properties;

import lombok.Getter;
import lombok.Setter;
import org.l2code.domain.dto.Dimensao;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "empacotamento")
@Getter
@Setter
public class CaixasProperties {
    private List<CaixaConfig> caixas;

    @Getter
    @Setter
    public static class CaixaConfig {
        private String id;
        private Dimensao dimensao;
    }
}
