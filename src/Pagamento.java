public abstract class Pagamento {

    protected int indiceConsulta;
    protected double valorBase;
    protected double valorFinal;
    protected int parcelas;

    public Pagamento(int indiceConsulta, double valorBase) {
        this.indiceConsulta = indiceConsulta;
        this.valorBase = valorBase;
        this.parcelas = 1;
        this.valorFinal = valorBase;
    }

    public Pagamento(int indiceConsulta, double valorBase, int parcelas) {
        this.indiceConsulta = indiceConsulta;
        this.valorBase = valorBase;
        this.parcelas = parcelas;
        this.valorFinal = valorBase;
    }

    // obrigatorio no PDF (polimorfismo real)
    public abstract void calcularValorFinal();

    public String exibirResumo() {
        double valorArredondado = Math.round(valorFinal * 100.0) / 100.0;

        String resumo = "Consulta #" + indiceConsulta +
                " | Valor: R$" + valorArredondado +
                " | Parcelas: " + parcelas;

        if (parcelas > 1) {
            double valorParcela = Math.round((valorFinal / parcelas) * 100.0) / 100.0;
            resumo += " (R$" + valorParcela + " cada)";
        }

        return resumo;
    }
}