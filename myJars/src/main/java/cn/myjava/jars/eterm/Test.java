package cn.myjava.jars.eterm;

import org.htmlparser.util.ParserException;

public class Test {
	public static void main(String[] args) throws ParserException {
		VeShareWebServiceService veShareWebServiceService = new VeShareWebServiceService();
		VeShareWebServiceServiceSoap vesoap = veShareWebServiceService.getVeShareWebServiceServiceSoap();
		String xml = vesoap.processRequest("avh pekwuh+", "test", "test");
		System.out.println(xml);
	}
}