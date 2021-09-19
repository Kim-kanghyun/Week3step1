package com.todo.service;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;


import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[�׸� �߰�]\n"
				+ "���� > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}
		sc.nextLine();
		System.out.print("���� > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("�߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("[�׸� ����]\n"
				+ "������ �׸��� ������ �Է��Ͻÿ� > ");
		String title = sc.next();
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("�����Ǿ����ϴ�.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("[�׸� ����]\n"
				+ "������ �׸��� ������ �Է��Ͻÿ� > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("���� �����Դϴ�!");
			return;
		}

		System.out.println("�� ���� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}
		sc.nextLine();
		System.out.println("�� ���� > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�����Ǿ����ϴ�.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void saveList(TodoList l,String filename) {
		for(TodoItem myitem : l.getList()) {
			try(FileWriter fo= new FileWriter(filename,true)){
				fo.write(myitem.toSaveString());
			}
			catch(Exception e) {
				System.out.println("����ó��");
			}
		}
		
	}
	public static void loadList(TodoList l,String filename) throws IOException {
		BufferedReader reader = new BufferedReader(
				new FileReader(filename));
		String str;
		while ((str = reader.readLine()) != null) {
			System.out.println(str);
			StringTokenizer st=new StringTokenizer(str,"##");
			String title, desc;
			title = st.nextToken();
			desc = st.nextToken();
			TodoItem t = new TodoItem(title, desc);
			l.addItem(t);
			}
			reader.close();
	}
	
    
	
}
