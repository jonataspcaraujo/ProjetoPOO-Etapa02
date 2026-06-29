package model;

import java.util.ArrayList;
import java.util.List;

public class Convenio {

    private String nome;
    private double percentualCobertura;

    private List<String> especialidadesCobertas;

    public Convenio(String nome, double percentualCobertura) {
        this.nome = nome;
        this.percentualCobertura = percentualCobertura;
        this.especialidadesCobertas = new ArrayList<>();
    }

    public Convenio(String nome, double percentualCobertura, List<String> especialidades) {
        this.nome = nome;
        this.percentualCobertura = percentualCobertura;
        this.especialidadesCobertas = new ArrayList<>(especialidades);
    }

    public String getNome() { 
        return nome; 
    }
    public double getPercentualCobertura() { 
        return percentualCobertura; 
    }
    public List<String> getEspecialidadesCobertas() { 
        return especialidadesCobertas; 
    }

    public void setNome(String nome) { 
        this.nome = nome; 
    }
    public void setPercentualCobertura(double percentualCobertura) { 
        this.percentualCobertura = percentualCobertura; 
    }

    public void adicionarEspecialidade(String especialidade) {
        especialidadesCobertas.add(especialidade);
    }

    public boolean cobre(String especialidade) {
        return especialidadesCobertas.contains(especialidade);
    }

    public String exibirResumo() {
        return "Convenio: " + nome + " | Cobertura: " + percentualCobertura + "% | Especialidades: " + especialidadesCobertas;
    }
}
