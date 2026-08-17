//Classe filha de Profissional, especialização de fisioterapeuta

public class Fisioterapeuta extends Profissional {

    private int totalSessoesPrevistas;

    // Inicialização de Fisioterapeuta apenas com dados base
    public Fisioterapeuta(String nome, String cpf) {
        super(nome, cpf);
        this.totalSessoesPrevistas = 10;
    }

    // Inicialização de Fisioterapeuta com dados completos
    public Fisioterapeuta(String nome, String cpf, String registro, double valorConsulta, int totalSessoesPrevistas) {
        super(nome, cpf, registro, valorConsulta);
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }

    // Inicialização de Profissional com dados completos
    public Fisioterapeuta(String nome, String cpf, String registro, double valorConsulta) {
        super(nome, cpf);
        this.registro = registro;
        this.valorConsulta = valorConsulta;
    }

    // Acesso dos dados para outra classe
    public int getTotalSessoesPrevistas() { return totalSessoesPrevistas; }
    public void setTotalSessoesPrevistas(int totalSessoesPrevistas) { this.totalSessoesPrevistas = totalSessoesPrevistas; }

    // Gerenciamento de sessões do profissional
    public void registrarSessao(String paciente, String evolucao) {
        System.out.println("--- REGISTRO FISIOTERAPIA ---");
        System.out.println("Paciente: " + paciente);
        System.out.println("Evolução: " + evolucao);
        System.out.println("Sessões previstas no plano: " + totalSessoesPrevistas);
    }

    // Método para exibir o resumo específico do profissional
    @Override
    public void exibirResumo() {
        System.out.println("====| Fisioterapeuta |====");
        exibirDados();
        System.out.println("Especialidade: Fisioterapia");
        System.out.println("Sessões previstas: " + totalSessoesPrevistas);
        System.out.println("=========================");
    }
}