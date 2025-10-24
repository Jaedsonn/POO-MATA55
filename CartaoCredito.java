public class CartaoCredito{
    private String codigoSeguranca;
    private int mesValidade;
    private int anoValidade;
    private String numeroCartao;
    
    public CartaoCredito(String codigoSeguranca, int mesValidade, int anoValidade, String numeroCartao){
        this.codigoSeguranca = codigoSeguranca;
        this.mesValidade = mesValidade;
        this.anoValidade = anoValidade;
        this.numeroCartao = numeroCartao;
    }
    
    public String getCodigoSeguranca(){
        return this.codigoSeguranca;
    }
    
    public void setCodigoSeguranca(String codigoSeguranca){
        this.codigoSeguranca = codigoSeguranca;
    }
    
    public int getMesValidade(){
        return this.mesValidade;
    }
    
    public void setMesValidade(int mesValidade){
        this.mesValidade = mesValidade;
    }
    
    public int getAnoValidade(){
        return this.anoValidade;
    }
    
    public void setAnoValidade(int anoValidade){
        this.anoValidade = anoValidade;
    }

    public void setNumeroCartao(String numeroCartao){
        this.numeroCartao = numeroCartao;
    }

    public String getNumeroCartao(){
        return this.numeroCartao;
    }
}