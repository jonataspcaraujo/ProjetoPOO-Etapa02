public class PagamentoCartao extends Pagamento {

    public PagamentoCartao(Consulta consulta, double valorBase, int parcelas) {
        super(consulta, valorBase, "Cartao");
        if (parcelas < 1) this.parcelas = 1;
        else if (parcelas > 6) this.parcelas = 6;
        else this.parcelas = parcelas;
        
        this.valorFinal = calcularValorFinal();
    }

    public static double calcularValor(double valorBase) {
        return valorBase;
    }

    // com desconto em percentual
    public static double calcularValor(double valorBase, double percentualDesconto) {
        double desconto = valorBase * percentualDesconto / 100;
        double valor = valorBase - desconto;
        if (valor < 0) {
            valor = 0;
        }
        return valor;
    }

    // com desconto e multa somada
    public static double calcularValor(double valorBase, double percentualDesconto, double multa) {
        double desconto = valorBase * percentualDesconto / 100;
        double valor = valorBase - desconto + multa;
        if (valor < 0) {
            valor = 0;
        }
        return valor;
    }

    // SOBRESCRITA
    @Override
    public double calcularValorFinal() {
        if (this.parcelas >= 3) {
            return this.valorFinal * 1.10; // Adiciona 10% de juros
        }
        return this.valorFinal; // Mantém o valor original para 1x ou 2x
    }
}