package kr.suo.controller;

import kr.suo.dao.ChatDAO;
import kr.suo.vo.Chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet(name = "/chatSubmitServlet",value = "/chatSubmitServlet")
public class ChatSubmitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String chatName = URLDecoder.decode(req.getParameter("chatName"),"UTF-8");
        String chatContent = URLDecoder.decode(req.getParameter("chatContent"),"UTF-8");

        if(chatName==null || chatName.equals("")||chatContent==null||chatContent.equals("")){
            resp.getWriter().write("0");
        }else{
            resp.getWriter().write(new ChatDAO().submit(chatName,chatContent)+"");
        }

    }
}
