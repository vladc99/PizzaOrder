## Pizza Order using Servlets and JSP ##
http://vladc-pizzaorder.herokuapp.com/

A simple Java Enterprise application that use Servlets and JSP files to process and display orders.
In this project the user will be promted to input his Name and his Phone number followed by other inputs like extra toppings,size of pizza
or if it's a delivery or a pick-up. The program will instantiate a PizzaOrder object that stores the size,toppings and if it's deliverable
or not. The getPrice will calculate the price depending on the choices. The program will do error checking with patterns, required and if
statements. There's other error checking like if for example somehow if submits with POST instead of GET the app will redirect you
to an error page. if you're in the second screen and you delete the name/phone number from the URL the page will redirect you to an error
page.

NOTE: The JavaScript total is just a temporary total that doesn't affect the total in any way.
