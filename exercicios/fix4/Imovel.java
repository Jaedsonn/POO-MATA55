public abstract class Imovel{
    private int numInscricao;
    private String endereco;
    private double area;
    private double valor;
    private TipoLocalizacao tipoLocalizacao;
    private Beneficiario Beneficiario;
    private double TAXA_PADRAO;

    public Imovel(int numInscricao, String endereco, double area, double valor, TipoLocalizacao tipoLocalizacao, Beneficiario Beneficiario) {
        this.numInscricao = numInscricao;
        this.endereco = endereco;
        this.area = area;
        this.valor = valor;
        this.tipoLocalizacao = tipoLocalizacao;
        this.Beneficiario = Beneficiario;

        this.TAXA_PADRAO = calcularTaxaPadrao();
    }


    // Caso a taxa padrão venha a depender de outros atributos
    // mudamos apenas este método para recalcular a taxa padrão
    private double calcularTaxaPadrao(){
        if(this.tipoLocalizacao == TipoLocalizacao.A){
            return this.valor * 0.02;
        } else if (this.tipoLocalizacao == TipoLocalizacao.B){
            return this.valor * 0.15;
        } else if (this.tipoLocalizacao == TipoLocalizacao.C){
            return this.valor * 0.01;
        } else {
            return this.valor * 0.05;
        }
    }

    public double getTaxaPadrao(){
        return this.TAXA_PADRAO;
    }

    public double getValor(){
        return this.valor;
    }

    public int getNumInscricao(){
        return this.numInscricao;
    }

    public abstract double taxaColeta();
}