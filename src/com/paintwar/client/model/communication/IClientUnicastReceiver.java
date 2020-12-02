package com.paintwar.client.model.communication;

/**
 * An implementation of IClientUnicastReceiver must link each method with an
 * action on the local client
 * 
 * @author jkerl
 *
 */
public interface IClientUnicastReceiver
{
	// example
	//
	// public void aMethod(String name, TypeA argumentA, TypeB argumentB);
	//
	// implementation should look like this :
	// public void aMethod(String name, TypeA argumentA, TypeB argumentB)
	// {
	// gameSystem.get(name).aMethod(TypeA argumentA, TypeB argumentB);
	// }
}
