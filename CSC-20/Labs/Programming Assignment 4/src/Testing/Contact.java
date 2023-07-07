package Testing;

public class Contact {
	private String fName;
	private String lName;
	private String phNum;
	private String emailAdd;

	public Contact(String fn, String ln, String pn, String e)
	{
		fName = fn;
		lName = ln;
		phNum = pn;
		emailAdd = e;
	}
	public void setfName(String newFirst)
	{
		fName = newFirst;
	}
	public void setlName(String newLast)
	{
		lName = newLast;
	}
	public void setphNum(String newNumber)
	{
		phNum = newNumber;
	}
	public void setemailAdd(String newemailAdd)
	{
		emailAdd = newemailAdd;
	}
	public String getfName()
	{
		return fName;
	}
	public String getlName()
	{
		return lName;
	}
	public String getphNum()
	{
		return phNum;
	}
	public String getemailAdd()
	{
		return emailAdd;
	}
}
