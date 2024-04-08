# Shopping List Tracker

## General Information

This **Shopping List Tracker** will be able to log prices, discount codes, links to online shopping sites, and other
additional information for products, in order to make tracking what you need to buy easier and more intuitive. This
application can be used by anyone, and will be a great way to ensure that you remember what you need to buy, while also
tracking expenses and finding the best discounts. I personally chose to work on this project due to my own need for an
easy-to-use shopping list application that also had a lot of useful functions, and I hope that this will be beneficial
to both me and others who use this.

## User Stories

- As a user, I want to be able to add a product to buy to the shopping list
- As a user, I want to be able to add multiple products to buy to the shopping list
- As a user, I want to be able to view the shopping list and see all the products I've added
- As a user, I want to be able to input prices, discount codes, and links for a specific product
- As a user, I want to be able to select a product in the shopping list and view its details 
- As a user, I want to be able to save my shopping list to file, if I choose to do so
- As a user, I want to be able to be able to load my shopping list from file, if I choose to do so

## Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking "Add 
  Product"
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking "Most
  Expensive" or "Least Expensive" 
- You can locate my visual component by clicking "Create Shopping List" or viewing any other message pop-up
- You can save the state of my application by clicking "Save Shopping List"
- You can reload the state of my application by clicking "Load Shopping List"

## Phase 4: Task 2
Sun Apr 07 13:39:45 PDT 2024 

Shopping list created.

Sun Apr 07 13:39:58 PDT 2024

Product added.

Sun Apr 07 13:40:03 PDT 2024

Product added.

Sun Apr 07 13:40:09 PDT 2024

Product added.

Sun Apr 07 13:40:12 PDT 2024

Product with lowest price found.

Sun Apr 07 13:40:14 PDT 2024

Product with highest price found.

## Phase 4: Task 3
If I were to refactor my code to improve the design, I would likely try to add an abstract class in the ui folder that
that encompasses both the console and GUI Shopping List Application, so that there would be reduced duplication between
those two classes. For instance, both of those classes has similar functionality, such as adding a product to a shopping
list. I could refactor these such common methods, and ensure that some of the code is not redundant.