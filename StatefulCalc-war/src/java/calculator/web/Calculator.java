/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.web;


import calculator.ejb.CalculatorBeanLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
@WebServlet(name = "Calculator", urlPatterns = {"/Calculator"})
public class Calculator extends HttpServlet {

    CalculatorBeanLocal calculatorBean = lookupCalculatorBeanLocal();
    
    String [] param         = {"satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan", "nol", "nolnol", "dot"};
    String [] paramVal      = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "00", "."};
    String valInput         = null;
    String lastOperation    = null;
    String operation        = null;
    String history          = null;
    
    double results          = 0;
    double value            = 0;
   
    @SuppressWarnings("UnusedAssignment")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         boolean Calculate = true;
        
        for (int i = 0; i < this.param.length; i++) {
            if(request.getParameter(this.param[i]) != null){
                this.valInput = calculatorBean.isNumber(this.valInput, this.paramVal[i]);
                Calculate = false;
            }
        }
        
        if(request.getParameter("delete") != null){
            valInput = calculatorBean.delete(valInput);
            Calculate = false;
        }
        if(request.getParameter("reset") != null){
            results = calculatorBean.reset();
            history = calculatorBean.History();
            request.setAttribute("operation", "");
            lastOperation = null;
            operation = "";
            Calculate = false;
            valInput = null;
        }
        if(request.getParameter("equ") != null){
            value = Double.parseDouble(valInput);
            results =  calculatorBean.equal(results, value, lastOperation);
            history = calculatorBean.History(history, lastOperation, valInput, Double.toString(results));
            operation = "=";
            lastOperation = null;
            Calculate = false; 
            valInput = null;
        }
        
        if(Calculate){
            if (valInput != null) {
                value = Double.parseDouble(valInput);
                
                if(null == lastOperation) {
                    results = calculatorBean.add(0, value);
                    history = calculatorBean.History(valInput); 
                } else {
                    switch (lastOperation) {
                        case "add":
                            results = calculatorBean.add(results, value);
                            break;
                        case "sub":
                            results = calculatorBean.subtract(results, value);
                            break;
                        case "div":
                            results = calculatorBean.divide(results, value);
                            break;
                        case "mul":
                            results = calculatorBean.multiply(results, value);
                            break;
                    }
                    history = calculatorBean.History(history, lastOperation, valInput);
                }
                valInput = null;
            }
        }
        if(request.getParameter("add") != null){
            lastOperation = "add";
            operation = "+";
        }
        if(request.getParameter("sub") != null){
            lastOperation = "sub";
            operation = "-";
        }
        if(request.getParameter("div") != null){
            lastOperation = "div";
            operation = "/";
        }
        if(request.getParameter("mul") != null){
            lastOperation = "mul";
            operation = "x";
        }
        
        request.setAttribute("history", history);
        request.setAttribute("operation", operation);
        request.setAttribute("results", results);
        request.setAttribute("value", valInput);
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Calculator.jsp");
        rd.forward(request, response);
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private CalculatorBeanLocal lookupCalculatorBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CalculatorBeanLocal) c.lookup("java:global/StatefulCalc/StatefulCalc-ejb/CalculatorBean!calculator.ejb.CalculatorBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    

    

}

