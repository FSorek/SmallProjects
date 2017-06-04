package projekt;

public class Main {

	public static void main(String[] args){
		
		Model theModel = new Model();
		
		View theView = new View();
		
		Controler theController = new Controler(theModel, theView);
		
		theView.frmCrud.setVisible(true);
		
	}
	
}
