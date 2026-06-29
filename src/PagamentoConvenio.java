public class PagamentoConvenio extends Pagamento {

    private double percentualCobertura;

    public PagamentoConvenio(int indiceConsulta, double valorBase, double percentualCobertura) {
        super(indiceConsulta, valorBase);
        this.percentualCobertura = percentualCobertura;
    }
    
    public void calcularValorFinal() {
        // ex: 40% cobertura = paga 60%
        this.valorFinal = this.valorBase * (1 - percentualCobertura);
    }
}