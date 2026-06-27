public abstract class Pagamento implements Exportavel {
    protected Consulta consulta;
    protected double valorFinal;
    protected String tipoPagamento;
    protected int parcelas;

    public Pagamento(Consulta consulta, double valorFinal, String tipoPagamento) {
        this.consulta = consulta;
        this.valorFinal = valorFinal;
        this.tipoPagamento = tipoPagamento;
        this.parcelas = 1;
    }

    // com parcelas (so pra cartao)
    public Pagamento(Consulta consulta, double valorFinal, String tipoPagamento, int parcelas) {
        this.consulta = consulta;
        this.valorFinal = valorFinal;
        this.tipoPagamento = tipoPagamento;
        this.parcelas = parcelas;
    }

    public Consulta getConsulta() {
        return this.consulta;
    }
    public double getValorFinal() {
        return this.valorFinal;
    }
    public String getTipoPagamento() {
        return this.tipoPagamento;
    }
    public int getParcelas() {
        return this.parcelas;
    }

    public abstract double calcularValorFinal();

    public String exibirResumo() {
        // arredonda pra 2 casas
        double valorArredondado = Math.round(valorFinal * 100.0) / 100.0;
        String resumo = "Consulta (CPF do paciente): " + consulta.getCpfPaciente() + " | Valor: R$" + valorArredondado
                + " | Tipo: " + tipoPagamento + " | Parcelas: " + parcelas;
        if (parcelas > 1) {
            double valorParcela = Math.round((valorFinal / parcelas) * 100.0) / 100.0;
            resumo = resumo + " (R$" + valorParcela + " cada)";
        }
        return resumo;
    }
    // SOBRESCRITA
    @Override
    public String exportarDados() {
        return "TIPO:Pagamento | CONSULTA DO PACIENTE (CPF):" + consulta.getCpfPaciente() +
               " | VALOR_FINAL:" + valorFinal +
               " | MODALIDADE:" + tipoPagamento +
               " | PARCELAS:" + parcelas;
    }
}
