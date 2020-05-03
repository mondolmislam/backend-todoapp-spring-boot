package com.proit.todoapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proit.todoapp.domains.ToDoItem;
import com.proit.todoapp.services.ToDoItemService;

@RestController
@RequestMapping(value="/api/todo")
public class ToDoItemController {
	
	@Autowired
	private ToDoItemService toDoItemService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ToDoItem>> getItems(){
		List<ToDoItem> items=toDoItemService.getAll();
		if(items==null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<List<ToDoItem>> getItem(@PathVariable("id")Long id){
		ToDoItem item=toDoItemService.findById(id);
		if(item==null) {
			return ResponseEntity.notFound().build();
		}
		List<ToDoItem>response=new ArrayList<>();
		response.add(item);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<List<ToDoItem>> create(@RequestBody ToDoItem toDoItem){
		ToDoItem insertedItem=toDoItemService.createOrUpdate(toDoItem);
		if(insertedItem==null) {
			return ResponseEntity.notFound().build();
		}
		List<ToDoItem>response=new ArrayList<>();
		response.add(insertedItem);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<List<ToDoItem>>update(@PathVariable("id")Long id,@RequestBody ToDoItem toDoItem){
		if(id==null) {
			return ResponseEntity.noContent().build();
		}		
		ToDoItem item=toDoItemService.findById(id);
		item.setName(toDoItem.getName());
		item.setDescription(toDoItem.getDescription());
		ToDoItem updatedItem=toDoItemService.createOrUpdate(item);
		if(updatedItem==null) {
			return ResponseEntity.notFound().build();
		}
		List<ToDoItem>response=new ArrayList<>();
		response.add(item);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
