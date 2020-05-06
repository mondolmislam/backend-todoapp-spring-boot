package com.proit.todoapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proit.todoapp.domains.ToDoItem;
import com.proit.todoapp.domains.ToDoItemResponse;
import com.proit.todoapp.services.ToDoItemService;

@RestController
@RequestMapping(value="/api/todo")
public class ToDoItemController {
	
	@Autowired
	private ToDoItemService toDoItemService;
	
	@GetMapping
	public ResponseEntity<ToDoItemResponse> getItems(){
		List<ToDoItem> items=toDoItemService.getAll();
		if(items==null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(new ToDoItemResponse(items), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<ToDoItem> getItem(@PathVariable("id")Long id){
		ToDoItem item=toDoItemService.findById(id);
		if(item==null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ToDoItem> create(@RequestBody ToDoItem toDoItem){
		ToDoItem item=toDoItemService.createOrUpdate(toDoItem);
		if(item==null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(item, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<ToDoItem>update(@PathVariable("id")Long id,@RequestBody ToDoItem toDoItem){
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
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
}
