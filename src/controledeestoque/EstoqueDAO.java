package controledeestoque;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    private Connection conn;
    
    public EstoqueDAO() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/estoqueDB";
        String user = "root";
        String password = "";
        conn = DriverManager.getConnection(url,user,password);
    }
    
    public void adicionarProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO estoque (quantidade, preco) VALUES(?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql); 
        stmt.setInt(1,produto.getQuantidade());
        stmt.setDouble(2,produto.getPreco());
        stmt.executeUpdate();
    }
    
    public void atualizarProduto(Produto produto) throws SQLException {
    // Adiciona a cláusula WHERE para identificar o produto específico
    String sql = "UPDATE estoque SET quantidade = ?, preco = ? WHERE idProduto = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);

    stmt.setInt(1, produto.getQuantidade());  // Primeiro placeholder para quantidade
    stmt.setDouble(2, produto.getPreco());    // Segundo placeholder para preço
    stmt.setInt(3, produto.getIdProduto());   // Terceiro placeholder para idProduto

    stmt.executeUpdate();  // Executa a atualização
    }

    public void removerProduto(int idProduto) throws SQLException {
        String sql = "DELETE FROM estoque WHERE idProduto = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,idProduto);
        stmt.executeUpdate();
    }
    
    public List<Produto> listarProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM estoque";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next()){
            int idProduto = rs.getInt("idProduto");
            int quantidade = rs.getInt("quantidade");
            double preco = rs.getDouble("preco");
            produtos.add(new Produto(idProduto, quantidade,preco));
        }
        return produtos;
        
      }
    
    }

     

