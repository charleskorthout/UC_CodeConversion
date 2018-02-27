# UC_CodeConversion


Code conversie applicatie

Om programmeercode uit te voeren is een compiler nodig. Compilers bevatten ingewikkelde algoritmes, en zijn daardoor lastig te maken. Gelukkig zijn er JIT compilers, die programmacode op het laatste moment compileren naar machinecode en dus runtime converteren. Deze JIT compilers verlangen wel, dat de programmeercode in ht juiste formaat aangeboden wordt. 

We willen gebruik gaan maken van de SchriekieJIT compiler. Deze compiler verwacht dat code als tekst aangeleverd wordt, en dat de programmeercode postfix statements bevat. Dit is afwijkend t.o.v. ‘normale’ JAVA statements, die in infix notatie staan.

Voorbeeld:
a = b + 3;   moet geconverteerd worden naar:
a b 3 + = 

en:
if (a < b) {
  c = 5;
}
Wordt:
{ c 5 = } (a b <) if

Maak een GUI applicatie die JAVA code uit file kan inlezen, en deze converteerd van infix naar postfix notatie. Zowel de ingelezen als geconverteerde code dient weergegeven te worden naast elkaar. En de geconverteerde code dient weggeschreven te worden naar file.

Leg je algoritme uit via javadoc in je code. 

Lever testcases die aantonen dat je programma alle verschillende soorten statements kan converteren.

Hou een logboek bij waarin je je overwegingen voor je algoritme bijhoudt, en uitlegt welke structuur je overwogen hebt, en uiteindelijk gebruikt voor het afhandelen van je data.

## Stap 1 Definieer contract
Het volgende gedrag wordt verwacht van de expressie: 
- de mogelijkheid om een expressie te kunnen verfijnen of reduceren.
- het converteren en uitvoeren van een expressie naar:
    - een infix notatie
    - een postfix notatie

``` java

interface IExpression {
    /**
    * reduce comprimeert een expressie naar een efficientere expressie 
    * Bijvoorbeeld a = b + 3 + 5 => a = b + 8
    **/
    IExpression reduce();
    
    /**
    * methode asInfix print een expressie uit in de infix notatie
    **/
    string asInfix();
    
    /**
    * methode asPostfix print een expressie uit in de postfix notatie
    **/
    string asPostfix();
}

```

## Stap 2 EmptyExpression

We creeren nu een klasse die de bovenstaande interface implementeert. De klasse modeleert een lege expressie die we kunnen gebruiken om functionaliteit te kunnen testen

```java

public class EmptyExpression implements IExpression {
    private static EmptyExpression instance = null;
    private EmptyExpression() {
        // Exists only to defeat instantiation.
    }

    public static EmptyExpression getInstance() {
        if(instance == null) {
            instance = new EmptyExpression();
        }
        return instance;
    }

    @Override
    public IExpression reduce() {
        return EmptyExpression.getInstance();
    }

    @Override
    public String asInfix() {
        return new String();
    }

    @Override
    public String asPostfix() {
        return new String();
    }
}

```

## IExpressionTest

Op basis van de bovenstaande lege expressie maken we nu test de test methodes aan om de drie methodes van het contract te kunnen testen.

``` java

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


```

## De keuze van een mogelijke datastructuur om de expressie op te slaan


