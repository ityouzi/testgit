package cn.tools.yy;

public class Test  extends Thread {
	
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
	public void run() {
		for(int i =0;i<20;i++){
			YYRegRun run = new YYRegRun();
			run.setIp(ip);
			run.setPort(port);
			run.start();
		}
	}
}