package modeles;

import controllers.Init;
import controllers.PhotoController;

public interface Photo {
	
	public void drawPhoto(Init init);
	
	public PhotoController getPhotoController();
	public void setPhotoController(PhotoController photoController);

}
