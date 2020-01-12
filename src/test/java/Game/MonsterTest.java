package Game;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class MonsterTest {


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
            monster = new Monster(60);
        }

        /***************************************************************************
         * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
         */
        @After
        public void tearDown() {
        }
    public void testExistujeMonstrum() {
            Monster monster = new Monster(60);
        assertEquals(monster,monster.getMonster());

    }
    }
