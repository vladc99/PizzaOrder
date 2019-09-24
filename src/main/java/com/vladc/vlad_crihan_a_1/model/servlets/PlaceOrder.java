package com.vladc.vlad_crihan_a_1.model.servlets;

import com.vladc.vlad_crihan_a_1.model.model.PizzaOrder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Gets the input from the previous page, stores it in a Pizza Object
 * that's sent to the next page(as a request attribute).
 * 
 * @author Vlad Crihan
 * @date Sept 17th, 2019
 */
public class PlaceOrder extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //instantiate a pizza object
        PizzaOrder pizza = new PizzaOrder();
        
        //check for delivery
        if (request.getParameter("pickOrDelivery").equals("delivery")) {
            pizza.setDelivery(true);
        }
        
        //set pizza size
        pizza.setSize(request.getParameter("pizzaSize"));
        
        //set toppings
        // note: it will store the values of toppings even if their not selected
        // if there are no toppings, it will store a NULL
        pizza.setToppings(request.getParameterValues("toppings"));

        //store the pizzaOrder as a request attribute
        request.setAttribute("pizza", pizza);

        //get the reqDispatcher and forward the req to the jsp page
        RequestDispatcher rd = request.getRequestDispatcher("displayOrder.jsp");
        rd.forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>ERROR</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Sorry, GET is NOT supported</h1>");
        out.println("</body>");
        out.println("</html>");
        }
    }
}
