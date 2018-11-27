package cn.tools.secret;

public class MySecret {
	
	//加密
	public String getSecret(String show){
		return show.replace("--", show.subSequence(0, 1)+"ivw"+show.substring(show.length()-1, show.length()));
	}
	
	//解密
	public String[] getShow(String secret){
		return secret.split(secret.subSequence(0, 1)+"ivw"+secret.substring(secret.length()-1, secret.length()));
	}
	
	public static void main(String[] args) {
		System.out.println(new MySecret().getShow("201711251249263801679452ivw3d265c4975640f254f1026ac2792ea7f3")[1]);
	}

}