package kr.suo.controller;

import kr.suo.dao.ChatDAO;
import kr.suo.vo.Chat;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "/chatListServlet",value = "/chatListServlet")
public class ChatListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String listType = req.getParameter("listType");
        if(listType == null || listType.equals("")) {
            resp.getWriter().write("");
        }else if(listType.equals("today")){
            resp.getWriter().write(getToday());
        }else if(listType.equals("ten")){
            resp.getWriter().write(getTen());
        }else{
            try{
                Integer.parseInt(listType);
                resp.getWriter().write(getID(listType));
            }catch(Exception e){
                resp.getWriter().write("");
            }
        }


    }

    public String getToday(){
        StringBuffer result = new StringBuffer("");
        result.append("{\"result\":[");
        ChatDAO chatDAO = new ChatDAO();
        ArrayList<Chat> chatList = chatDAO.getChatList(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        for(int i = 0 ; i <chatList.size(); i++){
            result.append("[{\"value\":\""+chatList.get(i).getChatName()+ "\"},");
            result.append("{\"value\":\""+chatList.get(i).getChatContent()+ "\"},");
            result.append("{\"value\":\""+chatList.get(i).getChatTime()+ "\"}]");
            if(i != chatList.size()-1){
                result.append(",");
            }
        }
        result.append("],\"last\":\""+ chatList.get(chatList.size()-1).getChatID()+"\"}");
        return result.toString();
    }

    public String getTen(){
        StringBuffer result = new StringBuffer("");
        result.append("{\"result\":[");
        ChatDAO chatDAO = new ChatDAO();
        ArrayList<Chat> chatList = chatDAO.getChatListByRecent(10);
        for(int i = 0 ; i <chatList.size(); i++){
            result.append("[{\"value\":\""+chatList.get(i).getChatName()+ "\"},");
            result.append("{\"value\":\""+chatList.get(i).getChatContent()+ "\"},");
            result.append("{\"value\":\""+chatList.get(i).getChatTime()+ "\"}]");
            if(i != chatList.size()-1){
                result.append(",");
            }
        }
        result.append("],\"last\":\""+ chatList.get(chatList.size()-1).getChatID()+"\"}");
        return result.toString();
    }

    public String getID(String chatID){
        StringBuffer result = new StringBuffer("");
        result.append("{\"result\":[");
        ChatDAO chatDAO = new ChatDAO();
        ArrayList<Chat> chatList = chatDAO.getChatListByRecent(chatID);
        for(int i = 0 ; i <chatList.size(); i++){
            result.append("[{\"value\":\""+chatList.get(i).getChatName()+ "\"},");
            result.append("{\"value\":\""+chatList.get(i).getChatContent()+ "\"},");
            result.append("{\"value\":\""+chatList.get(i).getChatTime()+ "\"}]");
            if(i != chatList.size()-1){
                result.append(",");
            }
        }
        result.append("],\"last\":\""+ chatList.get(chatList.size()-1).getChatID()+"\"}");
        return result.toString();
    }
}
