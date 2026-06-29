public class PagamentoCartao extends Pagamento {

    public PagamentoCartao(int indiceConsulta, double valorBase, int parcelas) {
        super(indiceConsulta, valorBase, parcelas);
    }

    public void calcularValorFinal() {

        double valor = this.valorBase;

        // regra do PDF:
        // acima de 3x, taxa de 2.5% por parcela extra
        if (parcelas > 3) {
            int extras = parcelas - 3;
            double taxa = valor * (0.025 * extras);
            valor += taxa;
        }

        this.valorFinal = valor;
    }
}