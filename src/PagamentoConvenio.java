public class PagamentoConvenio extends Pagamento implements Exportavel {
    private Convenio convenio;

    //construtor que liga o convenio ao pagamento
    public PagamentoConvenio(int indiceConsulta, double valorBase, Convenio convenio) {
        super(indiceConsulta, valorBase, "Convênio");
        this.convenio = convenio;
    }

    @Override
    public double calcularValorFinal() {
        if (this.convenio != null && this.convenio.getNome() != null) {
            String nomePlano = this.convenio.getNome().trim().toLowerCase();
            double percentualCobertura = 0.0;

            //switch case para aplicar a cobertura
            switch (nomePlano) {
                case "saudeplus":
                case "saúdeplus":
                    percentualCobertura = 0.40; // 40%
                    break;
                case "vidamais":
                    percentualCobertura = 0.30; // 30%
                    break;
                case "bemestar":
                    percentualCobertura = 0.50; // 50%
                    break;
                default:
                    percentualCobertura = this.convenio.getPercentual();
                    break;
            }

            //calcula o valor final
            return this.getValorBase() * percentualCobertura;
        }
        return 0.0;
    }

    @Override
    public String exibirResumo() {
        String nome = (this.convenio != null) ? this.convenio.getNome() : "Não informado";
        return super.exibirResumo() + "\nConvênio: " + nome + " | Valor Final: R$ " + this.valorTotal;
    }

    @Override
    public String exportarDados() {
        String nome = (this.convenio != null) ? this.convenio.getNome() : "SEM_CONVENIO";
        return "CONVENIO;" + getIndiceConsulta() + ";" + getValorBase() + ";" + calcularValorFinal() + ";" + nome;
    }

    //getters e setters
    public Convenio getConvenio() { return this.convenio; }
    public void setConvenio(Convenio convenio) { this.convenio = convenio; }
}