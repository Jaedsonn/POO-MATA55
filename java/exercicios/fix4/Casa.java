public class Casa extends Imovel {
    private double areaConstruida;
    private PadraoContrutivo padraoConstrutivo;

    public Casa(double areaConstruida, PadraoContrutivo padraoConstrutivo, int numInscricao, String endereco, double area, double valor, TipoLocalizacao tipoLocalizacao, Beneficiario Beneficiario) {
        super(numInscricao, endereco, area, valor, tipoLocalizacao, Beneficiario);
        this.areaConstruida = areaConstruida;
        this.padraoConstrutivo = padraoConstrutivo;
    }

 @Override
 public double taxaColeta(){
        double taxa = super.getTaxaPadrao() ;

        if(areaConstruida > 400){
            taxa += super.getTaxaPadrao() * 0.05;
        }

        if(padraoConstrutivo == PadraoContrutivo.A){
            taxa += super.getTaxaPadrao() * 0.15;
        }

        return taxa;
    }
}