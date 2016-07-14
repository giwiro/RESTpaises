package app.DAO.mysql;

import app.models.Pais;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gi Wah Davalos on 13/07/2016.
 */
public class PaisDAO {

    private Connection con;
    private final String readAllPaisesSQL = "SELECT * from paises";

    public PaisDAO(Connection con) {
        this.con = con;
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
