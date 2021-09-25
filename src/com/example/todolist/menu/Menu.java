package com.example.todolist.menu;

public class Menu {
	public static void displaymenu() {
		System.out.println();
		System.out.println("<ToDoList Management Command Inputs>");
		System.out.println("add - add new item");
		System.out.println("del - delete item");
		System.out.println("edit - update item");
		System.out.println("ls - display all items");
		System.out.println("ls_name_asc - display all items by name");
		System.out.println("ls_name_desc - display all items by name, reversed");
		System.out.println("ls_date - display all items by date");
		System.out.println("ls_date_desc - display all items by date, reversed");
		System.out.println("find - find and display item that which contains a keyword");
		System.out.println("find_cate - find and display all items in searched catgory");
		System.out.println("exit - exit program");
	}
	
	public static void prompt() {
		System.out.print("\nCommand > ");
	}
}
