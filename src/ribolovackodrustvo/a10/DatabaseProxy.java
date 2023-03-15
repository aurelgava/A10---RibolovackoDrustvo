/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ribolovackodrustvo.a10;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Korisnik
 */
public final class DatabaseProxy {
    public static final String URLBAZE = "jdbc:ucanaccess://src\\resursi\\Ribolovačko društvo.accdb";
    private static Connection c;
    public static void konektujSe(){
        try {
           c = DriverManager.getConnection(URLBAZE);
        } catch (SQLException ex) {            
            Logger.getLogger(DatabaseProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static ArrayList<PecarosDO> getPecarosi() {
        try {
            Statement s = c.createStatement(); 
            ResultSet rs = s.executeQuery("SELECT Pecaros.PecarosID AS PecarosID,"
                    + " Pecaros.Ime AS Ime,"
                    + " Pecaros.Prezime AS Prezime,"
                    + " Pecaros.Adresa AS Adresa,"
                    + " Pecaros.Telefon AS Telefon,"
                    + " Grad.Grad AS Grad,"
                    + " Grad.GradID AS GradID FROM "
                    + " Pecaros INNER JOIN Grad ON Pecaros.GradID=Grad.GradID");
            ArrayList<PecarosDO> pecarosi = new ArrayList<>();
            while(rs.next()){
                PecarosDO pecaros = new PecarosDO();
                pecaros.ID = rs.getInt("PecarosID");
                pecaros.ime = rs.getString("Ime");
                pecaros.prezime = rs.getString("Prezime");
                pecaros.adresa = rs.getString("Adresa");
                pecaros.telefon = rs.getString("Telefon");
                pecaros.grad = new GradDO();
                pecaros.grad.ID = rs.getInt("GradID");
                pecaros.grad.naziv = rs.getString("Grad");
                pecarosi.add(pecaros);
            }
            return pecarosi;
        } catch (SQLException ex) {
            
            Logger.getLogger(DatabaseProxy.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    static ArrayList<GradDO> getGradovi() {
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Grad");
            ArrayList<GradDO> gradovi = new ArrayList<>();
            while(rs.next()){
                GradDO grad = new GradDO();
                grad.ID = rs.getInt("GradID");
                grad.naziv = rs.getString("Grad");
                gradovi.add(grad);
            }
            return gradovi;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProxy.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
