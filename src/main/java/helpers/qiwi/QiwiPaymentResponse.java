package helpers.qiwi;

public class QiwiPaymentResponse
{
    private String account;
    private String terms;
    private Fields fields;
    private Sum sum;
    private String source;
    private String comment;
    private Transaction transaction;

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public Fields getFields()
    {
        return fields;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getComment()
    {
        return comment;
    }

    public void setFields(Fields fields)
    {
        this.fields = fields;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getTerms()
    {
        return terms;
    }

    public void setSum(Sum sum)
    {
        this.sum = sum;
    }

    public Sum getSum()
    {
        return sum;
    }

    public void setTerms(String terms)
    {
        this.terms = terms;
    }

    public Transaction getTransaction()
    {
        return transaction;
    }

    public void setTransaction(Transaction transaction)
    {
        this.transaction = transaction;
    }
}
