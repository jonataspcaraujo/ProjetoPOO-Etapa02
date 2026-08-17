public abstract class Pagamento {
    private int indiceConsulta;
    private double valorBase;
    protected double valorTotal;
    private String tipoPagamento;
    private String status;

    public Pagamento(int indiceConsulta, double valorBase, String tipoPagamento) {
        this.indiceConsulta = indiceConsulta;
        this.valorBase = valorBase;
        this.tipoPagamento = tipoPagamento;
        this.status = "PAGO";
    }

    //getters e setters
    public int getIndiceConsulta() { return this.indiceConsulta; }
    public void setIndiceConsulta(int indiceConsulta) { this.indiceConsulta = indiceConsulta; }

    public double getValorBase() { return this.valorBase; }
    public void setValorBase(double valorBase) { this.valorBase = valorBase; }

    public double getValorTotal(){ return this.valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public String getTipoPagamento() { return this.tipoPagamento; }
    public void setTipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }

    //método abstrato para o valor final
    public abstract double calcularValorFinal();

    //método concreto para exibir resumo
    public String exibirResumo() {
        return "Consulta #" + this.indiceConsulta +
                " | Status: " + this.status;
    }

    public static double calcularValor(double valorBase) {
        return valorBase;
    }

    public static double calcularValor(double valorBase, double percentualDesconto) {
        double desconto = valorBase * percentualDesconto / 100;
        double valor = valorBase - desconto;
        return valor < 0 ? 0 : valor;
    }

    public static double calcularValor(double valorBase, double percentualDesconto, double multa) {
        double desconto = valorBase * percentualDesconto / 100;
        double valor = valorBase - desconto + multa;
        return valor < 0 ? 0 : valor;
    }
}