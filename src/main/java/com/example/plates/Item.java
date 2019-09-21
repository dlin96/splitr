package com.example.plates;

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
		return name + "\t" + price;
	}
}
