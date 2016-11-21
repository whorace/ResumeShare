package edu.brandeis.cs.jiahuiming.resumeshare.utils;

import java.io.File;

public class FileUtil {
	private File mFile;
	private String mPath;
	
	public FileUtil(String path)
	{
		this.mFile= new File(path); 
		this.mPath=path;
	}
	
	public String getFileSize()
	{ 	
		if (mFile.exists() && mFile.isFile())
		{ 
			return mFile.length()/1024+"KB";}
		else
		{ return "Unable to get the size of file";}
	}
	
	public String getFileType()
	{
		if(mFile.exists() && mFile.isFile())
		{
			
			String [] suffix=mPath.split("\\.");
			return suffix[suffix.length-1];
		}
		else
		{return "Unable to get the type of file";}
	}
	
	public boolean isExist() {
		if (!mFile.exists()) 
		{
			if (mFile.mkdirs())
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		return true;
	}
}
