package com.example.demo6;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.lang.String;


public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
      /*  String str = files[0].getName();
        System.out.println(str.substring(0,str.lastIndexOf(".")));*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File dir = new File("C:\\Users\\User\\IdeaProjects\\demo6\\src\\main\\txt");
        File[] filesdir = dir.listFiles();
        int i;
        int j = 0;
        String[][] strdin = new String[filesdir.length][1000];
        for (i = 0; i < filesdir.length; i++) {
            strdin[i][0] = filesdir[i].getName();
            File[] files = filesdir[i].listFiles();
            for (j = 0; j < files.length; j++) {
                strdin[i][j + 1] = files[j].getName();
            }
        }
        String[][] bebra = new String[i][j+1];
        for(i=0;i<filesdir.length;i++){
            for(j=0;j<strdin[i].length;j++){
                if(strdin[i][j]!=null){
                    bebra[i][j]=strdin[i][j];
                }else{
                    break;
                }
            }
        }
        req.setAttribute("strdir", bebra);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* Collection<Part> parts = req.getParts();
        Part part = req.getPart("photo");
        part.write(UUID.randomUUID().toString())*/
        String name = req.getParameter("namee");
        String Newname=name.replace('_','\\');
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        File file = new File("C:\\Users\\User\\IdeaProjects\\demo6\\src\\main\\txt"+Newname);
        String fileName = "C:\\Users\\User\\IdeaProjects\\demo6\\src\\main\\txt\\"+Newname;
        String content = Files.lines(Paths.get(fileName)).reduce("", String::concat);
        writer.println("<p style='color:blue;text-align:center;font-size:30px'>" + content + "</p>");
    }



    public void destroy() {
    }
}