package gui;
import javafx.application.Application;

public class Engine {
	
	public static void main(String[] args) {
		WindowMain gui = new WindowMain();
		Application.launch(gui.getClass(), args);
		
	}
	
}
