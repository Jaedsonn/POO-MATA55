public class Carro{
    private String modelo;
    private String placa;
    private CategoriaVeiculo categoria;
    private StatusCarro status;
    private String cor;
    
    public Carro(String modelo, String placa, CategoriaVeiculo categoria, String cor){
        this.modelo = modelo;
        this.placa = placa;
        this.categoria = categoria;
        this.cor = cor;
        this.status = StatusCarro.DISPONIVEL;
    }
    
    
    public CategoriaVeiculo getCategoria(){
        return this.categoria;
    }
    
    public StatusCarro getStatusCarro(){
        return this.status;
    }
    
    public void setStatusCarro(StatusCarro status){
        this.status = status;
    }
    
    public String getModelo(){
        return this.modelo;
    }

    public String getCor(){
        return this.cor;
    }

    public String getPlaca(){
        return this.placa;
    }


}