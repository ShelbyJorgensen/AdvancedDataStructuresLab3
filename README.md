# AdvancedDataStructuresLab3
This project was created for my CS302 Advanced Data Structures class at Central Washington University. In this lab, we were asked to create a phone book with a working GUI. To complete this, I used the built in GUI builder that is part of the Eclipse IDE. Below is the provided outline for this project:

**Description**
Write a program that maintains the names, addresses, and phone numbers of your friends and relatives
and thus serves as an address book. You should be able to enter, delete, modify, or search this data. The
person's name should be the search key, and we can assume that the names are unique. The program should
be able to save the address book in a file for the use later. When tested, a new empty address book is going to
be created in the beginning.

**Implementation**
Design a class named Person.java to represent people in the address book and another class
named PhoneBookDriver.java to represent the address book itself. The phone book driver should contain a
binary search tree of people as a data member. An implementation of a binary
search tree (BinarySearchTree.java) can be found on Canvas in the lab directory. To save/load the phone
book, you will need to use the ObjectOutputStream with FileOutputStream and make sure your
classes implement the Serializable interface.
