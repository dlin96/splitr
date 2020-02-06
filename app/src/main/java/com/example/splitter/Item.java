package com.example.splitter;

import static java.lang.String.format;

class Item {
	double price;
	String name;

	Item(double price, String name) {
		this.name = name;
		this.price = price;
	}

	public boolean equals(Item item) {
		if (item.price == price && item.name == name) return true;
		return false;
	}

	@Override
	public String toString() {
		String itemprice = String.format(java.util.Locale.US, "%.2f", price);
		return name + "\t" + itemprice;
	}
}
