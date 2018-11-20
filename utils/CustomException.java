package com.nn.utils;

public class CustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	    private String errorCode;
	
	    private boolean propertiesKey = true;
	    
	    public CustomException(){};
	
	    public CustomException(String message){
	    	
	        super(message);
	        
	    }

}