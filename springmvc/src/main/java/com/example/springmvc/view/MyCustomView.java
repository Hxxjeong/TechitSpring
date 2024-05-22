package com.example.springmvc.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.View;

import java.io.PrintWriter;
import java.util.Map;

public class MyCustomView implements View { // View
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        PrintWriter writer = response.getWriter();
        writer.print("<html><body>");
        writer.print("<h2>Custom Page</h2>");
        writer.print("<p>This is my custom view.</p>");
        writer.print("</body></html>");

        writer.close();
    }
}
