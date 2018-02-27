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
