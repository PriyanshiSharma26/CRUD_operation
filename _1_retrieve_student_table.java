import java.sql.*;
class _1_jdbc
{
    
    public static void main(String arg[])
    {
        Connection connection = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");//load the code dynamically[old version]
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/school",
                "root", "1234");
 
    
 
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                "select * from student");
            int sno;
            String sname;
            String address;
            while (resultSet.next()) {
                 sno= resultSet.getInt("sno");
                sname = resultSet.getString("sname").trim();
                address=resultSet.getString("address").trim();
                System.out.println("sno :" + sno
                                   + " sname : " + sname  +" address: "+address);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    } 
} 
