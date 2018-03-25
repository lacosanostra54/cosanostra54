package helpers.qiwi;

public class Transaction
{
    private String id;
    private State state;

    public State getState()
    {
        return state;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setState(State state)
    {
        this.state = state;
    }

    public String getId()
    {
        return id;
    }
}
