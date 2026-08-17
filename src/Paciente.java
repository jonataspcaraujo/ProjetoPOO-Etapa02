
public class Paciente extends Pessoa {

    private String telefone;
    private int idade;
    private boolean status;

    /*Associação entre a Classe "Convenio" e a Classe "Paciente"
    Ambos existem independente um do outro, porém existe a relação "tem um" entre ambos
     */
    private Convenio convenio;

    public Paciente(String nome, String cpf){
        super(nome, cpf);
        this.status = true;
    }

    public Paciente (String nome, String cpf, String telefone, int idade, Convenio convenio){
        super(nome, cpf);
        this.telefone = telefone;
        this.idade = idade;
        this.status = true;
        this.convenio = convenio;
    }

    public Paciente (String nome, String cpf, Convenio convenio, int idade){
        super(nome, cpf);
        this.idade = idade;
        this.convenio = convenio;
        this.status = true;
    }

    public Paciente (String nome, String cpf, int idade, String telefone){
        super(nome, cpf);
        this.idade = idade;
        this.telefone = telefone;
        this.status = true;
    }

    public Paciente (String nome, String cpf, Convenio convenio, String telefone, int idade){
        super(nome, cpf);
        this.telefone = telefone;
        this.idade = idade;
        this.convenio = convenio;
        this.status = true;

    }

    //Inicialização de Paciente
    public Paciente(){
    }
    //Getters para acesso aos dados
    public String getTelefone() { return this.telefone; }
    public int getIdade() { return this.idade; }
    public boolean isStatus() { return this.status; }
    public Convenio getConvenio(){return this.convenio;}

    @Override
    public void setNome(String nome) {
        // Validação segura contra NullPointer e espaços em branco
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do paciente não pode ser nulo ou vazio.");
        }
        super.setNome(nome);

    }

    //Setters para validação de atributos
    @Override
    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF inválido! CPF do cadastro é obrigatório");
        }
        super.setCpf(cpf);
    }

    public void setTelefone(String telefone){
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Número inválido! Telefone obrigatório para cadastro!");
        }
        this.telefone = telefone;
    }

    public void setIdade(int idade){
        if (idade < 0 || idade > 100) {
            throw new IllegalArgumentException("Idade inválida para cadastro.");
        }
        this.idade = idade;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }


    //Método para verificar se o paciente possui convénio ativo.
    public boolean temConvenio() {
        return this.convenio != null;
    }

    /*@Override
    public void MetodoAuxiliar(){
        System.out.println("Convenio: " + this.convenio.getNome());
    }*/

    //Método sobrescrito para exibir dados do Paciente
    @Override
    public void exibirDados(){
        System.out.println("======| Dados do Paciente |======");
        System.out.println("Nome do paciente: " + getNome());
        System.out.println("CPF do paciente: " + getCpf());
        System.out.println("Idade do paciente: " + this.idade);
        System.out.println("Telefone do paciente: " + this.telefone);
        System.out.println("Convenio: " + this.convenio.getNome());
        System.out.println("Status do paciente: " + this.status);
        System.out.println("=======================================");
    }






}