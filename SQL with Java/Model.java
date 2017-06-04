package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Model {
	
	Connection c = null;
	boolean polaczenie = false;
	Statement stmt = null;
	
	public Connection getConnection(){
		Connection con;
		try{
			
			con= DriverManager.getConnection("jdbc:sqlite:test.db");
			//jdbc:sqlite:/~/test.db
			//jdbc:sqlite:E:/PROGRAMJAVA/Workspace/CRUD/src/test.db
			return con;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
		Model(){ //konstruktor + po��czenie z baz� danych
			

	
	c=getConnection();
		
	String sql = "CREATE TABLE IF NOT EXISTS Adres"+			
			"(lp INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"ulica VARCHAR(40),"+
			"numer VARCHAR(40),"+
			"miasto VARCHAR(40),"+
			"kod VARCHAR(40))";
	
	try {
	stmt = c.createStatement();
	stmt.execute(sql);
	} 
	catch (SQLException e) {
	e.printStackTrace();
	}
	
	sql ="CREATE TABLE IF NOT EXISTS Dostawca"+					
			"(lp INTEGER PRIMARY KEY,"+
			"imie VARCHAR(40),"+
			"nazwisko VARCHAR(40),"+
			"adres INTEGER,"+
			"firma VARCHAR(40),"+
			"FOREIGN KEY(adres) REFERENCES Adres(id_adres))";
	
	try {
	stmt.execute(sql);
	} 
	catch (SQLException e) {
	e.printStackTrace();
	}
	
	sql = "CREATE TABLE IF NOT EXISTS Magazyn"+					
			"(lp INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"polozenie VARCHAR(40),"+
			"opis VARCHAR(40),"+
			"miejscowosc VARCHAR(40),"+
			"oznaczenie INTEGER NOT NULL UNIQUE)";
	
	try {
	stmt.execute(sql);
	} 
	catch (SQLException e) {
	e.printStackTrace();
	}
	
	sql = "CREATE TABLE IF NOT EXISTS Dostawa"+
			"(lp INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"magazyn INTEGER,"+
			"dostawca INTEGER,"+
			"data VARCHAR(40),"+
			"kwota REAL NOT NULL,"+			
			"FOREIGN KEY(magazyn) REFERENCES Magazyn(id_magazyn),"+
			"FOREIGN KEY(dostawca) REFERENCES Dostawca(id_dostawca))";
	
	try {
	stmt.execute(sql);
	} 
	catch (SQLException e) {
	e.printStackTrace();
	}
	
	}
	
	//Adres metody
	
	public ResultSet getAdresTable(){
		String query="SELECT * FROM Adres";
		PreparedStatement pst;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(query);
			rs = pst.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}
		
	void AdresInsert(String kod, String ulica, String miasto, String numer){
		String zap = "INSERT INTO Adres(ulica, miasto, kod, numer) VALUES ('"+ulica+"','"+miasto+"','"+kod+"','"+numer+"')";
		try {
			stmt.execute(zap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	void AdresDelete(int selectedrow){
		String zap = "DELETE FROM Adres WHERE lp="+selectedrow+"";
		try {
			stmt.executeUpdate(zap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	void AdresUpdate(String kod, String ulica, String miasto, String numer, int selectedrow){
		String zap = "UPDATE Adres SET kod='"+kod+"', miasto='"+miasto+"', numer='"+numer+"', ulica='"+ulica+"' WHERE lp="+selectedrow+"";
		try {
			stmt.executeUpdate(zap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	 //Magazyn metody
	
	public ResultSet getMagazynTable(){
		String query="SELECT * FROM Magazyn";
		PreparedStatement pst;
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(query);
			rs = pst.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}
	
		void MagazynInsert(String polozenie, String opis, String miejscowosc, int numer){
			String zap = "INSERT INTO Magazyn(polozenie, opis, miejscowosc, oznaczenie) VALUES ('"+polozenie+"', '"+opis+"', '"+miejscowosc+"', "+numer+")";
			try {
				stmt.execute(zap);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ju� istenie magazyn o takim numerze!");
			}
		}
			
		void MagazynDelete(int selectedrow){
			String zap = "DELETE FROM Magazyn WHERE lp="+selectedrow+"";
			try {
				stmt.executeUpdate(zap);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		void MagazynUpdate(String polozenie, String opis, String miejscowosc, int numer, int selectedrow){
			String zap = "UPDATE Magazyn SET polozenie='"+polozenie+"', opis='"+opis+"', miejscowosc='"+miejscowosc+"', oznaczenie="+numer+" WHERE lp="+selectedrow+"";
			try {
				stmt.executeUpdate(zap);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//Dostawca metody
		
		public ResultSet getDostawcaTable(){
			String query="SELECT * FROM Dostawca CROSS JOIN Adres";
			PreparedStatement pst;
			ResultSet rs = null;
			try {
				pst = c.prepareStatement(query);
				rs = pst.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;		
		}

			ResultSet DostawcaGetComboAdres(){
				String zap = "SELECT * FROM Adres";
				ResultSet rs = null;
				try {
					rs = stmt.executeQuery(zap);
				} catch (SQLException e) {
					e.printStackTrace();
				}		
				return rs;
		
			}	
			
			void DostawcaInsert(String imie, String nazwisko, int idadres, String firma){
				String zap = "INSERT INTO Dostawca(imie, nazwisko, adres, firma) VALUES ('"+imie+"', '"+nazwisko+"', "+idadres+",'"+firma+"')";
				try {
					stmt.execute(zap);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				
			void DostawcaDelete(int selectedrow){
				String zap = "DELETE FROM Dostawca WHERE lp="+selectedrow+"";
				try {
					stmt.executeUpdate(zap);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			void DostawcaUpdate(String imie, String nazwisko, int idadres, int selectedrow, String firma){
				String zap = "UPDATE Dostawca SET imie='"+imie+"', nazwisko='"+nazwisko+"', id_adres="+idadres+", firma='"+firma+"' WHERE lp="+selectedrow+"";
				try {
					stmt.executeUpdate(zap);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
			//Dostawa metody
			
			public ResultSet getDostawaTable(){
				String query="SELECT * FROM Dostawa CROSS JOIN Dostawca CROSS JOIN Magazyn";
				PreparedStatement pst;
				ResultSet rs = null;
				try {
					pst = c.prepareStatement(query);
					rs = pst.executeQuery();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return rs;		
			}

				ResultSet DostawcaGetComboNrMag(){
					String zap = "SELECT * FROM Magazyn";
					ResultSet rs = null;
					try {
						rs = stmt.executeQuery(zap);
					} catch (SQLException e) {
						e.printStackTrace();
					}		
					return rs;
			
				}	
				
				
				ResultSet DostawcaGetComboNrDost(){
					String zap = "SELECT * FROM Dostawca";
					ResultSet rs = null;
					try {
						rs = stmt.executeQuery(zap);
					} catch (SQLException e) {
						e.printStackTrace();
					}		
					return rs;
				}
				

				
				void DostawaInsert(int magazyn, int dostawca, String data, double kwota){
					String zap = "INSERT INTO Dostawa(magazyn, dostawca, data, kwota) VALUES ("+magazyn+", "+dostawca+", '"+data+"', "+kwota+")";
					try {
						stmt.execute(zap);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
					
				void DostawaDelete(int selectedrow){
					String zap = "DELETE FROM Dostawa WHERE lp="+selectedrow+"";
					try {
						stmt.executeUpdate(zap);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				void DostawaUpdate(int magazyn, int dostawca, String data, double kwota, int selectedrow){
					String zap = "UPDATE Dostawa SET magazyn='"+magazyn+"', dostawca='"+dostawca+"', data='"+data+"', kwota="+kwota+" WHERE lp="+selectedrow+"";
					try {
						stmt.executeUpdate(zap);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			
}
	

	
	
	


