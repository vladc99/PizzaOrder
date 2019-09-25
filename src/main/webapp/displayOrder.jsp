<%-- 
    Document   : displayOrder
    Created on : 17-Sep-2019, 3:37:18 PM
    Author     : Vlad Crihan
--%>
<%@page import="java.util.Arrays"%>
<%@page import=" com.vladc.vlad_crihan_a_1.model.model.PizzaOrder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details</title>
    </head>
    <body>
        <link rel="stylesheet" type="text/css" href="styles.css"/>
        <!-- get the pizza object -->
        <% PizzaOrder pizza = (PizzaOrder) request.getAttribute("pizza");
        %>
        <h1>Pizza Order for <% out.print(request.getParameter("userName")); %> , 
            <% out.print(request.getParameter("phoneNumber")); %> total $
            <% out.print(pizza.getPrice()); %>
        </h1>

        <!-- check for delivery and print the corresponding message -->
        <% boolean delivery = pizza.isDelivery();
            String message = "Your Pizza will be ready for pickup in 20 minutes";
            if (delivery) {
                message = "Your pizza will be delivered within 40 minutes";
            };%>
        <h2> <% out.print(message); %> </h2>
        
        <br>
        
        <!-- Print the size -->
        <h2><% out.print(pizza.getSize()); %> pizza with </h2>

        <br>

    <!-- Placing the toppings in an Array and print values that are not null -->
        <% String[] temp = pizza.getToppings();
        
        //first check if the Array is empty
            if (!Arrays.toString(temp).equals("null")) {
                for (int i = 0; i < temp.length; i++) {%>
        <% if (!temp[i].equals("null")) { %>
        <ul> <!-- Print the items in an non-ordered list where is applicable -->
            <li><h2><% out.print(temp[i]); %></h2></li>
        </ul> <% }
                };

            //if the Array is empty
            } else { %>
        <h3>No Toppings</h3><% };%>
        
    </body>
</html>
