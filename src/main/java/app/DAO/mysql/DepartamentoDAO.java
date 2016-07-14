package app.DAO.mysql;

import app.models.Departamento;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gi Wah Davalos on 13/07/2016.
 */
public class DepartamentoDAO {

    private Connection con;
    private final String createDepartamentoSQL = "INSERT INTO departamentos (nombre, id_pais) VALUES (?, ?);";
    private final String updateDepartamentoSQL = "UPDATE departamentos SET nombre = ?, id_pais = ? WHERE id = ?";
    private final String readAllDepartamentosSQL = "SELECT * from departamentos";
    private final String deleteDepartamentoSQL = "DELETE from departamentos WHERE id = ?";

    public DepartamentoDAO(Connection mysqlConnection) {
        this.con = mysqlConnection;
    }

    public boolean createDepartamento(String nombre, int id_pais) throws SQLException{
        PreparedStatement pstmt = null;
        boolean rpta = false;

        try {
            pstmt = con.prepareStatement(createDepartamentoSQL);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, id_pais);

            pstmt.executeUpdate();

            System.out.println("Inserted departamento: '" + nombre + "'" );
            rpta = true;

        } catch (SQLException e) {
            e.printStackTrace();
            rpta = false;
        } finally{
            //finally block used to close resources
            try{
                if(pstmt != null)
                    pstmt.close();
            }catch(SQLException se2){
            }
        }

        return rpta;
    }

    public List<Departamento> getAllDepartamentos() {
        Statement stmt = null;
        ResultSet rs = null;
        List<Departamento> rpta = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(readAllDepartamentosSQL);
            Departamento tmpDepartamento = null;

            while (rs.next()){
                tmpDepartamento = new Departamento();
                tmpDepartamento.setId(rs.getInt("id"));
                tmpDepartamento.setNombre(rs.getString("nombre"));
                tmpDepartamento.setId_pais(rs.getInt("id_pais"));

                rpta.add(tmpDepartamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
        }

        return rpta;
    }

    public boolean updateDepartamento(int id, String nombre, int id_pais) throws SQLException{
        PreparedStatement pstmt = null;
        boolean rpta = false;

        try {
            pstmt = con.prepareStatement(updateDepartamentoSQL);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, id_pais);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();

            System.out.println("Updated departamento: '" + nombre + "'" );
            rpta = true;

        } catch (SQLException e) {
            e.printStackTrace();
            rpta = false;
        } finally{
            //finally block used to close resources
            try{
                if(pstmt != null)
                    pstmt.close();
            }catch(SQLException se2){
            }
        }

        return rpta;
    }

    public boolean deleteDepartamento(int id) throws SQLException{
        PreparedStatement pstmt = null;
        boolean rpta = false;

        try {
            pstmt = con.prepareStatement(deleteDepartamentoSQL);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.out.println("Deleted departamento: id -> '" + id+ "'" );
            rpta = true;

        } catch (SQLException e) {
            e.printStackTrace();
            rpta = false;
        } finally{
            //finally block used to close resources
            try{
                if(pstmt != null)
                    pstmt.close();
            }catch(SQLException se2){
            }
        }

        return rpta;
    }
}
