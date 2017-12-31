package modeles;

public abstract class PhotoDecorator implements Photo{
	protected Photo decoratedPhoto; // l'image d�cor�e

    public PhotoDecorator (Photo decoratedPhoto)
    {
        this.decoratedPhoto = decoratedPhoto;
    }
}
