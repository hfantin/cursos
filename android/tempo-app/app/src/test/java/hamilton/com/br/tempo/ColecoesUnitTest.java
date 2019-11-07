package hamilton.com.br.tempo;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ColecoesUnitTest {

    private Colecoes colecoes = new Colecoes();

    @Test
    public void testarAny() {
        assertTrue(colecoes.listAnyDivisao2());
        assertFalse(colecoes.listAnyMaior10());
    }

    @Test
    public void testarAll() {
        assertFalse(colecoes.listAllDivisao2());
        assertTrue(colecoes.listAllMenor10());
    }

    @Test
    public void testarCount() {
        assertEquals(3, colecoes.listCount());
    }

    @Test
    public void testarFold() {
        assertEquals(21, colecoes.listFold());
    }

    @Test
    public void testarFoldRight() {
        assertEquals(21, colecoes.listFoldRight());
    }

    @Test
    public void testarForeach() {
        colecoes.listForEach();
    }


    @Test
    public void testarForeachIndexed() {
        colecoes.listForEachIndexed();
    }

    @Test
    public void testarMax() {
        assertEquals((Integer) 6, colecoes.listMax());
    }

    @Test
    public void testarMaxBy() {
        assertEquals((Integer) 1, colecoes.listMaxBy());
    }

    @Test
    public void testarMin() {
        assertEquals((Integer) 1, colecoes.listMin());
    }

    @Test
    public void testarMinBy() {
        assertEquals((Integer) 6, colecoes.listMinBy());
    }

    @Test
    public void testarNone() {
        assertTrue( colecoes.listNone());
    }


    @Test
    public void testarReduce() {
        assertEquals(21, colecoes.listReduce());
    }


    @Test
    public void testarReduceRight() {
        assertEquals(21, colecoes.listReduceRight());
    }

    @Test
    public void testarSumBy() {
        assertEquals(3, colecoes.listSumBy());
    }

}