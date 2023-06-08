/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ribolovackodrustvo.a10;

/**
 *
 * @author Korisnik
 */
class PecarosDO {
    int ID;
    String ime,prezime,adresa,telefon;
    GradDO grad;

    @Override
    public String toString() {
        return ID + "-" + ime + " " + prezime;
    }
    
}
