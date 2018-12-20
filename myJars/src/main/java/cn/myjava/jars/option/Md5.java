package cn.myjava.jars.option;

import com.gexin.rp.sdk.base.uitls.MD5Util;

public class Md5 {
	
	public static void main(String[] args) {
		
		String r = MD5Util.getMD5Format("phone=18671436670timeStamp=1539156417975"+"64688142ca8a14ef1307fd2794771d37");
		r = MD5Util.getMD5Format(r+"64688142ca8a14ef1307fd2794771d37");
		System.out.println(r);
		
	}

}