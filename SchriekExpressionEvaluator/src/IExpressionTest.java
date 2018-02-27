import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IExpressionTest {

    IExpression expression1 = null;
    IExpression expression2 = null;

    @Before
    public void setUp() {
        expression1 = EmptyExpression.getInstance();
        expression2 = EmptyExpression.getInstance();
    }

    @After
    public void tearDown() {
        expression1 =null;
        expression2 =null;
    }


    /**
     * een lege expressie die gecomprimeerd wordt moet gelijk zijn aan een lege expressie
     */
    @Test
    public void EmptyExpressionReduceShouldMatchEmptyExpression() {
        assertEquals(expression1.reduce().asInfix(), expression2.asInfix());
    }

    /**
     * De infix uitvoer van een lege expressie moet gelijk zijn aan de postfix van die lege expressie
     */
    @Test
    public void InfixOfEmptyExpressionMustMatchPostfixOfEmptyExpression () {
        assertEquals(expression1.asInfix(), expression1.asPostfix());
    }

    /**
     * De postfix uitvoer van een lege expressie moet gelijk zijn aan de infix van die lege expressie
     */
    @Test
    public void PostfixOfEmptyExpressionMustMatchinfixOfEmptyExpression ()  {
        assertEquals(expression1.asPostfix(), expression1.asInfix());
    }
}