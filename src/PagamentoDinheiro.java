public class PagamentoDinheiro extends Pagamento {

    public PagamentoDinheiro(Consulta consulta, double valorBase) {
        super(consulta, valorBase, "Dinheiro");
        this.valorFinal = calcularValorFinal(); 
    }

    public static double calcularValor(double valorBase) {
        return valorBase;
    }

    // com desconto em percentual
    public static double calcularValor(double valorBase, double percentualDesconto) {
        double desconto = valorBase * percentualDesconto / 100;
        double valor = valorBase - desconto;
        if (valor < 0) {
            valor = 0;
        }
        return valor;
    }

    // com desconto e multa somada
    public static double calcularValor(double valorBase, double percentualDesconto, double multa) {
        double desconto = valorBase * percentualDesconto / 100;
        double valor = valorBase - desconto + multa;
        if (valor < 0) {
            valor = 0;
        }
        return valor;
    }

    // SOBRESCRITA
    @Override
    public double calcularValorFinal() {
        return this.valorFinal * 0.90; 
    }
}