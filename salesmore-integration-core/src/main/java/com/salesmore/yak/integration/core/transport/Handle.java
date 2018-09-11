package com.salesmore.yak.integration.core.transport;

/**
 * A handle which is used to visit entity handling conditions and to determine if the workflow is satisfied
 *
 *
 * @param <T>
 */
public class Handle<T> {

	private final HttpResponse response; 
	private final Class<T> returnType;
	private final boolean requiresVoidBodyHandling;
	private T returnObject;

	private boolean complete;
	
	private Handle(HttpResponse response, Class<T> returnType, boolean requiresVoidBodyHandling) {
		this.response = response;
		this.returnType = returnType;
		this.requiresVoidBodyHandling = requiresVoidBodyHandling;
	}
	
	static <T> Handle<T> create(HttpResponse response, Class<T> returnType, boolean requiresVoidBodyHandling) {
		return new Handle<T>(response, returnType, requiresVoidBodyHandling);
	}
	
	/**
	 * Stops handling workflow and sets the resulting return object
	 * 
	 * @param returnObject the return object
	 * @return the handle
	 */
	Handle<T> complete(T returnObject) {
		this.complete = true;
		this.returnObject = returnObject;
		return this;
	}
	
	/**
	 * Handler did not satisfy the conditions it was after so this method lets the workflow know to continue the visiting process
	 * to the next handler
	 * 
	 * @return the handle
	 */
	Handle<T> continueHandling() {
		this.complete = false;
		return this;
	}
	
	public T getReturnObject() {
		return returnObject;
	}

	public boolean isComplete() {
		return complete;
	}
	
	public HttpResponse getResponse() {
		return response;
	}

	public Class<T> getReturnType() {
		return returnType;
	}

	public boolean isRequiresVoidBodyHandling() {
		return requiresVoidBodyHandling;
	}
}
