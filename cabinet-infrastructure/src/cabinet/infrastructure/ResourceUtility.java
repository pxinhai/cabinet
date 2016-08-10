package cabinet.infrastructure;

public class ResourceUtility {

	private static String baseDirectory;

	static {
		baseDirectory =ResourceUtility.class.getResource("").getFile().replace("file:/","");
		baseDirectory=baseDirectory.substring(0, baseDirectory.indexOf("/WEB-INF/")+9);
	}

	public static String getBaseDirectory() {
		return baseDirectory;
	}
}
