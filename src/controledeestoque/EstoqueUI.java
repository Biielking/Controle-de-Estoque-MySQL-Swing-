package controledeestoque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EstoqueUI extends JFrame {
    private JTextField txtQuantidade, txtPreco;
    private JTable tabelaEstoque;
    private DefaultTableModel model;
    private EstoqueDAO estoqueDAO;

    public EstoqueUI() {
        setTitle("Controle de Estoque");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            estoqueDAO = new EstoqueDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Layout da interface
        JPanel panel = new JPanel(new GridLayout(4, 2));

        // Campos de texto
        panel.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        panel.add(txtQuantidade);

        panel.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        panel.add(txtPreco);

        // Botões de ação
        JButton btnAdicionar = new JButton("Adicionar Produto");
        panel.add(btnAdicionar);
        JButton btnAtualizar = new JButton("Atualizar Produto");
        panel.add(btnAtualizar);
        JButton btnRemover = new JButton("Remover Produto");
        panel.add(btnRemover);

        add(panel, BorderLayout.NORTH);

        // Tabela para exibir o estoque
        model = new DefaultTableModel(new String[]{"ID", "Quantidade", "Preço"}, 0);
        tabelaEstoque = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabelaEstoque);
        add(scrollPane, BorderLayout.CENTER);

        // Listeners dos botões
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarProduto();
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerProduto();
            }
        });

        listarProdutos();
    }

    private void adicionarProduto() {
        int quantidade = Integer.parseInt(txtQuantidade.getText());
        double preco = Double.parseDouble(txtPreco.getText());
        Produto produto = new Produto(quantidade, preco);
        try {
            estoqueDAO.adicionarProduto(produto);
            listarProdutos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

private void atualizarProduto() {
    int selectedRow = tabelaEstoque.getSelectedRow();
    if (selectedRow >= 0) {
        int idProduto = (int) model.getValueAt(selectedRow, 0);

        // Verifica se os campos estão vazios
        if (txtQuantidade.getText().isEmpty() || txtPreco.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            return; // Sai do método se os campos estiverem vazios
        }

        try {
            int quantidade = Integer.parseInt(txtQuantidade.getText());
            double preco = Double.parseDouble(txtPreco.getText());
            Produto produto = new Produto(idProduto, quantidade, preco);
            estoqueDAO.atualizarProduto(produto);
            listarProdutos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para quantidade e preço.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um produto para atualizar.");
    }
}


    private void removerProduto() {
        int selectedRow = tabelaEstoque.getSelectedRow();
        if (selectedRow >= 0) {
            int idProduto = (int) model.getValueAt(selectedRow, 0);
            try {
                estoqueDAO.removerProduto(idProduto);
                listarProdutos();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void listarProdutos() {
        try {
            model.setRowCount(0);
            for (Produto p : estoqueDAO.listarProdutos()) {
                model.addRow(new Object[]{p.getIdProduto(), p.getQuantidade(), p.getPreco()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EstoqueUI().setVisible(true));
    }
}
