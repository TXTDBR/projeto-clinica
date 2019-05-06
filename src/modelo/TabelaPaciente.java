
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaPaciente extends AbstractTableModel{
    private final List<Paciente> dados = new ArrayList<>();
    private final String[] colunas = {"ID", "Nome","Nascimento","RG","Telefone","CEP","Cidade","Bairro","Rua","N°","Complemento"};
    
    public TabelaPaciente(){
        
    }
    
    public void addRow(Paciente p){
        dados.add(p);
        this.fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int coluna){
        return colunas[coluna];
    }
    @Override
    public int getRowCount() {
        
         return dados.size();
    }
    public void removeRow(){
        this.dados.removeAll(dados);
        fireTableRowsDeleted(0, getRowCount());
    }
    @Override
    public int getColumnCount() {
         return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
         switch(coluna){
             case 0: return dados.get(linha).getId();
             case 1: return dados.get(linha).getNome();
             case 2: return dados.get(linha).getNascimento();
             case 3: return dados.get(linha).getRg();
             case 4: return dados.get(linha).getTelefone();
             case 5: return dados.get(linha).getCep();
             case 6: return dados.get(linha).getCidade();
             case 7: return dados.get(linha).getBairro();
             case 8: return dados.get(linha).getRua();
             case 9: return dados.get(linha).getNum();
             case 10: return dados.get(linha).getComplemento();
          
         }
        return null;
    }
    
}
