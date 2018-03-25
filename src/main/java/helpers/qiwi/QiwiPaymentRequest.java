package helpers.qiwi;

public class QiwiPaymentRequest
{
    private String id;
    private Sum sum;
    private PaymentMethod paymentMethod;
    private Fields fields;
    private String comment;

    QiwiPaymentRequest(String newId, Sum newSum, PaymentMethod newPaymentMethod, Fields newFields, String newComment)
    {
        id = newId;
        sum = newSum;
        paymentMethod = newPaymentMethod;
        fields = newFields;
        comment = newComment;
    }

    public Sum getSum()
    {
        return sum;
    }

    public void setSum(Sum sum)
    {
        this.sum = sum;
    }

    public String getComment()
    {
        return comment;
    }

    public void setFields(Fields fields)
    {
        this.fields = fields;
    }

    public Fields getFields()
    {
        return fields;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public PaymentMethod getPaymentMethod()
    {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }
}
