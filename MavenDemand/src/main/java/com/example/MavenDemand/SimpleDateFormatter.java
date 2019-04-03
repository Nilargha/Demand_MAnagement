/**
 * 
 */
package com.example.MavenDemand;

import java.util.Date;

import java.text.SimpleDateFormat;

/**
 * @author nilargha.ghosh
 *
 */
public class SimpleDateFormatter {
	String date;
	public SimpleDateFormatter()
	{
		//this.date=date;  
		}
	
	public String format()
	{
		
		Date date1 = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String strDate = formatter.format(date1);  
		return strDate;
		
	}

}
