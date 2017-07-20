package gnnt.MEBS.security.vo;

public class UriParamVO {
	private String uri;
	private String param;
	
	public UriParamVO(String uri, String param){
		this.uri = uri;
		this.param = param;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
}
