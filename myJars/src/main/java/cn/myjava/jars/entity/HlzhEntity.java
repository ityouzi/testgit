package cn.myjava.jars.entity;

import java.util.List;

public class HlzhEntity {
	private String hbh;					//航班号
	
	private String date;				//航班日期
	
	private String hbinfo;				//航班信息
	
	private List<HlzhEntityMx1> pinfo;		//历史信息

	private List<HlzhEntityMx2> flybox;		//航段信息

	public String getHbh() {
		return hbh;
	}

	public void setHbh(String hbh) {
		this.hbh = hbh;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHbinfo() {
		return hbinfo;
	}

	public void setHbinfo(String hbinfo) {
		this.hbinfo = hbinfo;
	}

	public List<HlzhEntityMx1> getPinfo() {
		return pinfo;
	}

	public void setPinfo(List<HlzhEntityMx1> pinfo) {
		this.pinfo = pinfo;
	}

	public List<HlzhEntityMx2> getFlybox() {
		return flybox;
	}

	public void setFlybox(List<HlzhEntityMx2> flybox) {
		this.flybox = flybox;
	}

	@Override
	public String toString() {
		return "EntityFull [hbh=" + hbh + ", date=" + date + ", hbinfo="
				+ hbinfo + ", pinfo=" + pinfo + ", flybox=" + flybox + "]";
	}
	
}