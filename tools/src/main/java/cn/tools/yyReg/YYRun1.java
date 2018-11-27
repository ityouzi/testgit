package cn.tools.yyReg;

public class YYRun1 extends Thread {
	private String ip;
	private int port;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void run(){
		for(int i =0;i<20;i++){
			YYRun2 run = new YYRun2();
			run.setIp(getIp());
			run.setPort(getPort());
			run.start();
		}
	}
	
}