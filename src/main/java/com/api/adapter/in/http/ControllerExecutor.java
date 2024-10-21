package com.api.adapter.in.http;
public class ControllerExecutor extends GenericControllerExecutor<String> {
	
	private ControllerExecutor(String title) {
		super(title);
	}
	
	private ControllerExecutor(String title, String objectEncrypted) {
		super(title);
	}
	
	public static ControllerExecutor of(String title) {
		return new ControllerExecutor(title);
	}
	
	public static ControllerExecutor of(String title, String objectEncrypted){
		return new ControllerExecutor(title, objectEncrypted);
	}

}
