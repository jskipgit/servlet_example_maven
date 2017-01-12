package com.ironyard.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

/**
 * Created by jasonskipper on 1/12/17.
 */
@WebServlet(name = "MyAgeServlet", urlPatterns = "/helloworld")
public class MyAgeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String yearBornAsString = request.getParameter("yearPersonWasBorn");

        int year = Integer.parseInt(yearBornAsString);
        int ageOfPerson = calculateAge(year);
        request.setAttribute("age", ageOfPerson);

        // forward to next jsp
        String nextJSP = "/age.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private int calculateAge(int yearPersonWasBorn){
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(yearPersonWasBorn, Month.JANUARY, 1);

        Period p = Period.between(birthday, today);
        // calculate age
        return p.getYears();
    }
}
