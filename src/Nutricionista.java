//Classe filha de Profissional, especialização de nutricionista

public class Nutricionista extends Profissional {

    private String planoAlimentar;

    // Inicialização de Nutricionista apenas com dados base
    public Nutricionista(String nome, String cpf) {
        super(nome, cpf);
        this.planoAlimentar = "Não definido";
    }

    // Inicialização de Nutricionista com dados completos
    public Nutricionista(String nome, String cpf, String registro, double valorConsulta, String planoAlimentar) {
        super(nome, cpf, registro, valorConsulta);
        this.planoAlimentar = planoAlimentar;
    }

    // Inicialização de Profissional com dados completos
    public Nutricionista(String nome, String cpf, String registro, double valorConsulta) {
        super(nome, cpf);
        this.registro = registro;
        this.valorConsulta = valorConsulta;
    }

    // Acesso dos dados para outra classe
    public String getPlanoAlimentar() { return planoAlimentar; }
    public void setPlanoAlimentar(String planoAlimentar) { this.planoAlimentar = planoAlimentar; }

    // Gerenciamento de planos do profissional
    public void registrarPlanoAlimentar(String paciente, String orientacoes) {
        System.out.println("--- REGISTRO NUTRIÇÃO ---");
        System.out.println("Paciente: " + paciente);
        System.out.println("Plano alimentar: " + planoAlimentar);
        System.out.println("Orientações: " + orientacoes);
    }

    // Método para exibir o resumo específico do profissional
    @Override
    public void exibirResumo() {
        System.out.println("====| Nutricionista |====");
        exibirDados();
        System.out.println("Especialidade: Nutrição");
        System.out.println("Plano alimentar: " + planoAlimentar);
        System.out.println("========================");
    }
}