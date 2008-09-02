package org.openborges.memex.ubus.file;


import java.io.File;

import org.openborges.memex.universe.Universe;

public interface IFileUniverse extends Universe {

	/**
	 * @return
	 */
	public File getFile();
	
	/**
	 * 
	 */
	public void save();
	
}
