package app.DAO.mysql;

import app.models.Provincia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gi Wah Davalos on 13/07/2016.
 */
public class ProvinciaDAO {

    private Connection con;
    private final String createProvinciaSQL = "INSERT INTO provincias (nombre, id_departamento) VALUES (?, ?);";
    private final String updateProvinciaSQL = "UPDATE provincias SET nombre = ?, id_departamento = ? WHERE id = ?";
    private final String readAllProvinciasSQL = "SELECT * from provincias";
    private final String deleteProvinciaSQL = "DELETE from provincias WHERE id = ?";

    public ProvinciaDAO(Connection mysqlConnection) {
        this.con = mysqlConnection;
    }

    public boolean createProvincia(String nombre, int id_departamento) throws SQLException{
        PreparedStatement pstmt = null;
        boolean rpta = false;

        try {
            pstmt = con.prepareStatement(createProvinciaSQL);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, id_departamento);

            pstmt.executeUpdate();

            System.out.println("Inserted provincia: '" + nombre + "'" );
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

    public List<Provincia> getAllProvincias() {
        Statement stmt = null;
        ResultSet rs = null;
        List<Provincia> rpta = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(readAllProvinciasSQL);
            Provincia tmpProvincia = null;

            while (rs.next()){
                tmpProvincia = new Provincia();
                tmpProvincia.setId(rs.getInt("id"));
                tmpProvincia.setNombre(rs.getString("nombre"));
                tmpProvincia.setId_departamento(rs.getInt("id_departamento"));

                rpta.add(tmpProvincia);
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

    public boolean updateProvincia(int id, String nombre, int id_departamento) throws SQLException{
        PreparedStatement pstmt = null;
        boolean rpta = false;

        try {
            pstmt = con.prepareStatement(updateProvinciaSQL);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, id_departamento);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();

            System.out.println("Updated provincia: '" + nombre + "'" );
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

    public boolean deleteProvincia(int id) throws SQLException{
        PreparedStatement pstmt = null;
        boolean rpta = false;

        try {
            pstmt = con.prepareStatement(deleteProvinciaSQL);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.out.println("Deleted provincia: id -> '" + id+ "'" );
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
