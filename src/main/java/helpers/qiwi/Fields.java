package helpers.qiwi;

public class Fields
{
    private String account;

    Fields()
    {
    }

    Fields(String newAccount)
    {
        account = newAccount;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }
}
