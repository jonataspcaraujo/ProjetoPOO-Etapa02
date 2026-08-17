public class PagamentoCartao extends Pagamento implements Exportavel {
    private String bandeira;
    private int parcelas;

    public PagamentoCartao(int indiceConsulta, double valorBase, String bandeira, int parcelas) {
        super(indiceConsulta, valorBase, "Cartão");
        this.bandeira = bandeira;
        this.parcelas = parcelas;
    }

    @Override
    public double calcularValorFinal() {
        if (this.parcelas > 3) {
            int parcelasExtras = this.parcelas - 3;
            // já aplicando a taxa de 2,5% por parcela extra acima de 3x
            return this.getValorBase() * (1 + (parcelasExtras * 0.025));
        }
        return this.getValorBase(); // até 3x não tem juros
    }


    @Override
    public String exibirResumo() {
        double valorFinal = calcularValorFinal();
        double valorArredondado = Math.round(valorFinal * 100.0) / 100.0;
        String resumo = super.exibirResumo() + "\nBandeira: " + this.bandeira + " | Parcelas: " + this.parcelas + " | Valor Final: R$ " + this.valorTotal;

        if (this.parcelas > 1) {
            double valorParcela = Math.round((valorFinal / this.parcelas) * 100.0) / 100.0;
            resumo = resumo + " (R$" + valorParcela + " cada)";
        }
        return resumo;
    }

    @Override
    public String exportarDados() {
        return "CARTAO;" + getIndiceConsulta() + ";" + getValorBase() + ";" + calcularValorFinal() + ";x" + this.parcelas;
    }

    //getters, setters
    public String getBandeira() { return this.bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }
    public int getParcelas() { return this.parcelas; }
    public void setParcelas(int parcelas) { this.parcelas = parcelas; }
}