import java.util.HashSet;
import java.util.Set;

public class Convenio {
    private String nome;
    private double percentualCobertura;
    private Set<String> especialidadesCobertas;

    public Convenio(String nome, double percentualCobertura, Set<String> especialidades) {
        this.nome = nome;
        this.percentualCobertura = percentualCobertura;

        // HashSet<String>: não permite duplicatas e não precisa de ordem.
        this.especialidadesCobertas = new HashSet<>();
        this.especialidadesCobertas.addAll(especialidades);
    }

    public void cadastrarEspecialidade(String especialidade) {
        if (especialidade != null) {
            this.especialidadesCobertas.add(especialidade);
        }
    }

    public boolean cobreEspecialidade(String especialidade) {
        if (especialidade == null) return false;
        return this.especialidadesCobertas.contains(especialidade);
    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPercentualCobertura() {
        return percentualCobertura;
    }

    public void setPercentualCobertura(double percentualCobertura) {
        if (percentualCobertura >= 0 && percentualCobertura <= 100) {
            this.percentualCobertura = percentualCobertura;
        } else {
            System.out.println("Percentual de cobertura deve ser entre 0 e 100.");
        }
    }

    public Set<String> getEspecialidadesCobertas() {
        return especialidadesCobertas;
    }
}