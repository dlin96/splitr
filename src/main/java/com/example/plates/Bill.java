package com.example.plates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Bill {
    ArrayList<Item> items;
    HashMap<String, Person> people;
    double subtotal;
    double tip;

    Bill(ArrayList<String> people) {
        this.items = new ArrayList<>();
        this.people = new HashMap<>();

        for (String person : people) {
            Person p = new Person(person);
            this.addPerson(p);
        }
    }

    Bill(ArrayList<String> people, double subtotal, double tip) {
        this.subtotal = subtotal;
        this.tip = tip;

        this.items = new ArrayList<>();
        this.people = new HashMap<>();

        for (String person : people) {
            Person p = new Person(person);
            this.addPerson(p);
        }
    }

    void addItem(Item item, String name) {
        items.add(item);
        Person p = people.get(name);
        p.addItem(item);

        double remaining = subtotal - item.price;
        if (remaining >= 0) {
            subtotal = remaining;
        } else {
            throw new Error();
        }
    }

    void addPerson(Person p) {
        people.put(p.name, p);
    }

    void addSharedItems(Item item, ArrayList<String> sharees) {
        double split = item.price / sharees.size();

        for (String name : sharees) {
            Person p = people.get(name);
            p.addItem(new Item(split, item.name));
        }

    }

    @Override
    public String toString() {
        StringBuilder billString = new StringBuilder();
       for (Map.Entry<String, Person> p : people.entrySet()) {
           billString.append(p.getKey() + "\n");
           billString.append(p.getValue().toString() + "\n");
       }

       return billString.toString();
    }
}