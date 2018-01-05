package modeles;

import javafx.scene.Node;
import main.Init;

public interface Photo {

	public void drawPhoto(Init init);

	public Node getText();

	public Node getSelectedImage();

	public Node getCadre();
}
