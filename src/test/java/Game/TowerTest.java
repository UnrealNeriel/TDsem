package Game;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class TowerTest {

    private Tower tower;


    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        tower = new Tower(3,3);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }
    public void testExistujeMonstrum() {
        Tower tower1 = new Tower(3,3);
        tower1.setAttackDamage(60);
        assertEquals(tower1,tower1.getAttackDamage());

    }
}

