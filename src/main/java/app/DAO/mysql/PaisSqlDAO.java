package app.DAO.mysql;

import app.models.mysql.Pais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PaisSqlDAO {

    private Connection con;
    private final String readAllPaisesSQL = "SELECT * from paises";

    private final String readPaisSQL_byId = "SELECT * from paises WHERE id = ?";

    public PaisSqlDAO(Connection con) {
        this.con = con;
    }

    public Pais getPais (int id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Pais pais = null;

        try {
            pstmt = con.prepareStatement(readPaisSQL_byId);
            pstmt.setInt(1, id);
            pais = new Pais();
            rs = pstmt.executeQuery();

            // No se usa while por que es solamente un registro
            rs.next();
            pais.setId(rs.getInt("id"));
            pais.setPbi(rs.getInt("pbi"));
            pais.setNombre(rs.getString("nombre"));

            System.out.println("Found pais: '" + pais.getNombre() + "'" );


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return pais;
    }

    public List<Pais> getAllPaises() {
        Statement stmt = null;
        ResultSet rs = null;
        List<Pais> rpta = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(readAllPaisesSQL);
            Pais tmpPais = null;

            while (rs.next()){
                tmpPais = new Pais();
                tmpPais.setId(rs.getInt("id"));
                tmpPais.setNombre(rs.getString("nombre"));
                tmpPais.setPbi(rs.getInt("pbi"));

                rpta.add(tmpPais);
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

}
