package org.l2code.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l2code.application.dto.DimensoesDTO;
import org.l2code.application.dto.PedidoDTO;
import org.l2code.application.dto.ProdutoDTO;
import org.l2code.application.dto.RequisicaoEmpacotamentoDTO;
import org.l2code.domain.dto.Dimensao;
import org.l2code.domain.dto.RespostaEmpacotamento;
import org.l2code.domain.properties.CaixasProperties;
import org.l2code.domain.useCase.EmpacotamentoUseCase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmpacotamentoUseCaseTest {

    private EmpacotamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        CaixasProperties.CaixaConfig caixa1 = new CaixasProperties.CaixaConfig();
        caixa1.setId("caixa-1");
        caixa1.setDimensao(new Dimensao(10, 10, 10));

        CaixasProperties.CaixaConfig caixa2 = new CaixasProperties.CaixaConfig();
        caixa2.setId("caixa-2");
        caixa2.setDimensao(new Dimensao(50, 50, 50));

        CaixasProperties properties = new CaixasProperties();
        properties.setCaixas(List.of(caixa1, caixa2));

        useCase = new EmpacotamentoUseCase(properties);
    }

    @Test
    void deveEmpacotarProdutosCorretamente() {

        ProdutoDTO produto1 = new ProdutoDTO("produto-1", new DimensoesDTO(5, 5, 5));
        ProdutoDTO produto2 = new ProdutoDTO("produto-2", new DimensoesDTO(40, 40, 40));
        ProdutoDTO produto3 = new ProdutoDTO("produto-3", new DimensoesDTO(100, 100, 100));

        PedidoDTO pedido = new PedidoDTO(1, List.of(produto1, produto2, produto3));
        RequisicaoEmpacotamentoDTO request = new RequisicaoEmpacotamentoDTO(List.of(pedido));

        RespostaEmpacotamento resposta = useCase.executar(request);

        assertNotNull(resposta);
        assertEquals(1, resposta.pedidos().size());

        var pedidoEmpacotado = resposta.pedidos().get(0);
        assertEquals(1, pedidoEmpacotado.pedidoId());
        assertEquals(3, pedidoEmpacotado.caixas().size());

        var caixaComProduto1 = pedidoEmpacotado.caixas().stream()
                .filter(c -> c.produtos().contains("produto-1"))
                .findFirst().orElse(null);
        assertNotNull(caixaComProduto1);
        assertEquals("caixa-1", caixaComProduto1.caixaId());

        var caixaComProduto2 = pedidoEmpacotado.caixas().stream()
                .filter(c -> c.produtos().contains("produto-2"))
                .findFirst().orElse(null);
        assertNotNull(caixaComProduto2);
        assertEquals("caixa-2", caixaComProduto2.caixaId());

        var produtoNaoAlocado = pedidoEmpacotado.caixas().stream()
                .filter(c -> c.caixaId() == null && c.produtos().contains("produto-3"))
                .findFirst().orElse(null);
        assertNotNull(produtoNaoAlocado);
        assertEquals("Produto não cabe em nenhuma caixa disponível.", produtoNaoAlocado.observacao());
    }
}
