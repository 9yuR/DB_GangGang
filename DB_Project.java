import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

public class DB_Project
{

    public static void main(String[] args) throws SQLException
    {

        Connection conn;
        Statement st;
        ResultSet rs;

        String url = "jdbc:postgresql://localhost:5432/";
        String user = "postgres";
        String password = "103404";

        try
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Database SQL Project: Good-shop & place recommendation service demo");

            // JDBC를 이용해 PostgreSQL 서버 및 데이터베이스 연결
            System.out.println("Connecting PostgreSQL database");
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();

//            final String create_table = "create table Users(userID int, name varchar(10));" +
//                    "create table review_comment(userID int, uPlace varchar(20), comment varchar(500))" +
//                    "create table review_starrate(userID int, VisitRoute varchar(50), rating float)";
//            st.execute(create_table);

//            final String insert_values = "insert into Goodshop values(111, '착한가게1', '경기도 수원시 팔달구 우만동 94-16', '01087310672', '정보가 없습니다.','www.google.com/img2', 1000, null);"+
//                    "insert into Place values(187, 'ko', '아주대학교', '경기도 수원시 영통구 월드컵로 206', '031-254-6802', '정보가 없습니다', null);";
//            st.execute(insert_values);

            int user_id=1;
            System.out.println("이 프로그램은 착한가격업소 및 관광명소를 추천하는 서비스입니다.");
            while(true) {
                System.out.println("먼저 사용하시는 분의 성함과 추천 받고자 하는 장소의 이름을 입력해주세요.");
                String location;
                int user_cost;
                String user_name;
                System.out.println("1. Input user name: ");
                user_name = scan.nextLine();
                final String insert_user = String.format("insert into Users(%d, %s)", user_id,user_name);
//                        "insert into review_comment values(user_id, null, null);" +
//                        "insert into review_starrate values(user_id, null, null);";
                st.execute(insert_user);

                System.out.print("2. Input location.(구 단위로 입력): ");
                location = scan.nextLine();

                System.out.print("3. Show me the money: ");
                user_cost = scan.nextInt();

                final String search_location = String.format("select * from (select * from Goodshop where address like '%%s%' and cost <10000 \n" +
                        "            order by random() limit 1) as a \n"+
                        "union \n" +
                        "select * from(select * from Place where address like '%%s%' order by random() limit 1) as b;", location, location);
                rs = st.executeQuery(search_location);

                while(rs.next()) {
//                    int sID = rs.getInt("sID");
                    String sName = rs.getString("sName");
                    String s_info = rs.getString("s_info");
//                    String PH = rs.getString("PH");
//                    String s_info = rs.getString("s_info");
//                    String img = rs.getString("img");
//                    int cost = rs.getInt("cost");
//                    String s_recommendation = rs.getString("s_recommendation");
                    System.out.println("착한 가격 업소 검색 결과입니다!");
//                    System.out.println(String.format("%d | %s | %s | %s | %s | %s | %d | %s |", sID, sName, address, PH, s_info, img, cost, s_recommendation));
                    System.out.println(String.format("%s, %s", sName, s_info));
                    // 별점 기능은 일단 테스트 완료 후에 진행하는 것으로~
                    System.out.print("별점을 입력해주세요(0을 입력할 시 별점리뷰 없이 진행됩니다.):" );
                    scan.nextLine();

                }

//                final String search_cost = "select * from Goodshop where address like '%rocation%' and cost < 10000;";
//                rs = st.executeQuery(search_cost);
//
//                while(rs.next()) {
//                    int pID = rs.getInt("pID");
//                    String language = rs.getString("language");
//                    String pName = rs.getString("pName");
//                    String address = rs.getString("address");
//                    String PH = rs.getString("PH");
//                    String p_info = rs.getString("p_info");
//                    String p_recommendation = rs.getString("p_recommendation");
//
//                    System.out.println("추천 장소입니다!");
//                    System.out.println(String.format("%d | %s | %s | %s | %s | %s | %s |", pID, language, pName, address, PH, p_info, p_recommendation));
//
//                    // 별점 기능은 일단 테스트 완료 후에 진행하는 것으로~
//                    System.out.print("별점을 입력해주세요 : ");
//                    scan.nextLine();
//                }
                int end;
                System.out.print("다른 사용자로 진행하시겠습니까?(0 입력 시 프로그램 종료): ");
                end = scan.nextInt();
                if (end == 0) break;
            }
            System.out.println("프로그램을 종료합니다.");
        }
        catch(SQLException ex) {
            throw ex;
        }
    }
}