package org.l2code.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PedidoDTO {
    @JsonProperty("pedido_id")
    private Integer pedidoId;
    @JsonProperty("produtos")
    private List<ProdutoDTO> produtos;
}
