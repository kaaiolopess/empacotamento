package org.l2code.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProdutoDTO {
    @JsonProperty("produto_id")
    private String produtoId;
    @JsonProperty("dimensoes")
    private DimensoesDTO dimensoes;
}
