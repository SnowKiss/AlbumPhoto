package modeles;

public abstract class PhotoDecorator implements Photo{
	protected Photo decoratedPhoto; // l'image décorée

    public PhotoDecorator (Photo decoratedPhoto)
    {
        this.decoratedPhoto = decoratedPhoto;
    }
}
