package cabinet.infrastructure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterUtility {

	public static String empty()
	{
		return "";
	}
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static int ConverterToInt(String str) {
		int rVal = 0;
		if (!isNullOrEmpty(str)) {
			try {
				rVal = Integer.parseInt(str);
			} catch (Exception e) {
			}
		}
		return rVal;
	}

	public static boolean IsNullOrWhiteSpace(String str) {
		boolean rVal = isNullOrEmpty(str);
		if (!rVal) {
			char[] arr = str.toCharArray();
			for (char c : arr) {
				if (c != ' ') {
					rVal = false;
					break;
				}
			}
		}
		return rVal;
	}

	public static String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		// <p>段落替换为换行
		htmlStr = htmlStr.replaceAll("<p .*?>", "\r\n");
		htmlStr = htmlStr.replaceAll("<br\\s*/?>", "\r\n");
		htmlStr = htmlStr.replaceAll("\\<.*?>", "");
			
		return htmlStr.trim(); // 返回文本字符串
	}
}
