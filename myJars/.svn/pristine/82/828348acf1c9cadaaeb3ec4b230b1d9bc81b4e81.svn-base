package cn.myjava.jars.test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TCRegTest {

	public static void main(String[] args) throws IOException {
		
//		https://captcha.guard.qcloud.com/getcap?appid=1251317268&uid=5965&buid=6&sceneid=1&captype=8&sig=b016NWpydimxzT0wW8Ue9X6GbqHRgH1EAHzsn1d-OZmSV1iNt85I-Y8K-SCgj2pNdHv2pFxYNHDgEAwqFZD7yu7u6MPs_PYjLfbp2uFHbJzeZkNp-_0SUAu9Q**&0.7499123302043984
		
		String url = "https://passport.ly.com/AjaxHandler/RegisterCheckHandler.ashx?tdleonid=tdleonid";
		Connection con = Jsoup.connect(url);
		con.data("action", "CheckAllJudgement");
		con.data("mobile", "18671436670");
		con.data("checkcode", "");
		con.data("ticket", "P2JA8f12IYGSVnCzRbWdzXXeZgR61Qy3ar9pnlaZk60u3WhmPmvEx5fPO8F_hKLp");
		con.data("refid", "");
		con.data("randno", "");
		con.data("cardno", "");
		con.data("areaCode", "86");
		
		Document doc = con.ignoreContentType(true).post();
		System.out.println(doc);
	}
	
}