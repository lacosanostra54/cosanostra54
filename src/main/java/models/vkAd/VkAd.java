package models.vkAd;

public class VkAd
{
    private Integer vkGroupId;
    private Double amount;

    VkAd(Integer newVkGroupId, Double newAmount)
    {
        vkGroupId = newVkGroupId;
        amount = newAmount;
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
}
