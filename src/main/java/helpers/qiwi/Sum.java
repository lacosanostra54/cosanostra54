package helpers.qiwi;

public class Sum
{
    private Double amount;
    private String currency;

    Sum(){}

    Sum(Double newAmount)
    {
        currency = "643";
        amount = newAmount;
    }

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }
}
