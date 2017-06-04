package projekt;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.table.TableColumn;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.SwingConstants;

public class View extends JFrame{
	

	public JFrame frmCrud;
	
	private JTable DostawcaTab;
	private JTable AdresTab;
	private JTable MagazynTab;
	private JTable DostawaTab;
	
	private JTextField adres_ulica;
	private JTextField adres_numer;
	private JTextField adres_miasto;
	private JTextField adres_kod;
	
	private JTextField dostawca_imie;
	private JTextField dostawca_nazwisko;
	private JComboBox dostawca_adres;
	
	private JTextField magazyn_miejscowosc;
	private JTextField magazyn_numer;
	
	private JTextField dostawa_data;
	private JTextField dostawa_kwota;
	
	private JButton btnAdd_dostawca;
	private JButton btnDelete_dostawca;
	private JButton btnUpdate_dostawca;
	
	private JButton btnAdd_adres;
	private JButton btnDelete_adres;
	private JButton btnUpdate_adres;
	
	private JButton btnAdd_magazyn;
	private JButton btnDelete_magazyn;
	private JButton btnUpdate_magazyn;
	
	private JButton btnAdd_dostawa;
	private JButton btnDelete_dostawa;
	private JButton btnUpdate_dostawa;
	
	private JComboBox dostawa_numermag;
	private JComboBox dostawa_numerdost;
	
	private JTextField magazyn_polozenie;
	private JTextField magazyn_opis;
	private JTextField dostawca_firma;


//	public View() {
//		initialize();
//	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public View() {
		
		frmCrud = new JFrame();
		frmCrud.setTitle("CRUD");
		frmCrud.setBounds(100, 100, 814, 391);
		frmCrud.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrud.getContentPane().setLayout(new BoxLayout(frmCrud.getContentPane(), BoxLayout.X_AXIS));
		frmCrud.setResizable(false);
		frmCrud.setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmCrud.getContentPane().add(tabbedPane);
		
		//Dostawca
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Dostawca", null, layeredPane, null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 793, 324);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		btnAdd_dostawca = new JButton("Add");
		btnAdd_dostawca.setBounds(10, 224, 89, 23);
		panel.add(btnAdd_dostawca);
				
		btnDelete_dostawca = new JButton("Delete");
		btnDelete_dostawca.setBounds(10, 258, 89, 23);
		panel.add(btnDelete_dostawca);
		
		btnUpdate_dostawca = new JButton("Update");
		btnUpdate_dostawca.setBounds(10, 292, 89, 23);
		panel.add(btnUpdate_dostawca);
		

		dostawca_adres = new JComboBox();
		dostawca_adres.setBounds(225, 293, 319, 20);
		panel.add(dostawca_adres);
		
		JLabel lblImie = new JLabel("Imie");
		lblImie.setBounds(109, 228, 46, 14);
		panel.add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(109, 262, 66, 14);
		panel.add(lblNazwisko);
		
		JLabel lblAdres = new JLabel("Adres");
		lblAdres.setBounds(109, 296, 46, 14);
		panel.add(lblAdres);
		

		
		DostawcaTab = new JTable();
		DostawcaTab.setBounds(10, 47, 773, 169);
		panel.add(DostawcaTab);
		
		JScrollPane scrollPane = new JScrollPane(DostawcaTab);
		scrollPane.setBounds(10, 11, 773, 202);
		panel.add(scrollPane);
		
