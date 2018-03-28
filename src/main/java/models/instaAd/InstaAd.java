package models.instaAd;

public class InstaAd
{

    private Long instaId;
    private String url;
    private Integer countOfDays;
    private Double amount;

    InstaAd(Long newInstaId, String newUrl, Integer newCountOfDays, Double newAmount)
    {
        instaId = newInstaId;
        url = newUrl;
        countOfDays = newCountOfDays;
        amount = newAmount;
    }

    public Integer getCountOfDays()
    {
        return countOfDays;
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
