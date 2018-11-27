package cn.tools.hlzh;

public class EntityMx1 {
	private String city;			//城市对
	
	private String mileage;			//总里程
	
	private String time;			//全程时长
	
	private String age;				//机型/机龄
	
	private String per;				//历史准点率

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	@Override
	public String toString() {
		return "EntityMx1 [city=" + city + ", mileage=" + mileage + ", time="
				+ time + ", age=" + age + ", per=" + per + "]";
	}
	
}