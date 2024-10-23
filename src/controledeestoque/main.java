package controledeestoque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {

    public static void main(String[] args) {
        // Dados de conexão com o banco
        String url = "jdbc:mysql://localhost:3306/estoqueDB"; // Nome do seu banco de dados
        String user = "root"; // Seu usuário do MySQL
        String password = ""; // Sua senha do MySQL

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexão com sucesso!");

            // Criação de uma tabela de exemplo (se não existir)
            String createTableSQL = "CREATE TABLE IF NOT EXISTS estoque ("
                    + "idProduto INT PRIMARY KEY AUTO_INCREMENT, "
                    + "quantidade INT NOT NULL, "
                    + "preco DOUBLE NOT NULL)";

            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);
            System.out.println("Tabela criada com sucesso!");

            // Inserir um produto no estoque
            String insertSQL = "INSERT INTO estoque (quantidade, preco) VALUES (10, 199.99)";
            statement.executeUpdate(insertSQL);
            System.out.println("Produto inserido com sucesso!");

            // Exibir os produtos no estoque
            String selectSQL = "SELECT * FROM estoque";
            ResultSet rs = statement.executeQuery(selectSQL);
            while (rs.next()) {
                System.out.println("ID Produto: " + rs.getInt("idProduto"));
                System.out.println("Quantidade: " + rs.getInt("quantidade"));
                System.out.println("Preço: " + rs.getDouble("preco"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
