package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private final int MAX_CONNECTIONS = 20;
    private final String URL = "jdbc:mysql://localhost:3306/couponsdb";
    private final String USER = "root";
    private final String PASSWORD = "Qwert1trewq!";

    private List<Connection> connections = new ArrayList<>(MAX_CONNECTIONS);

    private static ConnectionPool instance;

    private ConnectionPool() throws SQLException {
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            connections.add(DriverManager.getConnection(URL, USER, PASSWORD));
        }
    }

    public static ConnectionPool getInstance() throws SQLException {
        if(instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public synchronized Connection getConnection(){
        while(connections.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());;
            }
        }

        Connection con = connections.get(connections.size()-1);
        connections.remove(con);
        return con;
    }

    public synchronized void restoreConnection(Connection connection){
        connections.add(connection);
        notify(); // if thread is waiting wake them up!
    }

    public synchronized void closeConnections(){
        while(connections.size() < MAX_CONNECTIONS) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        for(Connection con: connections){
            try {
                con.close();
            } catch (SQLException e) {}
        }
    }
}
