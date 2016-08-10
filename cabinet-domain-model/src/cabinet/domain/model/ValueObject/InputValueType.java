package cabinet.domain.model.ValueObject;
public enum InputValueType 
{
	SingleText(0),
	MultisText(1), 
	Number(2), 
	SingleTag(3),
	MulitsTag(4);
	
	private int i;
	
	private InputValueType(int i)
	{
		this.i=i;
	}
	
	public static InputValueType valueOf(int i)
	{
		switch (i) {
		case 0:
			return InputValueType.SingleText;
		case 1:
			return InputValueType.MultisText;
		case 2:
			return InputValueType.Number;
		case 3:
			return InputValueType.SingleTag;
		case 4:
			return InputValueType.MulitsTag;
			default:
				return null;
		}
	}
	public String getText()
	{
		
		switch (this.i) {
		case 0:
			return "单文本";
		case 1:
			return "多文本";
		case 2:
			return "数字";
		case 3:
			return "单选标签";
		case 4:
			return "多选标签";
		default:
		return "";
		}
	}
}
