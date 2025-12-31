public class Terreno extends Imovel {
    private boolean areaPreservacao;
    private AreaTipo areaTipo;

    public Terreno(boolean areaPreservacao, AreaTipo areaTipo, int numInscricao, String endereco, double area, double valor, TipoLocalizacao tipoLocalizacao, Beneficiario Beneficiario) {
        super(numInscricao, endereco, area, valor, tipoLocalizacao, Beneficiario);
        this.areaPreservacao = areaPreservacao;
        this.areaTipo = areaTipo;
    }

    @Override
    public double taxaColeta(){
        double taxa = super.getTaxaPadrao();

        if(areaTipo == AreaTipo.RURAL){
            taxa -= super.getTaxaPadrao() * 0.10;
        }

        if(areaPreservacao){
            taxa += super.getTaxaPadrao() * 0.20;
        }

        return taxa;
    }
}
