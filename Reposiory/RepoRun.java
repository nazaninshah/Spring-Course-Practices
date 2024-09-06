package ir.freeland.springboot.practice.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ir.freeland.springboot.practice.Item;
import ir.freeland.springboot.practice.corruptedItem;

@Component
public class RepoRun {
	@Autowired
	ItemRepo itemRepo;
	@Autowired
	CorruptedRepo corruptedRepo;
	
	public void run() {
		var allItems = itemRepo.findAll();
		System.out.println(allItems);
		
		var item1 = itemRepo.findByNameEndingWith("item1");
		System.out.println(item1);
		
		var item2 = itemRepo.findByNameIgnoreCase("ITEMnew");
		System.out.println(item1);
		
		var allCItems = corruptedRepo.findByReasonIgnoreCase("exPiRed");
		System.out.println(allCItems);
	}
	
}
