public class PagamentoPix extends Pagamento implements Exportavel {
    private String chavePix;

    public PagamentoPix(int indiceConsulta, double valorBase, String chavePix) {
        super(indiceConsulta, valorBase, "Pix");
        this.chavePix = chavePix;
    }


    @Override
    public double calcularValorFinal() {
        Double valorTotal = this.getValorBase() * 0.95;
        this.valorTotal = valorTotal;
        return valorTotal;
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + "\nChave Pix: " + this.chavePix + 
                                        " | Valor final: R$ " + this.valorTotal;
    }

    @Override
    public String exportarDados() {
        return "PIX;" + getIndiceConsulta() + ";" + getValorBase() + ";" + calcularValorFinal();
    }

    //getters e settsers
    public String getChavePix() { return this.chavePix; }
    public void setChavePix(String chavePix) { this.chavePix = chavePix; }
}