package com.example.todolist.service;

import java.util.*;
import com.example.todolist.dao.TodoItem;
import com.example.todolist.dao.TodoList;

import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.io.FileWriter;
import java.io.Writer;

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
	
	
	// File I/O >> save and load in "todolist.txt"
	public static void loadList(TodoList list, String filename) {	// use BufferedReader, FileReader, StringTokenizer
		try {
			BufferedReader r = new BufferedReader(new FileReader(filename));
			String oneline;
			int counter=0;
			while((oneline = r.readLine()) != null) {
				counter++;
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				TodoItem t = new TodoItem(title, desc);
				t.setCurrent_date(date);
				list.addItem(t);
								
			}
			System.out.println("Number of loaded items: " + counter);
			r.close();
		} catch (FileNotFoundException e) {
			System.out.println("There is no file >> " + filename);
		} catch (IOException e) {
			System.out.println("There is no file >> " + filename);
		}
	}
	
	public static void saveList(TodoList list, String filename) {	// use FileWriter 
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : list.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("All items have been saved!");
		} catch(FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
}
