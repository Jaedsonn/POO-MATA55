class Beneficiario{
    private String nome;
    private String cpf;
    private String telefone;
    
    public Beneficiario(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Beneficiario(String nome, String cpf){
        this(nome, cpf, "N/A");
    }
}