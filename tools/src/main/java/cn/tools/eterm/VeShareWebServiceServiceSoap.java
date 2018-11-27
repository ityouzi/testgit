package cn.tools.eterm;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "VeShareWebServiceServiceSoap", targetNamespace = "urn:VeShareWebServiceService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface VeShareWebServiceServiceSoap {

	/**
	 * 
	 * @param bstrUserName
	 * @param bstrCmd
	 * @param bstrPassword
	 * @return returns java.lang.String
	 */
	@WebMethod(operationName = "ProcessRequest")
	@WebResult(partName = "return")
	public String processRequest(
			@WebParam(name = "bstrCmd", partName = "bstrCmd") String bstrCmd,
			@WebParam(name = "bstrUserName", partName = "bstrUserName") String bstrUserName,
			@WebParam(name = "bstrPassword", partName = "bstrPassword") String bstrPassword);

}
