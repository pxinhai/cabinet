package cabinet.web.util;

import org.codehaus.jackson.map.ObjectMapper;

public class UnifiedOut<T> {

	private int errorCode;
	private String message;
	private T data;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		if(this.errorCode==500)
		{
		return "程序内部错误";	
		}
		else
		{
		return this.message;
		}
	}

	public void setMessage(String message) {
		this.message= message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public String toString()
	{
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			return "{\"errorCode:99\",\"errorMessage\":\"序列化失败\"}";
		} 
	}

}
