package com.java.demo1;

import java.io.FileNotFoundException;

public class PreciseExceptionExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void rethrowException(String exceptionName) throws Exception {
		try {
			if (exceptionName.equals("First")) {
				throw new FirstException();
			} else {
				throw new SecondException();
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * if the caught exception is not reassigned  then precise is enabled
	 * @param exceptionName
	 * @throws Exception
	 */
	 public void rethrowExceptionPrecise(String exceptionName)
			  throws FirstException, SecondException,FileNotFoundException {
			    try {
			    	if (exceptionName.equals("First")) {
						throw new FirstException();
					} else {
						throw new SecondException();
					}
			    }
			    catch (Exception e) {			    
			      throw e;
			    }
			  }
	
	/**
	 * if the exception is reassigned then precise is disabled
	 * @param exceptionName
	 * @throws Exception
	 */
	 public void rethrowExceptionPreciseDisabled(String exceptionName)
			  throws Exception {//FirstException, SecondException {
			    try {
			    	if (exceptionName.equals("First")) {
						throw new FirstException();
					} else {
						throw new SecondException();
					}
			    }
			    catch (Exception e) {
			    	e=new FileNotFoundException();
			      throw e;
			    }
			  }

}

class FirstException extends Exception {
}

class SecondException extends Exception {
}
