package app.DAO.mysql;

import app.models.Distrito;
import app.models.Pais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gi Wah Davalos on 13/07/2016.
 */
public class DistritoDAO {

    private Connection con;

    private final String createDistritoSQL = "INSERT INTO distritos (nombre, poblacion, id_provincia) VALUES (?, ?, ?);";
    private final String updateDistritoSQL = "UPDATE distritos SET nombre = ?, poblacion = ?, id_provincia = ? WHERE id = ?";
    private final String readAllDistritosSQL = "SELECT * from distritos";
    private final String deleteDistritoSQL = "DELETE from distritos WHERE id = ?";

    public DistritoDAO(Connection con) {
        this.con = con;
    }

    public boolean createDistrito(String nombre, int poblacion, int id_provincia) throws SQLException{
        PreparedStatement pstmt = null;
        boolean rpta = false;

        try {
            pstmt = con.prepareStatement(createDistritoSQL);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, poblacion);
            pstmt.setInt(3, id_provincia);

            pstmt.executeUpdate();

            System.out.println("Inserted distrito: '" + nombre + "'" );
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

    public List<Distrito> getAllDistritos() {
        Statement stmt = null;
        ResultSet rs = null;
        List<Distrito> rpta = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(readAllDistritosSQL);
            Distrito tmpDistrito = null;

            while (rs.next()){
                tmpDistrito = new Distrito();
                tmpDistrito.setId(rs.getInt("id"));
                tmpDistrito.setNombre(rs.getString("nombre"));
                tmpDistrito.setPoblacion(rs.getInt("poblacion"));
                tmpDistrito.setId_provincia(rs.getInt("id_provincia"));

                rpta.add(tmpDistrito);
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

    public boolean updateDistrito(int id, String nombre, int poblacion, int id_provincia) throws SQLException{
        PreparedStatement pstmt = null;
        boolean rpta = false;

        try {
            pstmt = con.prepareStatement(updateDistritoSQL);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, poblacion);
            pstmt.setInt(3, id_provincia);
            pstmt.setInt(4, id);

            pstmt.executeUpdate();

            System.out.println("Updated distrito: '" + nombre + "'" );
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

    public boolean deleteDistrito(int id) throws SQLException{
        PreparedStatement pstmt = null;
        boolean rpta = false;

        try {
            pstmt = con.prepareStatement(deleteDistritoSQL);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.out.println("Deleted distrito: id -> '" + id+ "'" );
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
