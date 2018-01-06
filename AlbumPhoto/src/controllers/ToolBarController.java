package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class ToolBarController {
	private PhotoController photo;
	private Init init;
	private CheckBox checkbox;
	
	
	public ToolBarController(PhotoController photo, Init init) {
		super();
		this.photo = photo;
		this.init = init;
		this.afficherToolBar();
		
	}


	public void afficherToolBar() {
		this.init.getRoot().lookup("#toolBar").setManaged(true);
    	this.init.getRoot().lookup("#toolBar").setVisible(true);	
    	this.checkbox = (CheckBox) init.getRoot().lookup("#checkbox_bordure");
    	this.checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
            		if(checkbox.isSelected())
            		{
            			System.out.println("CADRE");
            			photo.ajouterCadre();
            			//init.getRoot().lookup("#choicebox_bordure").getStyleClass().add("border-simple");
            			//getSelectedImage().getStyleClass().add("border-simple");
            		}
            		else
            		{
            			System.out.println("NON CADRE");
            			photo.supprimerCadre();
            			//init.getRoot().lookup("#choicebox_bordure").getStyleClass().remove("border-simple");
            			//getSelectedImage().getStyleClass().remove("border-simple");
            		}
            		
                }
            });
	}


	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	
	
	
	
	
}
