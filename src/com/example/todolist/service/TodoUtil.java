package com.example.todolist.service;

import java.util.*;
import com.example.todolist.dao.TodoItem;
import com.example.todolist.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner s= new Scanner(System.in);
		
		System.out.print("[Add Item]\n" + "Enter title > ");
		
		title = s.next();
		if(list.isDuplicate(title)) {
			System.out.println("This item already exists!");
			return;
		}
		s.nextLine();
		System.out.print("Enter description > ");
		desc = s.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("New item has been added!");
	}
	
	public static void deleteItem(TodoList list) { 
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("[Delete Item]\n" + "Enter title of item to be removed > ");
		String title = s.next();
		
		for(TodoItem item : list.getList()) {
			if(title.equals(item.getTitle())) {
				list.deleteItem(item);
				System.out.println("The item has been deleted!");
				break;
			}
		}
	}
	
	public static void updateItem(TodoList list) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("[Update Item]\n" + "Enter title of item to be updated > ");
		String title = s.next().trim();
		if(!list.isDuplicate(title)) {
			System.out.println("There is no such item!");
			return;
		}
		
		System.out.print("Enter new title > ");
		String new_title = s.next().trim();
		if(list.isDuplicate(new_title)) {
			System.out.println("This item already exists!");
			return;
		}
		s.nextLine();
		System.out.print("Enter new description > ");
		String new_description = s.nextLine().trim();
		for(TodoItem item : list.getList()) {
			if(item.getTitle().equals(title)) {
				list.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				list.addItem(t);
				System.out.println("The item has been updated!");
			}
		}
	}
	
	public static void listAll(TodoList list) {
		System.out.println("[Item List]");
		for (TodoItem item : list.getList()) {
			System.out.println(item.toString());
		}
	}
}
