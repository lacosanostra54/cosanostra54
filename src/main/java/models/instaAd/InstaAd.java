package models.instaAd;

public class InstaAd
{

    private Long instaId;
    private String url;
    private Double amount;

    InstaAd(Long newInstaId, String newUrl, Double newAmount)
    {
        instaId = newInstaId;
        url = newUrl;
        amount = newAmount;
    }

    public Double getAmount()
    {
        return amount;
    }

    public Long getInstaId()
    {
        return instaId;
    }

    public String getUrl()
    {
        return url;
    }
}
