import java.util.ArrayList;

public class Prefeitura {
  private ArrayList<Imovel> imoveis;
  private ArrayList<Beneficiario> beneficiarios;
  
    public Prefeitura() {
        this.imoveis = new ArrayList<>();
        this.beneficiarios = new ArrayList<>();
    }

    public void listarImoveis(){
        for(Imovel imovel : imoveis){
            System.out.println(imovel);
        }
    }

    public void listarBeneficiarios(){
        for(Beneficiario beneficiario : beneficiarios){
            System.out.println(beneficiario);
        }
    }

    public double consultarTaxa(int numInscricao){
        for(Imovel imovel: imoveis){
            if(imovel.getNumInscricao() == numInscricao){
                return imovel.taxaColeta();
            }
        }
        System.out.println("Imóvel não encontrado.");
        return 0;
    }
}
