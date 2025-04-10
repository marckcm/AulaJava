import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        atualizarTabela();
    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) tabelaProdutos.getModel();
        model.setRowCount(0);

        List<Produto> produtos = ProdutoDAO.listar();
        for (Produto p : produtos) {
            model.addRow(new Object[]{p.getId(), p.getNome(), p.getPreco()});
        }
    }
	
	
	    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String nome = txtNome.getText();
        double preco = Double.parseDouble(txtPreco.getText());
        ProdutoDAO.inserir(new Produto(0, nome, preco));
        atualizarTabela();        // TODO add your handling code here:
        limparCampos();
    }                                          

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        int linhaSelecionada = tabelaProdutos.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para atualizar.");
            return;
        }

        try {
            String idStr = tabelaProdutos.getValueAt(linhaSelecionada, 0).toString();
            int id = Integer.parseInt(idStr);

            String nome = txtNome.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome do produto não pode estar vazio.");
                return;
            }

            double preco = Double.parseDouble(txtPreco.getText().trim());

            Produto produto = new Produto(id, nome, preco);
            ProdutoDAO.atualizar(produto);
            atualizarTabela();
            limparCampos(); // Esse método não existe ainda, vou sugerir abaixo
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite valores válidos para ID e Preço.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar produto: " + ex.getMessage());
            ex.printStackTrace();
        }                                            
    }

    private void limparCampos() {
        txtNome.setText("");
        txtPreco.setText("");
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {                                           
        int linha = tabelaProdutos.getSelectedRow();
        if (linha >= 0) {
            int id = (int) tabelaProdutos.getValueAt(linha, 0);
            ProdutoDAO.excluir(id);
            atualizarTabela();
        }        // TODO add your handling code here:
    } 
	
	//ABAIXO APOIO PARA CONSTRUÇÃO DA TELA
	    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnInserir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaProdutos;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPreco;
    // End of variables declaration      