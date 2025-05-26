package org.l2code.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.l2code.application.dto.DimensoesDTO;

@Getter
@Setter
public class Dimensao {

    private final int largura;  // em cm
    private final int altura;
    private final int profundidade;

    public Dimensao(int largura, int altura, int profundidade) {
        this.largura = largura;
        this.altura = altura;
        this.profundidade = profundidade;
    }

    public int volume() {
        return largura * altura * profundidade;
    }

    /**
     * Verifica se essa dimensão comporta a dimensão do produto.
     * Aqui consideramos orientação fixa, ou seja,
     * o produto deve caber dentro da caixa nas mesmas orientações.
     * Se quiser considerar rotacionar o produto, o método ficaria mais complexo.
     */
    public boolean comporta(DimensoesDTO outra) {
        return this.largura >= outra.getLargura()
                && this.altura >= outra.getAltura()
                && this.profundidade >= outra.getComprimento();
    }
}
