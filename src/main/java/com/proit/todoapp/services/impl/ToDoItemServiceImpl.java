package com.proit.todoapp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proit.todoapp.domains.ToDoItem;
import com.proit.todoapp.repositories.ToDoItemRepository;
import com.proit.todoapp.services.ToDoItemService;

@Service
public class ToDoItemServiceImpl implements ToDoItemService{
	
	@Autowired
	private ToDoItemRepository toDoItemRepo;

	@Override
	public ToDoItem findById(Long id) {
		return toDoItemRepo.findById(id).get();
	}

	@Override
	public List<ToDoItem> getAll() {
		return (List<ToDoItem>) toDoItemRepo.findAll();
	}

	@Override
	public ToDoItem createOrUpdate(ToDoItem toDo) {
		return toDoItemRepo.save(toDo);
	}

	@Override
	public ToDoItem delete(ToDoItem toDo) {
		ToDoItem item=toDoItemRepo.findById(toDo.getId()).get();
		if(item!=null) {
			toDoItemRepo.delete(toDo);
		}
		return item;
	}
}
