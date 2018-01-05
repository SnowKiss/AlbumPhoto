package modeles;

import controllers.Init;
import controllers.PhotoController;

public class Cadre extends PhotoDecorator {

	public Cadre(Photo decoratedPhoto) {
		super(decoratedPhoto);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawPhoto(Init init) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhotoController getPhotoController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPhotoController(PhotoController photoController) {
		// TODO Auto-generated method stub
		
	}

}
