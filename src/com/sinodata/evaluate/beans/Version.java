package com.sinodata.evaluate.beans;

public class Version {
//[{"id":"3","result":"000000","apkName":"jlbankv2.8.0.apk","releaseDate":"20150604","url":"http://123.57.4.158:8080/Assessment/uploadFile/jlbankv2.8.0.apk","version":"2"}]
	private String id;
	private String result;
	private String apkName;
	private String releaseDate;
	private String url;
	private String version;
	public Version() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Version(String id, String result, String apkName,
			String releaseDate, String url, String version) {
		super();
		this.id = id;
		this.result = result;
		this.apkName = apkName;
		this.releaseDate = releaseDate;
		this.url = url;
		this.version = version;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getApkName() {
		return apkName;
	}
	public void setApkName(String apkName) {
		this.apkName = apkName;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Version [id=" + id + ", result=" + result + ", apkName="
				+ apkName + ", releaseDate=" + releaseDate + ", url=" + url
				+ ", version=" + version + "]";
	}
	
	
	
}
