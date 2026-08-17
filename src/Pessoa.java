public abstract class Pessoa {

    private String nome, cpf;

    protected Pessoa(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    //Inicialização de Pessoa
    public Pessoa(){
    }

    //Getters para acesso aos dados
    public String getCpf(){
        return cpf;
    }
    public String getNome(){
        return nome;
    }



    //public abstract void MetodoAuxiliar();

    //Método para mostrar dados do objeto
    protected void exibirDados(){
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);

    }

    //Setters para validação de dados
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido! O nome do cadastro é obrigatório.");
        }
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF inválido! CPF do cadastro é obrigatório");
        }
        this.cpf = cpf;
    }



}
