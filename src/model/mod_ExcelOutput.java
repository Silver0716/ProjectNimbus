package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mod_ExcelOutput 
{
	public static ArrayList<List<String>> ExcelOutputTable = new ArrayList<>();
	public final String CollaborateUser = "Collaborate User";
	public final String CommunicateUser = "Communicate User";
	public final String PureCloudUser = "PureCloud User";
	public final String AdministratorUser = "Administrator User";
	public final String PureCloudCCManager = "PureCloud CC Manager";
	public final String PureCloudDeveloper = "PureCloud Developer";
	public final String Config = "Configuration";
	
	
	public void setOutputValues(String key, String testObj, String status, String remarks)
	{
		ExcelOutputTable.add(Arrays.asList(key, testObj, status, remarks));
	}
	
	public ArrayList<List<String>> getOutputValues(String key, String value, String remarks)
	{
		return ExcelOutputTable;
	}
}
