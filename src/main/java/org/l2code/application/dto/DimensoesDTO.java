package org.l2code.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class DimensoesDTO {
    @JsonProperty("altura")
    private Integer altura;
    @JsonProperty("largura")
    private Integer largura;
    @JsonProperty("comprimento")
    private Integer comprimento;


    public int getLargura() { return largura; }
    public int getAltura() { return altura; }
    public int getComprimento() { return comprimento; }
}