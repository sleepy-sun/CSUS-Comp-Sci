package Testing;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactsTest {
	public static void main(String args[])
	{

		ArrayList<Contact> contacts = new ArrayList<Contact>();
		Scanner in = new Scanner(System.in);
		while(true)
		{

			
			System.out.println("1)Add a new contact");
			System.out.println("2)Display all contacts");
			System.out.println("3)Display specific contact");
			System.out.println("4)Delete a contact");
			System.out.println("-1) Quit");
			System.out.print("Enter Your choice: ");

					int choice = in.nextInt();
					in.nextLine();
					switch(choice)
					{

					case 1:
						System.out.print("Enter first name: ");
						String fn = in.nextLine();
						System.out.print("Enter last name: ");
						String ln = in.nextLine();
				System.out.print("Enter phone number: ");
						String pn = in.nextLine();
						System.out.print("Enter Email address: ");
						String ea = in.nextLine();
						contacts.add(new Contact(fn, ln, pn, ea));
						break;
					case 2:
						for(int i = 0; i < contacts.size(); i++)
						{
							Contact c = contacts.get(i);
					System.out.print(c.getlName() + "  " + c.getfName() + "; ");
					System.out.print("Phone number: " + c.getphNum());
					System.out.print("; Email: " + 	c.getemailAdd());
							System.out.println();
						}
						break;
					case 3:
						System.out.println("Enter an information piece about person:");
						String info = in.nextLine();
						Contact c = findPerson(contacts, info);	
						if(c != null)
						{
				System.out.print(c.getlName() + ", " + c.getfName() + "; ");
				System.out.print("Phone number: " + c.getphNum());
				System.out.print("; Email: " + c.getemailAdd());
							System.out.println();
						}
						else
							System.out.println("Contact not found.");

						break;

					case 4:
						System.out.println("Enter an information piece about person:");
									String someInfo = in.nextLine();
						Contact contact = 	findPerson(contacts, someInfo);
						if(contact != null)
						{
			System.out.print(contact.getlName() + ", " + contact.getfName() + "; 				");
					System.out.print("Phone number: " + 	contact.getphNum());
					System.out.print("; Email: " + 		contact.getemailAdd());
							System.out.println();
					System.out.println("Are you sure to delete contact? (Y/N)");
									String delete = in.nextLine();
									if(delete.equalsIgnoreCase("y"))
									{
										contacts.remove(contact);
							System.out.println("Contact deleted successfully.");
									}
						}
						else
							System.out.println("Contact not found.");

						break;
					}

					if(choice == -1)
						break;

					System.out.println("___________________________________________");
		}

	}

	private static Contact findPerson(ArrayList<Contact> contacts, String info)
	{
		Contact c = null;
		for(int i = 0; i < contacts.size(); i++)
		{
			if(contacts.get(i).getfName().
					indexOf(info) != -1)
				c = contacts.get(i);

			if(contacts.get(i).getlName().
					indexOf(info) != -1)
				c = contacts.get(i);

			if(contacts.get(i).getphNum().
					indexOf(info) != -1)
				c = contacts.get(i);

			if(contacts.get(i).getemailAdd().
					indexOf(info) != -1)
				c = contacts.get(i);
		}
		return c;
	}
}

