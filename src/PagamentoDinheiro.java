public class PagamentoDinheiro extends Pagamento {

    public PagamentoDinheiro(int indiceConsulta, double valorBase) {
        super(indiceConsulta, valorBase);
    }

    public void calcularValorFinal() {
        // 5% de desconto
        this.valorFinal = this.valorBase * 0.95;
    }
}