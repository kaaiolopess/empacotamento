package org.l2code.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PedidosRequestDTO {
    @JsonProperty("pedidos")
    private List<PedidoDTO> pedidos = new ArrayList<>();
}