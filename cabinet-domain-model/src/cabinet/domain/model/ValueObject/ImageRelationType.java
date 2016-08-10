package cabinet.domain.model.ValueObject;

public enum ImageRelationType {
	Goods(0), 
	Info(1);

	private int i;

	private ImageRelationType(int i) {
		this.i = i;
	}

	public static ImageRelationType valueOf(int i)
	{
		switch (i) {
		case 0:
			return ImageRelationType.Goods;
		case 1:
			return ImageRelationType.Info;
		default:
			return null;
		}
	}
	
	public int getValue()
	{
		return this.i;
	}
}
