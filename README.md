# Plates Design Document

## Purpose

This application is intended to be an android application that helps groups of people split a bill evenly by itemizing each receipt item and assigning it to a person and displaying what each person owes in the end. This came from a need I noticed whenever my friends and I would go out to eat and would need to split the bill. I found the Plates app on iPhone to be limited (as most of my friends have Android devices) and also could be quite confusing. Although it is designed beautifully, it seems to lack clarity (for me, at least) and I believe a more straightforward application that specifically assigns items to named entities (i.e. using people's names instead of arbitrary plate assignments), would make the process much smoother. 



## Flow

The flow should be:

1. Enter names, subtotal, and tax (tip percentage optional)

2. Enter item name, price, and assign it people who need to pay for it

3. The list of names are expandable. As the user adds items to the bill, the selected persons' lists are updated. Once pressed upon, the name item should expand into an itemized list of what they need to pay for (including items they shared). 



## Design Choices

Since there is no need to connect to the internet or a need to store any information, this application will solely be client-based: there won't be a need for a server or database. 

The objects I chose to go with in this are Bill, Person, and Item. 

A Bill object represents a real-world bill that needs to be split (i.e. the whole reason why anyone would use this application). This object would store the subtotal, tip percentage, people, and all the Item objects entered into the bill.

A Person object represents someone who needs to be taken into account while splitting the bill (i.e. a participant in the bill). Each person has a name, a list of Item objects specific to them that they need to pay for, and the total amount owed.

An Item object represents one item on the total bill. This really is the whole point of the application as this would be the abstraction that allows the user to itemize the bill. Each item is represented by an item name (or description) and a price. 