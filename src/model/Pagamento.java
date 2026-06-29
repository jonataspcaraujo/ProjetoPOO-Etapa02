package model;

public abstract class Pagamento implements Exportavel {

    private int indiceConsulta;
    private double valorBase;

    public Pagamento(int indiceConsulta, double valorBase) {
        this.indiceConsulta = indiceConsulta;
        this.valorBase = valorBase;
    }

    public int getIndiceConsulta() { 
        return indiceConsulta; 
    }
    public double getValorBase() { 
        return valorBase; 
    }

    public void setIndiceConsulta(int indiceConsulta) { 
        this.indiceConsulta = indiceConsulta; 
    }
    public void setValorBase(double valorBase) { 
        this.valorBase = valorBase; 
    }

    public abstract double calcularValorFinal();

    public String getTipoPagamento() { 
        return "generico"; 
    }

    @Override
    public String exportarDados() {
        double vf = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return "[PAGAMENTO] Consulta #" + indiceConsulta
                + " | Tipo: " + getTipoPagamento()
                + " | Valor base: R$" + valorBase
                + " | Valor final: R$" + vf;
    }

    public String exibirResumo() {
        double vf = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return "Consulta #" + indiceConsulta
                + " | Tipo: " + getTipoPagamento()
                + " | Valor final: R$" + vf;
    }
}