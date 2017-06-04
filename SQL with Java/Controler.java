package projekt;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Controler {

		private Model m_model;
		private View m_view;
	
		Controler(Model model, View view){
			this.m_model=model;
			this.m_view=view;
			
			//listenery
			
			this.m_view.addAdresListener(new AddAdresListener());
			this.m_view.delAdresListener(new DeleteAdresListener());
			this.m_view.upAdresListener(new UpdateAdresListener());
			
			this.m_view.addDostawcaListener(new AddDostawcaListener());
			this.m_view.delDostawcaListener(new DeleteDostawcaListener());
			this.m_view.upDostawcaListener(new UpdateDostawcaListener());
			
			this.m_view.addMagazynListener(new AddMagazynListener());
			this.m_view.delMagazynListener(new DeleteMagazynListener());
			this.m_view.upMagazynListener(new UpdateMagazynListener());
			
			this.m_view.addDostawaListener(new AddDostawaListener());
			this.m_view.delDostawaListener(new DeleteDostawaListener());
			this.m_view.upDostawaListener(new UpdateDostawaListener());
			
			
			
			// inicjalizacja
			try {
				
				this.m_view.resultAdres(m_model.getAdresTable());
				this.m_view.resultDostawca(m_model.getDostawcaTable());
				this.m_view.resultMagazyn(m_model.getMagazynTable());
				this.m_view.resultDostawa(m_model.getDostawaTable());
				
				ResultSet rs=this.m_model.DostawcaGetComboAdres();
				this.m_view.AddComboDostawca(rs);
				
				ResultSet rs1=this.m_model.DostawcaGetComboNrDost();
				this.m_view.AddComboDostawaNrDostawcy(rs1);
				
				ResultSet rs2=this.m_model.DostawcaGetComboNrMag();
				this.m_view.AddComboDostawaNrMagazynu(rs1);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}

		
	class AddAdresListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			 String kod;
			 String ulica;
			 String miasto;
			 String numer;
			 int test;
			 
			 try{
				 test=m_view.AddTest();
				 if(test==1){
					 
					 kod=m_view.getkodAdres();
					 ulica=m_view.getUlicaAdres();
					 miasto=m_view.getMiastoAdres();
					 numer=m_view.getnumerAdres();
					 
					 if((kod.isEmpty()==true) || (ulica.isEmpty()==true) || (miasto.isEmpty()==true) || (numer.isEmpty()==true))
					 {
						 m_view.ErrorFields();
					 }
					 else{
						 
						 m_model.AdresInsert(kod, ulica, miasto,numer);
						 m_view.resultAdres(m_model.getAdresTable());
						
						m_view.deleteDostawcaCombo();
						ResultSet rs=m_model.DostawcaGetComboAdres();
						m_view.AddComboDostawca(rs);
						 
					 }
					 

					 
				 }

				

				

				
				 
				 
			 }
			 catch(Exception err){
				 err.printStackTrace();
			 }
			
		}
		
	}
	
	class DeleteAdresListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int row = 0;
			int test;
			 
			 try{
				 test=m_view.DelTest();
				 if(test==1){
					 
					 row=m_view.getRowAdres();
					 m_model.AdresDelete(row);
					 m_view.resultAdres(m_model.getAdresTable());
					 
					m_view.deleteDostawcaCombo();
					ResultSet rs=m_model.DostawcaGetComboAdres();
					m_view.AddComboDostawca(rs);
					 
				 }
				 

			 }
			 catch(Exception err){
				m_view.ErrorDelete();
			 }
			
		}
		
	}
	
	class UpdateAdresListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			int row = 0;
			String ulica;
			String kod;
			String numer;
			String miasto;
			int test;
			
			try{
				test=m_view.UpTest();
				if(test==1){
					
					ulica=m_view.getUlicaAdres();
					kod=m_view.getkodAdres();
					numer=m_view.getnumerAdres();
					miasto=m_view.getMiastoAdres();
					row=m_view.getRowAdres();
					
					 if((kod.isEmpty()==true) || (ulica.isEmpty()==true) || (miasto.isEmpty()==true) || (numer.isEmpty()==true))
					 {
						 m_view.ErrorFields();
					 }
					 else{
					
					m_model.AdresUpdate(kod, ulica, miasto, numer, row);
					m_view.resultAdres(m_model.getAdresTable());
					
					m_view.deleteDostawcaCombo();
					ResultSet rs=m_model.DostawcaGetComboAdres();
					m_view.AddComboDostawca(rs);
					}
				}

			}
			catch(Exception err){
				m_view.ErrorDelete();
			}
			
		}
	}
		
		
		class AddDostawcaListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				 String imie;
				 String nazwisko;
				 String idadres;
				 String firma;
				 int test;
				 try{
					 test=m_view.AddTest();
					 if(test==1){
						 
						 imie=m_view.getDostawcaImie();
						 nazwisko=m_view.getDostawcaNazwisko();
						 idadres=m_view.getDostawcaCombo();
						 firma=m_view.GetDostawcaFirma();
						 
						 if((imie.isEmpty()==true)||(nazwisko.isEmpty()==true)||(idadres.isEmpty()==true)||(firma.isEmpty()==true))
						 {
							 m_view.ErrorFields();
						 }
						 else{
							 
							 String[] parts = idadres.split(" ");
							 int pom = Integer.parseInt(parts[0]);
							 
							 
							 m_model.DostawcaInsert(imie,nazwisko,pom, firma);
							 m_view.resultDostawca(m_model.getDostawcaTable());
							 
							m_view.deleteDostawaComboDost();
							ResultSet rs=m_model.DostawcaGetComboNrDost();
							m_view.AddComboDostawaNrDostawcy(rs);
							 
						 }
						 

						 
					 }

					 
				 }
				 catch(Exception err){
					 err.printStackTrace();
				 }
				
			}
			
		}
		
		class DeleteDostawcaListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int row = 0;
				int test;
				
				 try{
					 test=m_view.DelTest();
					 if (test==1){
						 
						 row=m_view.getRowDostawca();
						 m_model.DostawcaDelete(row);
						 m_view.resultDostawca(m_model.getDostawcaTable());
						 
							m_view.deleteDostawaComboDost();
							ResultSet rs=m_model.DostawcaGetComboNrDost();
							m_view.AddComboDostawaNrDostawcy(rs);
						 
					 }

				 }
				 catch(Exception err){
					m_view.ErrorDelete();
				 }
				
			}
			
		}
		
		class UpdateDostawcaListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				int row = 0;
				 String imie;
				 String nazwisko;
				 String idadres;
				 String firma;
				 int test;
				
				try{
					
					test=m_view.UpTest();
					if(test==1){
						
						row=m_view.getRowDostawca();
						firma=m_view.GetDostawcaFirma();	 
						imie=m_view.getDostawcaImie();
						nazwisko=m_view.getDostawcaNazwisko();
						idadres=m_view.getDostawcaCombo();
						
						 if((imie.isEmpty()==true)||(nazwisko.isEmpty()==true)||(idadres.isEmpty()==true)||(firma.isEmpty()==true))
						 {
							 m_view.ErrorFields();
						 }
						 else{
						
							 

						String[] parts = idadres.split(" ");
						int pom = Integer.parseInt(parts[0]);

						
						m_model.DostawcaUpdate(imie, nazwisko, pom, row, firma);
						m_view.resultDostawca(m_model.getDostawcaTable());
						
						m_view.deleteDostawaComboDost();
						ResultSet rs=m_model.DostawcaGetComboNrDost();
						m_view.AddComboDostawaNrDostawcy(rs);
						 }
					}

				}
				catch(Exception err){
					m_view.ErrorDelete();
				}
				
			}
	}
		
		class AddMagazynListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String polozenie;
				String opis;
				String miejscowosc;
				String numer;
				int test;
				 try{
					 test=m_view.AddTest();
					 if(test==1){
						 
						 polozenie=m_view.getPolozenieMagazyn();
						 opis=m_view.getOpisMagazyn();
						 miejscowosc=m_view.getMiejscowoscMagazyn();
						 numer=m_view.getNumerMagazyn();
						 
						 if((polozenie.isEmpty()==true)||(opis.isEmpty()==true)||(miejscowosc.isEmpty()==true)||(numer.isEmpty()==true))
						 {
							 m_view.ErrorFields();
						 }
						 else{
							 int pom= Integer.parseInt(numer);
							 
							 m_model.MagazynInsert(polozenie, opis, miejscowosc, pom);
							 m_view.resultMagazyn(m_model.getMagazynTable());
							 
								m_view.deleteDostawaComboMag();
								ResultSet rs=m_model.DostawcaGetComboNrMag();
								m_view.AddComboDostawaNrMagazynu(rs);
						 }

						 
					 }
					 

					 
				 }
				 catch(Exception err){
					m_view.ErrorMagazyn();
				 }
				
			}
			
		}
		
		class DeleteMagazynListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int row = 0;
				int test;
				 
				 try{
					 
					 test=m_view.DelTest();
					 if(test==1){
						 
						 row=m_view.getRowMagazyn();
						 m_model.MagazynDelete(row);
						 m_view.resultMagazyn(m_model.getMagazynTable());
						 
							m_view.deleteDostawaComboMag();
							ResultSet rs=m_model.DostawcaGetComboNrMag();
							m_view.AddComboDostawaNrMagazynu(rs);
						 
					 }

				 }
				 catch(Exception err){
					 m_view.ErrorMagazyn();
					 

					
				 }
				
			}
			
		}
		
		class UpdateMagazynListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				int row = 0;
				String polozenie;
				String opis;
				String miejscowosc;
				String numer;
				int test;
				
				
				 try{
					 test=m_view.UpTest();
					 if(test==1){
						 
						 polozenie=m_view.getPolozenieMagazyn();
						 opis=m_view.getOpisMagazyn();
						 miejscowosc=m_view.getMiejscowoscMagazyn();
						 numer=m_view.getNumerMagazyn();
						 
						 if((polozenie.isEmpty()==true)||(opis.isEmpty()==true)||(miejscowosc.isEmpty()==true)||(numer.isEmpty()==true))
						 {
							 m_view.ErrorFields();
						 }
						 else{
						 
						 int pom= Integer.parseInt(numer);
						 
						 m_model.MagazynUpdate(polozenie, opis, miejscowosc, pom, row);
						 m_view.resultMagazyn(m_model.getMagazynTable());
						 
							m_view.deleteDostawaComboMag();
							ResultSet rs=m_model.DostawcaGetComboNrMag();
							m_view.AddComboDostawaNrMagazynu(rs);
						 }
					 }
					 

					 
			}
			catch(Exception err){
				err.printStackTrace();
				}
			}
		}
		
		class AddDostawaListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String magazyn;
				String dostawca;
				String data;
				String kwota;
				int test;
				 
				 try{
					 
					 test=m_view.AddTest();
					 if(test==1){
						 
						 magazyn=m_view.getDostawaNumerMagazynu();
						 dostawca=m_view.getDostawaNumerDostawcy();
						 data=m_view.GetDostawaData();
						 kwota=m_view.getDostawaKwota();
						 
						 
						 
						 if((magazyn.isEmpty()==true)||(dostawca.isEmpty()==true)||(data.isEmpty()==true)||(kwota.isEmpty()==true))
						 {
							 m_view.ErrorFields();
						 }
						 else{
							 
							 String[] parts = dostawca.split(" ");
							 int pomm = Integer.parseInt(magazyn);
							 int pomd = Integer.parseInt(parts[0]);
							 double pomk = Double.parseDouble(kwota);
							 
							 m_model.DostawaInsert(pomm, pomd, data, pomk);
							 m_view.resultDostawa(m_model.getDostawaTable());
						 }
						 
					 }
					  
				 }
				 catch(Exception err){
					 err.printStackTrace();
				 }
				
			}
			
		}
		
		class DeleteDostawaListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int row = 0;
				int test;
				 
				 try{
					 test=m_view.DelTest();
					 if(test==1){
						 
						 row=m_view.getRowDostawa();
						 m_model.DostawaDelete(row);
						 m_view.resultDostawa(m_model.getDostawaTable());
						 
					 }

				 }
				 catch(Exception err){
					m_view.ErrorDelete();
				 }
				
			}
			
		}
		
		class UpdateDostawaListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				int row = 0;
				String magazyn;
				String dostawca;
				String data;
				String kwota;
				int test;
				
				try{
					test=m_view.UpTest();
					if(test==1){
						
						 magazyn=m_view.getDostawaNumerMagazynu();
						 dostawca=m_view.getDostawaNumerDostawcy();
						 data=m_view.GetDostawaData();
						 kwota=m_view.getDostawaKwota();
						 row=m_view.getRowDostawa();
						 
						 if((magazyn.isEmpty()==true)||(dostawca.isEmpty()==true)||(data.isEmpty()==true)||(kwota.isEmpty()==true))
						 {
							 m_view.ErrorFields();
						 }
						 else{
						 
							 String[] parts = dostawca.split(" ");
							 int pomm = Integer.parseInt(magazyn);
							 int pomd = Integer.parseInt(parts[0]);
							 double pomk = Double.parseDouble(kwota);
						
						m_model.DostawaUpdate(pomm, pomd, data, pomk, row);
						m_view.resultDostawa(m_model.getDostawaTable());
						 }
					}

				}
				catch(Exception err){
					err.printStackTrace();
					m_view.ErrorDelete();
				}
				
			}
	}
		
}

