package controledeestoque;
public class Produto {
    private int idProduto;
    private int quantidade;
    private double preco;
    
    //Construtores 
    public Produto(int quantidade, double preco) {
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    
    public Produto(int idProduto, int quantidade, double preco){
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    
    public int getIdProduto() { return idProduto; }
    public void setIdProduto(int idProduto){ this.idProduto = idProduto; }
    
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade){ this.quantidade = quantidade;}
    
    public double getPreco() { return preco; }
    public void setPreco(double preco) {this.preco = preco; }
}
