public class PagamentoConvenio extends Pagamento {
    private Convenio convenio;

    public PagamentoConvenio(Consulta consulta, double valorBase, Convenio convenio) {
        super(consulta, valorBase, "Convenio");
        this.convenio = convenio;
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
        if (this.convenio != null) {
            double cobertura = this.convenio.getPercentualCobertura();
            double descontoConvenio = this.valorFinal * (cobertura / 100.0);
            return this.valorFinal - descontoConvenio;
        }
        return this.valorFinal;
    }
}