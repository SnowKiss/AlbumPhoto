package modeles;

import javafx.scene.Node;
import main.Main;

public interface Photo {

	public void drawPhoto(Main main);

	public Node getText();

	public Node getSelectedImage();
}
