/*Write a Java program that performs CRUD operations on an Employee table in a database. The Employee table has the following structure:

id (INT, Primary Key, Auto-increment)
name (VARCHAR)
department (VARCHAR)
salary (DECIMAL)
Your program should include methods to:

Add a new employee
Retrieve all employees
Update an employee's salary based on their ID
Delete an employee based on their ID*/

package questions;
import java.sql.*;
import java.util.Scanner;

class employee{
    public static Connection connect()
    {
       String url="jdbc:mysql://localhost:3306/practice";
       String user="root";
       String password="1234";

       Connection con=null;
       try{
        con=DriverManager.getConnection(url, user, password);
        System.out.println("connection successful");
       }
       catch(SQLException e){
        e.printStackTrace();
        

       }
       return con;
    }


       public void insert(int id,String name,String department,double salary)
    {
        String sql="insert into Employee(id,name,department,salary)values(?,?,?,?)";

        try(Connection con=employee.connect() )
        {
            PreparedStatement st= con.prepareStatement(sql);
            st.setInt(1,id);
            st.setString(2, name);
            st.setString(3, department);
            st.setDouble(4, salary);
            st.executeUpdate();
            System.out.println("insertion done");
            con.close();

        }

        catch(SQLException e)
        {
            e.printStackTrace();;
        }
    }



    public void  update( int id,String name,String department,double salary)
    {
        String sql="update Employee set name=?,department=?,salary=? where id=?";

        try(Connection con=employee.connect())
        {
            PreparedStatement st=con.prepareStatement(sql);
    
            st.setString(1,name);
            st.setString(2, department);
            st.setDouble(3, salary);
            st.setInt(4, id);

           int rowupdated= st.executeUpdate();
            

              if (rowupdated > 0) {
            System.out.println("Employee updated successfully."); // Success message
        } else {
            System.out.println("No employee found with ID: " + id); // ID not found message
        }
    }
        catch(SQLException e)
        {
          e.printStackTrace();
        }

    }

    public void retrieve()
    {
        String sql="select * from Employee";

        try(Connection con=employee.connect())
        {
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next())
            {
               int id=rs.getInt("id");
               String name=rs.getString("name");
               String department=rs.getString("department");
               double salary=rs.getDouble("salary");

               System.out.println("id is"+id+" "+"name is"+name+" "+"department is"+department+" "+"salary is"+salary);

            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }


    }

    public void delete( int id)

    {
        String sql="delete  from Employee where id=?";
        try(Connection con=employee.connect())
        {
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,id);
            st.executeUpdate();
            System.out.println("deletion sucessfull");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}




     class DRiver{
    public static void main(String[] args) {
        Scanner sc=new  Scanner(System.in);

        employee e=new employee();
        int choice=0;

        while(choice!=5)
        {
    
        System.out.println("1.insertion");
        System.out.println("2.retrieval");
        System.out.println("3.update");
        System.out.println("4.delete");


        System.out.println("enter your choice");
         choice=sc.nextInt();
        sc.nextLine();


        switch(choice)
        {
            case 1:

            System.out.println("Enter the ID of employee:");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consume the leftover newline

                    System.out.println("Enter the name of employee:");
                    String name = sc.nextLine(); // Now reads the name correctly

                    System.out.println("Enter the department of employee:");
                    String department = sc.nextLine(); // Now reads the department correctly

                    System.out.println("Enter the salary of employee:");
                    double salary = sc.nextDouble();
                    e.insert(id, name, department, salary); // Call insert method
                    break;

            case 2:
                e.retrieve();
                break;
            case 3:
                System.out.println("Enter the ID of employee to update:");
                int updateId = sc.nextInt();
                sc.nextLine(); // Consume the leftover newline
                System.out.println("Enter the new name of employee:");
                String newName = sc.nextLine();
                System.out.println("Enter the new department of employee:");
                String newDepartment = sc.nextLine();
                System.out.println("Enter the new salary of employee:");
                double newSalary = sc.nextDouble();
                e.update(updateId, newName, newDepartment, newSalary);
                break;
            case 4:
                System.out.println("Enter the ID of employee to delete:");
                int deleteId = sc.nextInt();
                e.delete(deleteId);
                break;
            default:
              System.out.println("Invalid choice");
        }
        
        }

        
    }
}
     





      
    













        // e.insert(2, "kashi", "IT department", 40000);


        // e.retrieve();

        // e.update(2,"priyanshi","commerece",70000);

        // e.delete(1);
    
    







 
    
