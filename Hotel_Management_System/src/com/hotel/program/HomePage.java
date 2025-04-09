package com.hotel.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HomePage {
	//create method for reservations (insert type)
	private static void reserveRoom(Connection con, Scanner sc){
		try {
			System.out.println("Enter Guest Name : ");
			String guest = sc.next();
			sc.nextLine();
			System.out.println("Enter Room Number : ");
			int room = sc.nextInt();
			System.out.println("Enter Contact Number :");
			String contact = sc.next();
			//issue the query
			String query = "INSERT INTO RESERVATIONS(GUEST, ROOM, CONTACT) VALUES('"+guest+"',"+room+",'"+contact+"')";
			Statement stmt = con.createStatement(); //create statement
			int count = stmt.executeUpdate(query); //execute query & returns 1 integer row affected
			if(count>0)
			{
				System.out.println("------Reservation Done Successfully------");
			}
			else {
				System.out.println("----Reservation Fails----Try Again-------");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//create method for checking whether reservations are exist/not
	private static boolean reservationExists(Connection con, int id) {
		try {
			String query = "SELECT ID FROM RESERVATIONS WHERE ID = "+id; //issue the query
			Statement stmt = con.createStatement(); //create statement
			ResultSet rs = stmt.executeQuery(query); //execute query
			return rs.next(); //if there is reservation exist
		}catch(Exception e) {
			e.printStackTrace();
			return false; //handle the database error as needed
		}
	}
	//create method for view reservations
	private static void viewReservations(Connection con)
	{
		String query = "SELECT ID,GUEST,CONTACT,ROOM,DATE FROM RESERVATIONS"; // Issue the query
		//String query = "SELECT * FROM RESERVATIONS;
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query); //execute query & returns entire table records as result
			System.out.println("------------CURRENT RESRVATIONS-----------");
			System.out.println("+--------------+----------------------+-------------------+-----------------+-------------------------------+");
			System.out.println("|    ID        |       GUEST_NAME     |     CONTACT       |     ROOMNO      |             DATE              |");
			System.out.println("+--------------+----------------------+-------------------+-----------------+-------------------------------+");
			while(rs.next())
			{
				int id = rs.getInt("ID");
				String guest = rs.getString("GUEST");
				String contact = rs.getString("CONTACT");
				int room = rs.getInt("ROOM");
				String date = rs.getTimestamp("DATE").toString();
				System.out.printf("|  %-10d  |   %-14s   |  %-13s   |  %-12d      |     %-19s     |\n",id,guest,contact,room,date);
			}
			System.out.println("------------------------------------------------------------------------------------------------------------");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//create method for get the room numbers
	private static void  getRoomNumber(Connection con,Scanner sc)
	{
		try
		{
			System.out.println("Enter Reservation id :");
			int id = sc.nextInt();
			System.out.println("Enter Guest Name : ");
			String guest  = sc.next();
			String query = "SELECT ROOM FROM RESERVATIONS WHERE ID = "+id+" AND GUEST = '"+guest+"' ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				int room = rs.getInt("ROOM");
				System.out.println("RoomNo : "+room);
				System.out.println(" Room Number for above given Id : "+id+" &  guestname  : "+guest);
			}
			else
			{
				System.out.println("--------------RESERVATION NOT FOUND!!!!------------");
				System.out.println(" Reservations does not exist for given id : "+id+" &  guestname  : "+guest);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//create method for update reservations
	private static void updateReservations(Connection con ,Scanner sc)
	{
		try
		{
			System.out.println("Enter Reservation Id to Update : ");
			int id =  sc.nextInt();
			sc.nextLine();
			if(!reservationExists(con,id))
			{
				System.out.println("--------RESERVATION NOT FOUND FOR GIVEN ID ---------");
				return;
			}
			System.out.println("Enter New name for Guest : ");
			String guest = sc.nextLine();
			System.out.println("Enter new Contact Number of an Guest :");
			String contact = sc.next();
			System.out.println("Enter New RoomNo for Guest :");
			int room = sc.nextInt();
			String query = "UPDATE RESERVATIONS SET GUEST = '"+guest+"', ROOM = "+room+", CONTACT = '"+contact+"' WHERE ID = "+id;
			Statement stmt = con.createStatement(); //issue the query
			int count = stmt.executeUpdate(query); //execute query & return 1 row affected
			if(count>0)
			{
				System.out.println("-----RESERVATION UPDATED -----");
			}
			else
			{
				System.out.println("------RESERVATIONS UPDATED FAIL------");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//create method for delete reservations
	private static void deleteReservations(Connection con,Scanner sc)
	{
		try
		{
			System.out.println("Enter Reservation Id to Delete : ");
			int id = sc.nextInt();
			if(!reservationExists(con,id))
			{
				System.out.println("--------Reservation Not Found--------");
				return;
			}
			String query = "DELETE FROM RESERVATIONS WHERE ID = "+id;
			Statement stmt = con.createStatement();
			int count= stmt.executeUpdate(query);
			if(count>0)
			{
				System.out.println("-----RESERVATION DELETED -----");
			}
			else
			{
				System.out.println("------RESERVATIONS DELETE FAIL------");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//create method for exiting the reservation
	public static void exit() throws InterruptedException {
		System.out.println("--------Exiting the System----------");
		int i=5;
		while(i!=0)
		{
			System.out.print(",");
			Thread.sleep(3000); //will pause the execution of, for 3 seconds every time until condition becomes false
			i--;
		}
		System.out.println(); //empty line
		System.out.println("-------Thank You For Using Hotel Reservation System ---------");
	}
	
	private static final String dburl = "jdbc:mysql://localhost:3306/hotel_db";
	private static final String user = "root";
	private static final String password = "root"; 
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //loading driver
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(dburl,user,password);  //Establish Connection
			while(true)
			{
				System.out.println(); //new line
				System.out.println("	Welcome  ");
				System.out.println("   Hotel Reservation System   ");
				Scanner sc = new Scanner(System.in);
				System.out.println("1. RESERVE A ROOM ");
				System.out.println("2. VIEW RESERVATIONS ");
				System.out.println("3. GET A ROOM NUMBER ");
				System.out.println("4. UPDATE RESERVATIONS ");
				System.out.println("5. DELETE RESERVATIONS ");
				System.out.println("0. EXIT ");
				System.out.println(" CHOOSE ANY OPTION ");
				int choice = sc.nextInt();
				switch(choice) {
				case 1: 
					reserveRoom(con, sc);
					break;
				case 2: 
					viewReservations(con);
					break;
				case 3:
					getRoomNumber(con, sc);
					break;
				case 4:
					updateReservations(con, sc);
					break;
				case 5: 
					deleteReservations(con, sc);
					break;
				case 0:
					exit(); //here it will throw interrupted exception so it recommended to handle
					sc.close();
					return;
				default:
					System.out.println("Invalid choice ...... try again!!!!!");
				}
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(InterruptedException e) {
			throw new RuntimeException();
		}
	}
}
