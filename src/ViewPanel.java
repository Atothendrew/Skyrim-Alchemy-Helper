import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;



public class ViewPanel extends JPanel {

	private static final long serialVersionUID = 9068632756744792250L;

	private String[] columnNames;
	private Canvas canvas;
	private Object[][] potionArray = null;
	public JTable table;
	private DefaultTableModel tableModel;
	private JComboBox comboBox;
	private JLabel sortLabel = new JLabel("Sort By:");

	public ViewPanel(ArrayList<Potion> potionList, JComboBox comboBox){

		this.comboBox = comboBox;

		JPanel panel = new JPanel(new GridLayout(3,0));
		panel.add(sortLabel);
		panel.add(comboBox);

		setTableData(potionList);

		columnNames = new String[]{
				"#",
				"Ingredient",
				"Ingredient",
				"Ingredient",
				"Effect (value)",
				"Effect (value)",
				"Effect (value)",
				"Effect (value)",
				"Effect (value)",
				"Value"
				};

		tableModel = new DefaultTableModel(potionArray, columnNames);

		table = new JTable(tableModel);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(40); // #
		table.getColumnModel().getColumn(1).setPreferredWidth(120); // Ingredient
		table.getColumnModel().getColumn(2).setPreferredWidth(120); // Ingredient
		table.getColumnModel().getColumn(3).setPreferredWidth(120); // Ingredient
		table.getColumnModel().getColumn(4).setPreferredWidth(130); // Effect
		table.getColumnModel().getColumn(5).setPreferredWidth(130); // Effect
		table.getColumnModel().getColumn(6).setPreferredWidth(130); // Effect
		table.getColumnModel().getColumn(7).setPreferredWidth(130); // Effect
		table.getColumnModel().getColumn(8).setPreferredWidth(130); // EFfect
		table.getColumnModel().getColumn(9).setPreferredWidth(50); // Value

		table.getModel().addTableModelListener(new TableModelListener() {

			public void tableChanged(TableModelEvent e) {
				System.out.println(e);
			}
		});		

		JScrollPane scrollPane = new JScrollPane(table);

		table.setFillsViewportHeight(true);	

		JPanel sp = new JPanel (new BorderLayout(0,2));
		sp.add(panel, BorderLayout.SOUTH);
		sp.add(scrollPane);

		setLayout(new BorderLayout(0,1)); // sets the layout
		add(sp); // adds the layout

	}

	public void setTableData(ArrayList<Potion> potionList){

		potionArray = new Object[potionList.size()][10];
		for (int i = 0; i < potionList.size(); i++) {
			Object[] tempArray = potionList.get(i).convertToArray(i);
			for (int j = 0; j < 10; j++){
				potionArray[i][j] = String.valueOf(tempArray[j]);
			}
		}
	}

	public void setTableDataAnotherClass(ArrayList<Potion> potionList){

		potionArray = new Object[potionList.size()][10];
		for (int i = 0; i < potionList.size(); i++) {
			Object[] tempArray = potionList.get(i).convertToArray(i);
			for (int j = 0; j < 10; j++){
				potionArray[i][j] = String.valueOf(tempArray[j]);
			}
		}

		createGUI();
	}

	public JTable getJTable(){
		return table;
	}

	private void createGUI(){

		tableModel = new DefaultTableModel(potionArray, columnNames);

		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(40); // #
		table.getColumnModel().getColumn(1).setPreferredWidth(120); // Ingredient
		table.getColumnModel().getColumn(2).setPreferredWidth(120); // Ingredient
		table.getColumnModel().getColumn(3).setPreferredWidth(120); // Ingredient
		table.getColumnModel().getColumn(4).setPreferredWidth(130); // Effect
		table.getColumnModel().getColumn(5).setPreferredWidth(130); // Effect
		table.getColumnModel().getColumn(6).setPreferredWidth(130); // Effect
		table.getColumnModel().getColumn(7).setPreferredWidth(130); // Effect
		table.getColumnModel().getColumn(8).setPreferredWidth(130); // EFfect
		table.getColumnModel().getColumn(9).setPreferredWidth(50); // Value
	}
}



