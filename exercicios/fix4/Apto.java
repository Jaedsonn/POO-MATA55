public class Apto extends Imovel {
    private double areaPrivativa;
    private PadraoContrutivo padraoContrutivo;

    public Apto(double areaPrivativa, PadraoContrutivo padraoContrutivo, int numInscricao, String endereco, double area, double valor, TipoLocalizacao tipoLocalizacao, Beneficiario Beneficiario) {
        super(numInscricao, endereco, area, valor, tipoLocalizacao, Beneficiario);
        this.areaPrivativa = areaPrivativa;
        this.padraoContrutivo = padraoContrutivo;
    }

    @Override
    public double taxaColeta(){
        double taxa = super.getTaxaPadrao() + (super.getTaxaPadrao() * 0.50);

        if(areaPrivativa > 300){
            taxa += super.getTaxaPadrao() * 0.02;
        }

        if(padraoContrutivo != PadraoContrutivo.D){
            taxa += super.getTaxaPadrao() * 0.01;
        }

        return taxa;
    }

}
