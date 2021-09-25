package com.example.todolist;

import java.util.Scanner;
import java.util.StringTokenizer;

import com.example.todolist.dao.TodoList;
import com.example.todolist.menu.Menu;
import com.example.todolist.service.TodoUtil;

public class TodoMain {

	public static void start() {
		Scanner s = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;		//flag var
		boolean quit = false;		//flag var
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String command = s.nextLine();
			String choice = command.split(" ")[0];
			String keyword = command.split(" ")[1];
			
			switch (choice) {
			
			case "add" :
				TodoUtil.createItem(l);
				break;
				
			case "del" :
				TodoUtil.deleteItem(l);
				break;
				
			case "edit" :
				TodoUtil.updateItem(l);
				break;
				
			case "ls" :
				TodoUtil.listAll(l);
				break;
				
			case "ls_name_asc" :
				l.sortByName();
				System.out.println("Sorted by title!");
				isList = true;
				break;
				
			case "ls_name_desc" :
				l.sortByName();
				l.reverseList();
				System.out.println("Sorted by title, reversed!");
				isList = true;
				break;
				
			case "ls_date" :
				l.sortByDate();
				System.out.println("Sorted by date!");
				isList = true;
				break;
				
			case "ls_date_desc" :
				l.sortByDate();
				l.reverseList();
				System.out.println("Sorted by date, reversed!");
				isList = true;
				break;
				
			case "help" :
				Menu.displaymenu();
				break;
				
			case "find" :
				TodoUtil.findKeyword(l, keyword);
				break;
				
			case "find_cate" :
				TodoUtil.findKeywordCate(l, keyword);
				break;
				
			case "exit" :
				quit = true;
				break;
				
			default : 
				System.out.println("Please enter the correct commmand (\"help\" for list of commands)");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
