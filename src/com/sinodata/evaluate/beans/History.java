package com.sinodata.evaluate.beans;

public class History {
	
	private String resultID;
	private String mainHabitus;
	private String mainHabitusLevel;
	private String otherHabitus;
	private String accountId;
	private String realName;
	private String createTime;
	private String aScore;
	private String bScore;
	private String cScore;
	private String dScore;
	private String eScore;
	private String fScore;
	private String gScore;
	private String hScore;
	private String iScore;
	
	public History() {
		super();
		// TODO Auto-generated constructor stub
	}

	public History(String resultID, String mainHabitus,
			String mainHabitusLevel, String otherHabitus, String accountId,
			String realName, String createTime, String aScore, String bScore,
			String cScore, String dScore, String eScore, String fScore,
			String gScore, String hScore, String iScore) {
		super();
		this.resultID = resultID;
		this.mainHabitus = mainHabitus;
		this.mainHabitusLevel = mainHabitusLevel;
		this.otherHabitus = otherHabitus;
		this.accountId = accountId;
		this.realName = realName;
		this.createTime = createTime;
		this.aScore = aScore;
		this.bScore = bScore;
		this.cScore = cScore;
		this.dScore = dScore;
		this.eScore = eScore;
		this.fScore = fScore;
		this.gScore = gScore;
		this.hScore = hScore;
		this.iScore = iScore;
	}

	public String getResultID() {
		return resultID;
	}

	public void setResultID(String resultID) {
		this.resultID = resultID;
	}

	public String getMainHabitus() {
		return mainHabitus;
	}

	public void setMainHabitus(String mainHabitus) {
		this.mainHabitus = mainHabitus;
	}

	public String getMainHabitusLevel() {
		return mainHabitusLevel;
	}

	public void setMainHabitusLevel(String mainHabitusLevel) {
		this.mainHabitusLevel = mainHabitusLevel;
	}

	public String getOtherHabitus() {
		return otherHabitus;
	}

	public void setOtherHabitus(String otherHabitus) {
		this.otherHabitus = otherHabitus;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getaScore() {
		return aScore;
	}

	public void setaScore(String aScore) {
		this.aScore = aScore;
	}

	public String getbScore() {
		return bScore;
	}

	public void setbScore(String bScore) {
		this.bScore = bScore;
	}

	public String getcScore() {
		return cScore;
	}

	public void setcScore(String cScore) {
		this.cScore = cScore;
	}

	public String getdScore() {
		return dScore;
	}

	public void setdScore(String dScore) {
		this.dScore = dScore;
	}

	public String geteScore() {
		return eScore;
	}

	public void seteScore(String eScore) {
		this.eScore = eScore;
	}

	public String getfScore() {
		return fScore;
	}

	public void setfScore(String fScore) {
		this.fScore = fScore;
	}

	public String getgScore() {
		return gScore;
	}

	public void setgScore(String gScore) {
		this.gScore = gScore;
	}

	public String gethScore() {
		return hScore;
	}

	public void sethScore(String hScore) {
		this.hScore = hScore;
	}

	public String getiScore() {
		return iScore;
	}

	public void setiScore(String iScore) {
		this.iScore = iScore;
	}

	@Override
	public String toString() {
		return "Histroy [resultID=" + resultID + ", mainHabitus=" + mainHabitus
				+ ", mainHabitusLevel=" + mainHabitusLevel + ", otherHabitus="
				+ otherHabitus + ", accountId=" + accountId + ", realName="
				+ realName + ", createTime=" + createTime + ", aScore="
				+ aScore + ", bScore=" + bScore + ", cScore=" + cScore
				+ ", dScore=" + dScore + ", eScore=" + eScore + ", fScore="
				+ fScore + ", gScore=" + gScore + ", hScore=" + hScore
				+ ", iScore=" + iScore + "]";
	}
	
}
