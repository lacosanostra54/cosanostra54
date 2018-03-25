package helpers.qiwi;

public class PaymentMethod
{
    private String type;
    private String accountId;

    PaymentMethod()
    {
        type = "Account";
        accountId = "643";
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
