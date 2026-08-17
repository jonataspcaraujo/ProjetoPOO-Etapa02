import java.util.ArrayList;
import java.util.List;

public class Convenio {


    private String nomeConvenio;
    private double percentual;
    private List<String> especialidades;

    public Convenio (String nomeConvenio, double percentual, List<String> especialidades) {
        this.nomeConvenio = nomeConvenio;

        // Chamando o setter para garantir a validação de limite
        setpercentualConvenio(percentual);

        // Inicializando a lista copiando os dados recebidos
        if (especialidades != null) {
            this.especialidades = new ArrayList<>(especialidades);
        } else {
            this.especialidades = new ArrayList<>();
        }
    }

    //Construtor simples
    public Convenio (String nomeConvenio){

        this.nomeConvenio = nomeConvenio;
    }

    //Getters para acesso aos dados
    public String getNome(){
        return nomeConvenio;
    }
    public double getPercentual(){
        return percentual;
    }
    public List<String> getEspecialidades(){
        return new ArrayList<>(especialidades);
    }

    //Setters para validação e adicionar especialidade
    public void setEspecialidades(String especialidade){
        if (especialidade != null && !especialidade.trim().isEmpty()){
            this.especialidades.add(especialidade.toLowerCase());
        }
    }

    public boolean cobreEspecialidade(String especialidade){
        if (especialidade == null || especialidade.trim().isEmpty()){
            return false;

        }
        return this.especialidades.contains(especialidade.toLowerCase());
    }

    public void setNomeConvenio(String nome){
        this.nomeConvenio = nome;
    }

    public void setpercentualConvenio(double percentual){
        if (percentual < 0.0 || percentual > 1.0){
            throw new IllegalArgumentException("Percentual invalido! Deve estar entre 0 e 1 (0% a 100%).");
        }
        this.percentual = percentual;
    }

}