		//Adres
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Adres", null, layeredPane_1, null);
		layeredPane_1.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 793, 324);
		layeredPane_1.add(panel_1);
		panel_1.setLayout(null);
		
		btnAdd_adres = new JButton("Add");
		btnAdd_adres.setBounds(10, 224, 89, 23);
		panel_1.add(btnAdd_adres);
		
		btnUpdate_adres = new JButton("Update");
		btnUpdate_adres.setBounds(10, 292, 89, 23);
		panel_1.add(btnUpdate_adres);
		
		btnDelete_adres = new JButton("Delete");
		btnDelete_adres.setBounds(10, 258, 89, 23);
		panel_1.add(btnDelete_adres);
		

		
		AdresTab = new JTable();
		AdresTab.setBounds(10, 47, 773, 169);
		panel.add(AdresTab);

		
		JScrollPane scrollPane_1 = new JScrollPane(AdresTab);
		scrollPane_1.setBounds(10, 11, 773, 202);
		panel_1.add(scrollPane_1);
		
		JLabel lblUlica = new JLabel("Ulica");
		lblUlica.setBounds(109, 228, 46, 14);
		panel_1.add(lblUlica);
		
		JLabel lblNumer = new JLabel("Numer");
		lblNumer.setBounds(109, 262, 46, 14);
		panel_1.add(lblNumer);
		
		JLabel lblMiasto = new JLabel("Miasto");
		lblMiasto.setBounds(109, 296, 46, 14);
		panel_1.add(lblMiasto);
		
		adres_ulica = new JTextField();
		adres_ulica.setBounds(225, 225, 86, 20);
		panel_1.add(adres_ulica);
		adres_ulica.setColumns(10);
		
		adres_numer = new JTextField();
		adres_numer.setBounds(225, 258, 86, 22);
		panel_1.add(adres_numer);
		adres_numer.setColumns(10);
		
		adres_miasto = new JTextField();
		adres_miasto.setBounds(225, 293, 86, 20);
		panel_1.add(adres_miasto);
		adres_miasto.setColumns(10);
		
		JLabel lblKod = new JLabel("Kod");
		lblKod.setBounds(321, 228, 46, 14);
		panel_1.add(lblKod);
		
		adres_kod = new JTextField();
		adres_kod.setBounds(386, 225, 86, 20);
		panel_1.add(adres_kod);
		adres_kod.setColumns(10);
		

		
		//Magazyn
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Magazyn", null, layeredPane_2, null);
		layeredPane_2.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 793, 324);
		layeredPane_2.add(panel_2);
		panel_2.setLayout(null);
		
		btnAdd_magazyn = new JButton("Add");
		btnAdd_magazyn.setBounds(10, 224, 89, 23);
		panel_2.add(btnAdd_magazyn);
		
		btnUpdate_magazyn = new JButton("Update");
		btnUpdate_magazyn.setBounds(10, 292, 89, 23);
		panel_2.add(btnUpdate_magazyn);
		
		btnDelete_magazyn = new JButton("Delete");
		btnDelete_magazyn.setBounds(10, 258, 89, 23);
		panel_2.add(btnDelete_magazyn);
		
		MagazynTab = new JTable();
		MagazynTab.setBounds(10, 47, 773, 169);
		panel.add(MagazynTab);
		
		JScrollPane scrollPane_2 = new JScrollPane(MagazynTab);
		scrollPane_2.setBounds(10, 11, 773, 202);
		panel_2.add(scrollPane_2);
		
		JLabel lblNumer_1 = new JLabel("Oznaczenie");
		lblNumer_1.setBounds(109, 228, 89, 14);
		panel_2.add(lblNumer_1);
		
		JLabel lblMiasto_1 = new JLabel("Miejscowosc");
		lblMiasto_1.setBounds(109, 262, 89, 14);
		panel_2.add(lblMiasto_1);
		
		magazyn_miejscowosc = new JTextField();
		magazyn_miejscowosc.setBounds(225, 259, 86, 20);
		panel_2.add(magazyn_miejscowosc);
		magazyn_miejscowosc.setColumns(10);
		
		magazyn_numer = new JTextField();
		magazyn_numer.setBounds(225, 225, 86, 20);
		panel_2.add(magazyn_numer);
		magazyn_numer.setColumns(10);
		
		JLabel lblPolozenie = new JLabel("Polozenie");
		lblPolozenie.setBounds(109, 296, 89, 14);
		panel_2.add(lblPolozenie);
		
		magazyn_polozenie = new JTextField();
		magazyn_polozenie.setBounds(225, 293, 86, 20);
		panel_2.add(magazyn_polozenie);
		magazyn_polozenie.setColumns(10);
		
		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setBounds(321, 228, 55, 14);
		panel_2.add(lblOpis);
		
		magazyn_opis = new JTextField();
		magazyn_opis.setBounds(386, 225, 86, 20);
		panel_2.add(magazyn_opis);
		magazyn_opis.setColumns(10);
		
		//Dostawa
		
		JLayeredPane layeredPane_3 = new JLayeredPane();
		tabbedPane.addTab("Dostawa", null, layeredPane_3, null);
		layeredPane_3.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 793, 324);
		layeredPane_3.add(panel_3);
		panel_3.setLayout(null);
		
		btnAdd_dostawa = new JButton("Add");
		btnAdd_dostawa.setBounds(10, 224, 89, 23);
		panel_3.add(btnAdd_dostawa);
		
		btnUpdate_dostawa = new JButton("Update");
		btnUpdate_dostawa.setBounds(10, 292, 89, 23);
		panel_3.add(btnUpdate_dostawa);
		
		btnDelete_dostawa = new JButton("Delete");
		btnDelete_dostawa.setBounds(10, 258, 89, 23);
		panel_3.add(btnDelete_dostawa);
		

		
		DostawaTab = new JTable();
		DostawaTab.setBounds(10, 47, 773, 169);
		panel.add(DostawaTab);
			
			JScrollPane scrollPane_3 = new JScrollPane(DostawaTab);
			
			dostawca_imie = new JTextField();
			dostawca_imie.setColumns(10);
			dostawca_imie.setBounds(225, 225, 86, 20);
			panel.add(dostawca_imie);
			
			dostawca_nazwisko = new JTextField();
			dostawca_nazwisko.setColumns(10);
			dostawca_nazwisko.setBounds(225, 259, 86, 20);
			panel.add(dostawca_nazwisko);
			
			JLabel lblFirma = new JLabel("Firma");
			lblFirma.setBounds(321, 228, 46, 14);
			panel.add(lblFirma);
			
			dostawca_firma = new JTextField();
			dostawca_firma.setBounds(386, 225, 86, 20);
			panel.add(dostawca_firma);
			dostawca_firma.setColumns(10);
			scrollPane_3.setBounds(10, 11, 773, 202);
			panel_3.add(scrollPane_3);
			
			JLabel lblNumerMagazynu = new JLabel("Nr Magazynu");
			lblNumerMagazynu.setBounds(109, 228, 106, 14);
			panel_3.add(lblNumerMagazynu);
			
			JLabel lblNazwaDostawcy = new JLabel("Dostawca");
			lblNazwaDostawcy.setBounds(109, 262, 89, 14);
			panel_3.add(lblNazwaDostawcy);
			
			JLabel lblData = new JLabel("Data");
			lblData.setBounds(109, 296, 46, 14);
			panel_3.add(lblData);
			
			dostawa_data = new JTextField();
			Date today = new Date();
			String dateOut;
			DateFormat dateFormatter;
			dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
			dateOut = dateFormatter.format(today);
			dostawa_data.setText(dateOut);
			dostawa_data.setBounds(225, 293, 86, 20);
			panel_3.add(dostawa_data);
			dostawa_data.setColumns(10);
			
			JLabel lblKwota = new JLabel("Kwota");
			lblKwota.setBounds(321, 228, 46, 14);
			panel_3.add(lblKwota);
			
			dostawa_kwota = new JTextField();
			dostawa_kwota.setBounds(386, 225, 86, 20);
			panel_3.add(dostawa_kwota);
			dostawa_kwota.setColumns(10);
			
			dostawa_numermag = new JComboBox();
			dostawa_numermag.setBounds(225, 225, 86, 20);
			panel_3.add(dostawa_numermag);
			
			dostawa_numerdost = new JComboBox();
			dostawa_numerdost.setBounds(225, 259, 86, 20);
			panel_3.add(dostawa_numerdost);
	}
	
	public void ErrorDelete(){
	JOptionPane.showMessageDialog(null, "Nie zaznaczono ¿adnych danych!");
	}
	
	public void ErrorMagazyn(){
	JOptionPane.showMessageDialog(null, "Ju¿ istenie magazyn o takim oznaczeniu!");
	}
	
	public void ErrorFields(){
		JOptionPane.showMessageDialog(null, "Uzupe³nij wszystkie pola z danymi!");
	}
	
	//Dostawca get/listenery/metody
	
	public String getDostawcaImie(){
		return dostawca_imie.getText();
	}
	
	public String getDostawcaNazwisko(){
		return dostawca_nazwisko.getText();
	}
	
	public String getDostawcaCombo(){
		return (String)(dostawca_adres.getSelectedItem());
	}
	
	public String GetDostawcaFirma() {
		return dostawca_firma.getText();
	}
	public void AddComboDostawca(ResultSet rs){
		try {
			while(rs.next()){
				String pom = rs.getString("ulica");
				String pom1 = rs.getString("miasto");
				String pom2 = rs.getString("numer");
				String pom3 = rs.getString("kod");
				String pom4 = rs.getString("lp");
				String pom5 = pom4 +" "+ pom + " "+pom2 + " "+pom1+" "+pom3;
				dostawca_adres.addItem(pom5);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void addDostawcaListener(ActionListener listenForAddAdres){
		btnAdd_dostawca.addActionListener(listenForAddAdres);
	}
	
	void delDostawcaListener(ActionListener listenForDelAdres){
		btnDelete_dostawca.addActionListener(listenForDelAdres);
	}
	
	void upDostawcaListener(ActionListener listenForUpAdres){
		btnUpdate_dostawca.addActionListener(listenForUpAdres);
	}
	
	public void resultDostawca(ResultSet rs) throws SQLException{
        //Create new table model
        DefaultTableModel tableModel = new DefaultTableModel();

        //Retrieve meta data from ResultSet
        ResultSetMetaData metaData = rs.getMetaData();

        //Get number of columns from meta data
        int columnCount = metaData.getColumnCount();

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 1; columnIndex < columnCount+1; columnIndex++){
        	if((columnIndex==1)||(columnIndex==4)||(columnIndex==6)){
        		
        	}
        	else{
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        	}
        }

        //Create array of Objects with size of column count from meta data
        Object[] row = new Object[columnCount];

        //Scroll through result set
        
        while (rs.next()){
            //Get object from column with specific index of result set to array of objects
            for (int i = 0; i < columnCount; i++){
            	if(i==0){     		
            		row[i] = rs.getObject(i+2);
            		i++;
            	}
            	if(i==1){
            		row[i] = rs.getObject(i+2);
            		i++;
            	}
            	if(i==2){
            		row[i] = rs.getObject(i+3);
            		i++;
            	}
            	if(i==3){
            		row[i] = rs.getObject(i+4);
            		i++;
            	}
            	if(i==4){
               		row[i] = rs.getObject(i+4);
            		i++;
            	}
            	if(i==5){
               		row[i] = rs.getObject(i+4);
            		i++;
            	}
            	if(i==6){
               		row[i] = rs.getObject(i+4);
            		i++;
            	}
            	else{
            	row[i] = rs.getObject(i+1);
            	}
                
            }
            //Now add row to table model with that array of objects as an argument
            tableModel.addRow(row);
        }

        //Now add that table model to your table and you are done :D
        DostawcaTab.setModel(tableModel);
    }
	
	public int getRowDostawca(){
		int column =0;
		int row = DostawcaTab.getSelectedRow();
		int value = (int)(DostawcaTab.getModel().getValueAt(row, column));
		return value;
	}
	
	public void deleteDostawcaCombo(){
		dostawca_adres.removeAllItems();
	}
	
	
	
	//Adres gety/listenery/metody
	
	public String getUlicaAdres(){
		return adres_ulica.getText();		
	}
	
	public String getkodAdres(){
		return adres_kod.getText();		
	}
	
	public String getMiastoAdres(){
		return adres_miasto.getText();
	}
	
	public String getnumerAdres(){
		return adres_numer.getText();
	}
	
	void addAdresListener(ActionListener listenForAddAdres){
		btnAdd_adres.addActionListener(listenForAddAdres);
	}
	
	void delAdresListener(ActionListener listenForDelAdres){
		btnDelete_adres.addActionListener(listenForDelAdres);
	}
	
	void upAdresListener(ActionListener listenForUpAdres){
		btnUpdate_adres.addActionListener(listenForUpAdres);
	}
	
	public void resultAdres(ResultSet rs) throws SQLException{
        //Create new table model
        DefaultTableModel tableModel = new DefaultTableModel();

        //Retrieve meta data from ResultSet
        ResultSetMetaData metaData = rs.getMetaData();

        //Get number of columns from meta data
        int columnCount = metaData.getColumnCount();

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 2; columnIndex < columnCount+1; columnIndex++){
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        //Create array of Objects with size of column count from meta data
        Object[] row = new Object[columnCount];

        //Scroll through result set
        
        while (rs.next()){
            //Get object from column with specific index of result set to array of objects
            for (int i = 0; i < columnCount; i++){
            	if(i==0){
            		row[i] = rs.getObject(i+2);
            		i++;
            	}
            	if(i==1){
            		row[i] = rs.getObject(i+2);
            		i++;
            	}
            	if(i==2){
            		row[i] = rs.getObject(i+2);
            		i++;
            	}
            	if(i==3){
            		row[i] = rs.getObject(i+2);
            		i++;
            	}
            	else{
                row[i] = rs.getObject(i+1);
            	}
            }
            //Now add row to table model with that array of objects as an argument
            tableModel.addRow(row);
        }

        //Now add that table model to your table and you are done :D
        AdresTab.setModel(tableModel);
    }
	
	public int getRowAdres(){
		int column =0;
		int row = AdresTab.getSelectedRow();
		int value = (int)(AdresTab.getModel().getValueAt(row, column));
		return value;
	}
	
	public int DelTest(){
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(this, "Czy napewno chcesz usun¹æ zaznaczony rekord?", "", dialogButton);
		if(dialogResult == 0) {
		  return 1;
		} else {
		 return 0;
		} 
	}
	
	public int AddTest(){
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(this, "Czy napewno chcesz dodaæ rekord z podanymi wartosciami?", "", dialogButton);
		if(dialogResult == 0) {
		  return 1;
		} else {
		 return 0;
		} 
	}
	
	public int UpTest(){
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(this, "Czy napewno chcesz zast¹piæ zaznaczony rekord podanymi danymi??", "", dialogButton);
		if(dialogResult == 0) {
		  return 1;
		} else {
		 return 0;
		} 
	}
	
	//MAgazyn gety/listenery/metody
	
	
	public String getNumerMagazyn(){
		return magazyn_numer.getText();		
	}
	
	public String getPolozenieMagazyn(){
		return magazyn_polozenie.getText();		
	}
	
	public String getOpisMagazyn(){
		return magazyn_opis.getText();
	}
	
	public String getMiejscowoscMagazyn(){
		return magazyn_miejscowosc.getText();
	}
	
	void addMagazynListener(ActionListener listenForAddAdres){
		btnAdd_magazyn.addActionListener(listenForAddAdres);
	}
	
	void delMagazynListener(ActionListener listenForDelAdres){
		btnDelete_magazyn.addActionListener(listenForDelAdres);
	}
	
	void upMagazynListener(ActionListener listenForUpAdres){
		btnUpdate_magazyn.addActionListener(listenForUpAdres);
	}
	
	public void resultMagazyn(ResultSet rs) throws SQLException{
        //Create new table model
        DefaultTableModel tableModel = new DefaultTableModel();

        //Retrieve meta data from ResultSet
        ResultSetMetaData metaData = rs.getMetaData();

        //Get number of columns from meta data
        int columnCount = metaData.getColumnCount();

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 2; columnIndex < columnCount+1; columnIndex++){
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        //Create array of Objects with size of column count from meta data
        Object[] row = new Object[columnCount];

        //Scroll through result set
        
        while (rs.next()){
            //Get object from column with specific index of result set to array of objects
            for (int i = 0; i < columnCount; i++){
            	if(i==0){
            		row[i] = rs.getObject(i+2);
            		i++;
            	}
            	if(i==1){
            		row[i] = rs.getObject(i+2);
            		i++;
            	}
            	if(i==2){
            		row[i] = rs.getObject(i+2);
            		i++;
            	}
            	if(i==3){
            		row[i] = rs.getObject(i+2);
            		i++;
            	}
            	else{
                row[i] = rs.getObject(i+1);
            	}
            }
            //Now add row to table model with that array of objects as an argument
            tableModel.addRow(row);
        }

        //Now add that table model to your table and you are done :D
        MagazynTab.setModel(tableModel);
    }
	public int getRowMagazyn(){
		int column =0;
		int row = MagazynTab.getSelectedRow();
		int value = (int)(MagazynTab.getModel().getValueAt(row, column));
		return value;
	}

	//Dostawa  gety/listenery/metody
	
	public String getDostawaKwota(){
		return dostawa_kwota.getText();
	}
	
	public String getDostawaNumerDostawcy(){
		return (String)(dostawa_numerdost.getSelectedItem());
	}
	public String getDostawaNumerMagazynu(){
		return (String)(dostawa_numermag.getSelectedItem());
	}
	
	public String GetDostawaData() {
		return dostawa_data.getText();
	}
	
	public void AddComboDostawaNrDostawcy(ResultSet rs){
		try {
			while(rs.next()){
				String pom1 = rs.getString("lp");
				String pom = rs.getString("firma");
				String pom3 = pom1 + " " + pom;
				dostawa_numerdost.addItem(pom3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void AddComboDostawaNrMagazynu(ResultSet rs){
		try {
			while(rs.next()){
				String pom = rs.getString("lp");
				dostawa_numermag.addItem(pom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void addDostawaListener(ActionListener listenForAddAdres){
		btnAdd_dostawa.addActionListener(listenForAddAdres);
	}
	
	void delDostawaListener(ActionListener listenForDelAdres){
		btnDelete_dostawa.addActionListener(listenForDelAdres);
	}
	
	void upDostawaListener(ActionListener listenForUpAdres){
		btnUpdate_dostawa.addActionListener(listenForUpAdres);
	}
	
	public void resultDostawa(ResultSet rs) throws SQLException{
        //Create new table model
        DefaultTableModel tableModel = new DefaultTableModel();

        //Retrieve meta data from ResultSet
        ResultSetMetaData metaData = rs.getMetaData();

        //Get number of columns from meta data
        int columnCount = metaData.getColumnCount();

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 1; columnIndex < columnCount+1; columnIndex++){
        	if(columnIndex==1){
        		columnIndex++;
        	}
        	if(columnIndex==6){
        		columnIndex++;
        	}
        	if(columnIndex==11){
        		columnIndex++;
        	}
        	if(columnIndex==2){
        		columnIndex++;
        	}
        	if(columnIndex==3){
        		columnIndex++;
        	}
        	if(columnIndex==9){
        		columnIndex++;
        	}
        	else{
                tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        	}

        }

        //Create array of Objects with size of column count from meta data
        Object[] row = new Object[columnCount];

        //Scroll through result set
        
        while (rs.next()){
            //Get object from column with specific index of result set to array of objects
            for (int i = 0; i < columnCount; i++){
            	if(i==0){
            		row[i] = rs.getObject(i+4);
            		i++;
            	}
            	if(i==1){
            		row[i] = rs.getObject(i+4);
            		i++;
            	}
            	if(i==2){
            		row[i] = rs.getObject(i+5);
            		i++;
            	}
            	if(i==3){
            		row[i] = rs.getObject(i+5);
            		i++;
            	}
            	if(i==4){
            		row[i] = rs.getObject(i+8);
            		i++;
            	}
            	if(i==5){
            		row[i] = rs.getObject(i+8);
            		i++;
            	}
            	if(i==6){
            		row[i] = rs.getObject(i+8);
            		i++;
            	}
            	if(i==7){
            		row[i] = rs.getObject(i+8);
            		i++;
            	}
            	else{
            		row[i] = rs.getObject(i+1);	
            	}
                
            }
            //Now add row to table model with that array of objects as an argument
            tableModel.addRow(row);
        }

        //Now add that table model to your table and you are done :D
        DostawaTab.setModel(tableModel);
    }
	
	public int getRowDostawa(){
		int column =0;
		int row = DostawaTab.getSelectedRow();
		int value = (int)(DostawaTab.getModel().getValueAt(row, column));
		return value;
	}
	
	public void deleteDostawaComboMag(){
		dostawa_numermag.removeAllItems();
	}
	
	public void deleteDostawaComboDost(){
		dostawa_numerdost.removeAllItems();
	}
	
}
