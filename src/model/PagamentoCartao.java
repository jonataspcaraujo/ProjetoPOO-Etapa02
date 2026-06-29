package model;

public class PagamentoCartao extends Pagamento {

    private int parcelas;
    private static final double TAXA_PARCELA_EXTRA = 2.5;
    private static final int PARCELAS_SEM_TAXA = 3;
    private static final int PARCELAS_MAX = 6;

    public PagamentoCartao(int indiceConsulta, double valorBase) {
        super(indiceConsulta, valorBase);
        this.parcelas = 1;
    }

    public PagamentoCartao(int indiceConsulta, double valorBase, int parcelas) {
        super(indiceConsulta, valorBase);
        this.parcelas = parcelas;
    }

    public int getParcelas() { return parcelas; }
    public void setParcelas(int parcelas) { this.parcelas = parcelas; }


    @Override
    public double calcularValorFinal() {
        if (parcelas > PARCELAS_SEM_TAXA) {
            int parcelasExtras = parcelas - PARCELAS_SEM_TAXA;
            double taxa = TAXA_PARCELA_EXTRA * parcelasExtras;
            return getValorBase() * (1 + taxa / 100.0);
        }
        return getValorBase();
    }

    @Override
    public String getTipoPagamento() { return "cartao"; }

    @Override
    public String exibirResumo() {
        double vf = Math.round(calcularValorFinal() * 100.0) / 100.0;
        double vlrParcela = Math.round((vf / parcelas) * 100.0) / 100.0;
        String det = parcelas > 1 ? " | " + parcelas + "x de R$" + vlrParcela : " | a vista";

        if (parcelas > PARCELAS_SEM_TAXA) {
            det += " (taxa de " + (TAXA_PARCELA_EXTRA * (parcelas - PARCELAS_SEM_TAXA)) + "% aplicada)";
        }
        return super.exibirResumo() + det;
    }
}