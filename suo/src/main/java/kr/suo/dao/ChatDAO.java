package kr.suo.dao;

import kr.suo.vo.Chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChatDAO {

    private Connection conn;

    public ChatDAO() {
        try {
            String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
            String id = "week7";
            String pwd = "week7";

            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url, id, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Chat> getChatList(String nowTime) {
        ArrayList<Chat> chatList = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from chat where chatTime > ? order by chatTime";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nowTime);
            rs = pstmt.executeQuery();
            chatList = new ArrayList<Chat>();
            while (rs.next()) {
                Chat chat = new Chat();
                chat.setChatID(rs.getInt("chatID"));
                chat.setChatName(rs.getString("chatName"));
                chat.setChatContent(rs.getString("chatContent")
                        .replaceAll(" ", "&nbsp;").replaceAll(">", "&gt;")
                        .replaceAll("<", "&lt;").replaceAll("\n","<br>"));
                int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
                String timeType="오전";
                if(Integer.parseInt(rs.getString("chatTime").substring(11,13)) >= 12){
                    timeType="오후";
                    chatTime-=12;
                }
                chat.setChatTime(rs.getString("chatTime").substring(0,11)+" "+timeType+" "+chatTime+ ":" + rs.getString("chatTime").substring(14,16)+"");
            //    chat.setChatTime(rs.getString("chatTime"));
                chatList.add(chat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chatList;
    }

    public ArrayList<Chat> getChatListByRecent(int num) {
        ArrayList<Chat> chatList = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from chat where chatID > (select max(chatID)-? from chat)  order by chatTime";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            chatList = new ArrayList<Chat>();
            while (rs.next()) {
                Chat chat = new Chat();
                chat.setChatID(rs.getInt("chatID"));
                chat.setChatName(rs.getString("chatName"));
                chat.setChatContent(rs.getString("chatContent")
                        .replaceAll(" ", "&nbsp;").replaceAll(">", "&gt;")
                        .replaceAll("<", "&lt;").replaceAll("\n","<br>"));
                int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
                String timeType="오전";
                if(Integer.parseInt(rs.getString("chatTime").substring(11,13)) >= 12){
                    timeType="오후";
                    chatTime-=12;
                }
                chat.setChatTime(rs.getString("chatTime").substring(0,11)+" "+timeType+" "+chatTime+ ":" + rs.getString("chatTime").substring(14,16)+"");
 //               chat.setChatTime(rs.getString("chatTime"));
                chatList.add(chat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chatList;
    }

    public ArrayList<Chat> getChatListByRecent(String chatID) {
        ArrayList<Chat> chatList = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from chat where chatID > ? order by chatTime";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, chatID);
            rs = pstmt.executeQuery();
            chatList = new ArrayList<Chat>();
            while (rs.next()) {
                Chat chat = new Chat();
                chat.setChatID(rs.getInt("chatID"));
                chat.setChatName(rs.getString("chatName"));
                chat.setChatContent(rs.getString("chatContent")
                        .replaceAll(" ", "&nbsp;").replaceAll(">", "&gt;")
                        .replaceAll("<", "&lt;").replaceAll("\n","<br>"));
                int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
                String timeType="오전";
                if(Integer.parseInt(rs.getString("chatTime").substring(11,13)) >= 12){
                    timeType="오후";
                    chatTime-=12;
                }
                chat.setChatTime(rs.getString("chatTime").substring(0,11)+" "+timeType+" "+chatTime+ ":" + rs.getString("chatTime").substring(14,16)+"");
//                chat.setChatTime(rs.getString("chatTime"));
                chatList.add(chat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chatList;
    }

    public int submit(String chatName, String chatContent) {
        int r = -1;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "insert into chat values (chatID_seq.nextval,?,?,sysdate)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, chatName);
            pstmt.setString(2, chatContent);
            r = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

}
