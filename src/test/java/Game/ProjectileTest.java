package Game;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class ProjectileTest {


    private Projectile projectile;
    private Monster monster;



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
        projectile = new Projectile(monster, 3 , 3);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }
    public void testExistujeMonstrum() {
        Projectile projectile = new Projectile(monster,3,3);
        assertEquals(monster,projectile.getTarget());

    }
}
