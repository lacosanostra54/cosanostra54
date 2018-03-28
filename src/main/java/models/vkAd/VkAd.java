package models.vkAd;

public class VkAd
{
    private Integer vkGroupId;
    private Integer countOfDays;
    private Double amount;

    VkAd(Integer newVkGroupId, Integer newCountOfDays, Double newAmount)
    {
        vkGroupId = newVkGroupId;
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

    public Integer getVkGroupId()
    {
        return vkGroupId;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    public void setVkGroupId(Integer vkGroupId)
    {
        this.vkGroupId = vkGroupId;
    }

    public void setCountOfDays(Integer countOfDays)
    {
        this.countOfDays = countOfDays;
    }
}
