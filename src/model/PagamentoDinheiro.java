package model;

public class PagamentoDinheiro extends Pagamento {

    private static final double DESCONTO_DINHEIRO = 5.0;

    public PagamentoDinheiro(int indiceConsulta, double valorBase) {
        super(indiceConsulta, valorBase);
    }

    @Override
    public double calcularValorFinal() {
        double desconto = getValorBase() * DESCONTO_DINHEIRO / 100.0;
        return getValorBase() - desconto;
    }

    @Override
    public String getTipoPagamento() {
        return "dinheiro/pix";
    }

    @Override
    public String exibirResumo() {
        double vf = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return super.exibirResumo() + " (5% desconto aplicado)";
    }
}
