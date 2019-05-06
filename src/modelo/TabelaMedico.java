
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Medico;

public class TabelaMedico extends AbstractTableModel{
    
    private final List<Medico> dados = new ArrayList<>();
    private String[] colunas = {"ID","Nome","CRM","Especialidade"};
    
        
    @Override
    public String getColumnName(int coluna){
      return colunas[coluna];  
    }
        
    public void addRow(Medico p){
         dados.add(p);
        fireTableDataChanged();
    }
    
    public void removerRow(){
        this.dados.removeAll(dados);
        fireTableRowsDeleted(0, getRowCount());
    }

    @Override
    public int getRowCount() {
        
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return dados.get(linha).getId();
            case 1:
            return dados.get(linha).getNome();
            case 2:
                return dados.get(linha).getCrm();
            case 3:
                return dados.get(linha).getEspecialidade_id();
        }
        return null;
    }
    
    @Override
     public void setValueAt(Object valor, int linha, int coluna){
        switch(coluna){
            case 0:
                dados.get(linha).setId(Integer.parseInt((String)valor));
                break;
            case 1:
                dados.get(linha).setNome((String)valor);
                break;
            case 2:
                dados.get(linha).setCrm(Integer.parseInt((String) valor));
                break;
            case 3:
                dados.get(linha).setEspecialidade_id((int) valor);
                break;
        }
        fireTableRowsUpdated(linha, linha);
    }
}
