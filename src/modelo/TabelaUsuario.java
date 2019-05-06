
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class TabelaUsuario extends AbstractTableModel{
    private List<Usuario> dados;
    private String[] colunas = {"ID","Usuário","Login","senha","Tipo"};
    
    public TabelaUsuario(ArrayList<Usuario> lista){
        dados = lista;
        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
               int linha = tme.getFirstRow();
               Usuario p = dados.get(linha); 
            }
        });
    }
    
    @Override
    public String getColumnName(int coluna){
        return colunas[coluna];
    }
    
    public void addRow(Usuario u){
        dados.add(u);
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
     return   colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
       switch(coluna){
           case 0:
               return dados.get(linha).getId();
           case 1:
               return dados.get(linha).getUsuario();
           case 2:
               return dados.get(linha).getLogin();
           case 3:
               return dados.get(linha).getSenha();
           case 4:
               return dados.get(linha).getTipo();
       }
       return null;
    }
    
    @Override
    public void setValueAt(Object valor, int linha, int coluna){
        switch(coluna){
            case 0:
                dados.get(linha).setId(Integer.parseInt((String) valor));
                break;
            case 1: 
                dados.get(linha).setUsuario((String) valor);
                
                break;
            case 2:
                dados.get(linha).setLogin((String) valor);
            break;
            case 4:
                dados.get(linha).setSenha((String) valor);
            break;
            case 5:
                dados.get(linha).setTipo((String) valor);
            break;
        }
    }
    
}
