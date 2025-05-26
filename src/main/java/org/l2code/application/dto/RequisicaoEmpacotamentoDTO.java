package org.l2code.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RequisicaoEmpacotamentoDTO {
    @JsonProperty("pedidos")
    @NotEmpty(message = "A lista de pedidos não pode estar vazia.")
    @Valid
    private List<PedidoDTO> pedidos = new ArrayList<>();
}