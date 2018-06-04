package yhy.exception;


public class ServiceException extends Exception{

	private static final long serialVersionUID = -3172803700995059492L;
	private String errorCode;
	private String errorMessage;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public ServiceException(String errorCode) {
	    super("Error Code:" + errorCode);
	    this.errorCode = errorCode;
	}
	
	public ServiceException(String errorCode, String errorMessage) {
		super("Error Code:" + errorCode+"Error Message"+errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public ServiceException(String errorCode, Exception e) {
		super("Error Code:" + errorCode,e);
		this.errorCode = errorCode;
		//this.errorMessage= PropertiesLoaderUtils.getString(Constants.CONFIG_NAME.SYSTEM_CODE, errorCode);
	}
	
	public ServiceException(String errorCode, String errorMessage, Exception e) {
		super("Error Code:" + errorCode+":Error Message"+errorMessage,e);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

}
