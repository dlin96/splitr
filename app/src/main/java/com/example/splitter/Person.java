package com.example.splitter;

import java.util.ArrayList;

class Person {
	String name;
	private ArrayList<Item> items;
	private double amountOwed;

	Person(String name) {
		this.name = name;
		this.items = new ArrayList<>();
		this.amountOwed = 0;
	}

	void addItem(Item item) {
		items.add(item);
		amountOwed += item.price;
	}

	@Override
	public String toString() {
		StringBuilder bill = new StringBuilder();
		bill.append(name).append("\n");
		for (Item item : items) {
			bill.append(item.toString()).append("\n");
		}
		bill.append("Total Amount Owed: ").append(String.format(java.util.Locale.US, "%.2f\n", amountOwed)
		).append("\n");
		return bill.toString();
	}
}
