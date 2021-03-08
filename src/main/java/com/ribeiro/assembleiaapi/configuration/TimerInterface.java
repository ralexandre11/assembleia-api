package com.ribeiro.assembleiaapi.configuration;

public interface TimerInterface {

	public final long SECOND = 1000; 
	
	public final long MINUTE = SECOND * 60; 

	public void execute();

}
