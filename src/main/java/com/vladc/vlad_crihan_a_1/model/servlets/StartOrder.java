package com.vladc.vlad_crihan_a_1.model.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vlad Crihan
 * Start order servlet that checks for user input from the previous page.
 * After the checking is done the servlet displays error if the input is empty
 * or it display the page if the input is correct.
 * The script tag displays an dynamic total that changes depending on the
 * user choice of size,delivery or toppings. It's a temporary value that's not
 * sent to the next page, it's only for display reasons.
 * NOTE: I've left the script indented the way it is so it's easier to read
 * within the program. It's also indented if you inspect the source of the page
 * The servlet also checks if fields are empty,null or with only a space.
 * If that's the case it's going to give you an error page
 * NOTE 2: The tempTotal as the name suggests it's a temporary value that's not
 * affecting the next page.
 * There's also a doPOST method that redirects you to a error page if somehow
 * the page is hacked and it tries post as a method.
 * 
 */
public class StartOrder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //store the variables from the previous page ( Name/phone number)
            String userName = request.getParameter("userName");
            String phoneNumber = request.getParameter("phoneNumber");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");            
            
            out.println("<link rel=\"stylesheet\" "
                    + "type=\"text/css\" href=\"styles.css\"/>");
            
            //checking if userName/phoneNumber are null/empty
            if( userName == null || phoneNumber == null ||
                    userName.equals(" ") || phoneNumber.equals(" ") ||
                    userName.isEmpty() || phoneNumber.isEmpty()){
                out.println("<title>ERROR</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>You forgot your name/phone number BRO</h1>");
                out.println("</body>");
                out.println("</html>");
            }else{
                    
            out.println("<link rel='stylesheet' type='text/css' "
                    + "href='styles.css'/>");
            
            out.println("<title>Place your order</title>");
            out.println("</head>");
            out.println("<body>");

            //printing the variables
            out.println("<h1>Hi " + userName + "</h1>");
            out.println("<h1>" + phoneNumber + "</h1>");
            out.println("<h1 id=tempTotal></h1>");

            //setting destination/method for the information inside the form tag
            out.println("<form action='PlaceOrder.do' method='POST'>");

            //Delivery or pick-up
            out.println("Pick up or Delivery <select name=pickOrDelivery"
                    + " id=pickOrDelivery >"
                    + "<option value='delivery' >Delivery</option>"
                    + "<option value='pickUp' >Pick-Up</option>"
                    + "</select>");

            //keeping the name/phone number in invisible tags so the next page
            //has access to them
            out.println("<input type='hidden' name='userName' value='"
                    + userName + "' >");
            out.println("<input type='hidden' name='phoneNumber' value='"
                    + phoneNumber + "'>");
            
            //radio button for the pizza sizes options
            out.println("<br><br><input type='radio' value='smallPizza' "
                    + "name='pizzaSize' id='pizzaSize' checked>"
                    + "<span>Small($5)</span>"
                    + "<input type='radio' value='mediumPizza'"
                    + "name='pizzaSize' id='pizzaSize'> "
                    + "<span>Medium($7)</span>"
                    + "<input type='radio' value='largePizza'"
                    + " name='pizzaSize' id='pizzaSize'>"
                    + "<span>Large($9)</span>");

            out.println("<br><br>");

            out.println("<h3>Extras (+$1 each)</h3>");
            
            //checkboxes for available toppings
            out.println("<input type='checkbox'name='toppings' "
                    + "value='Pepperoni' id=toppings>Pepperoni");
            out.println("<input type='checkbox' name='toppings' value='Sausage' "
                    + "id=toppings>Sausage");
            out.println("<input type='checkbox' name='toppings' "
                    + "value='Baby Spinach' id=toppings>Baby Spinach");
            out.println("<input type='checkbox' name='toppings' "
                    + "value='Pepper' id=toppings>Pepper"
                    + "<br><br>");
            
            //Submit button
            out.println("<input type='submit' value='Place My Order'>");
            out.println("</form>");
            
            
            out.println("<script>\n"+
"        var toppings = 0;\n" +
"        var size = 5;\n" +
"        var delivery = 2;\n" +
"        updateTotal();\n\n" +
                    
         //function to update the temporary total ( displayed total )
"        function updateTotal(){\n" +
"          var total = toppings + size + delivery;\n" +
"          document.getElementById('tempTotal').innerHTML = "
                    + "`Your Total is $${total}`;\n" +
                    "        }\n\n" +
                    
         //check for delivery and add or substract from price
"        var isDelivery = document.getElementById('pickOrDelivery');\n" +
"        isDelivery.addEventListener('change',function(e){\n" +
"          if(e.target.value == 'delivery'){\n" +
"            delivery = 2;\n" +
"            updateTotal();\n" +
"          }else{\n" +
"            delivery = 0;\n" +
"            updateTotal();\n" +
"          }\n" +
"        });\n\n" +
                    
         //check for extra toppings and add the price
"        var extraToppings = document.querySelectorAll('#toppings');\n\n" +
                    
"        for (var i = 0; i < extraToppings.length; i++) {\n" +
"          extraToppings[i].addEventListener('change',function(e){\n" +
"            if(e.target.checked){\n" +
"              toppings++;\n" +
"              updateTotal();\n" +
"            }else{\n" +
"              toppings--;\n" +
"              updateTotal();\n" +
"            }\n" +
"          })};\n\n" +
                    
         //check for size and update total
"        var pizzaSize = document.querySelectorAll('#pizzaSize');\n" +
"        for (var i = 0; i < pizzaSize.length; i++) {\n" +
"          pizzaSize[i].addEventListener('change',function(e){\n" +
"            if(e.target.value == 'smallPizza'){\n" +
"              size = 5;\n" +
"              updateTotal();\n" +
"            }else if(e.target.value == 'mediumPizza'){\n" +
"              size = 7;\n" +
"              updateTotal();\n" +
"            }else if(e.target.value == 'largePizza'){\n" +
"              size = 9;\n" +
"              updateTotal();\n" +
"            }\n" +
"          })};"
                    + "</script>"
                    + "");
            
            //closing basic HTML tags
            out.println("</body>");
            out.println("</html>");

            }
        }
    }
    //error page for POST method
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ERROR</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Sorry, POST is NOT supported</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
