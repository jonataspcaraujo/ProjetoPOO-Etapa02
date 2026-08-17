//Classe filha de Profissional, especialização de psicologo

public class Psicologo extends Profissional {

    private String abordagem;

    // Inicialização de Psicologo apenas com dados base
    public Psicologo(String nome, String cpf) {
        super(nome, cpf);
        this.abordagem = "Não informada";
    }

    // Inicialização de Psicologo com dados completos
    public Psicologo(String nome, String cpf, String registro, double valorConsulta, String abordagem) {
        super(nome, cpf, registro, valorConsulta);
        this.abordagem = abordagem;
    }

    // Inicialização de Profissional com dados completos
    public Psicologo(String nome, String cpf, String registro, double valorConsulta) {
        super(nome, cpf);
        this.registro = registro;
        this.valorConsulta = valorConsulta;
    }

    // Acesso dos dados para outra classe
    public String getAbordagem() { return abordagem; }
    public void setAbordagem(String abordagem) { this.abordagem = abordagem; }

    // Gerenciamento de sessões do profissional
    public void registrarEvolucaoSessao(String paciente, String estadoEmocional, String analise) {
        System.out.println("--- REGISTRO PSICOLOGIA (SIGILOSO) ---");
        System.out.println("Paciente: " + paciente);
        System.out.println("Estado do paciente: " + estadoEmocional);
        System.out.println("Análise clínica: " + analise);
    }

    // Método para exibir o resumo específico do profissional
    @Override
    public void exibirResumo() {
        System.out.println("====| Psicólogo |====");
        exibirDados();
        System.out.println("Especialidade: Psicologia");
        System.out.println("Abordagem: " + abordagem);
        System.out.println("====================");
    }
}