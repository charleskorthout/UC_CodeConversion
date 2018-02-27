public interface IExpression {
         /**
         * reduce comprimeert een expressie naar een efficientere expressie
         * Bijvoorbeeld a = b + 3 + 5 => a = b + 8
         **/
        IExpression reduce();

        /**
         * methode asInfix print een expressie uit in de infix notatie
         **/
        String asInfix();

        /**
         * methode asPostfix print een expressie uit in de postfix notatie
         **/
        String asPostfix();

}
