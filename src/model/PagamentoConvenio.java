package model;

public class PagamentoConvenio extends Pagamento {

    private Convenio convenio;

    public PagamentoConvenio(int indiceConsulta, double valorBase, Convenio convenio) {
        super(indiceConsulta, valorBase);
        this.convenio = convenio;
    }

    public Convenio getConvenio() { 
        return convenio; 
    }
    public void setConvenio(Convenio convenio) { 
        this.convenio = convenio; 
    }


    @Override
    public double calcularValorFinal() {
        double cobertura = convenio.getPercentualCobertura();
        double desconto = getValorBase() * cobertura / 100.0;
        return getValorBase() - desconto;
    }

    @Override
    public String getTipoPagamento() { 
        return "convenio"; 
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo()
                + " (Convenio: " + convenio.getNome()
                + " | Cobertura: " + convenio.getPercentualCobertura() + "%)";
    }
}