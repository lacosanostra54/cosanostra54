package helpers.vk;

import com.vk.api.sdk.client.ClientResponse;
import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.base.BoolInt;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.responses.GetMembersResponse;
import com.vk.api.sdk.objects.users.User;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.groups.GroupField;
import com.vk.api.sdk.queries.users.UserField;
import kotlin.Pair;

import java.util.List;

public class VkChecker
{
    private ServiceActor serviceActor;
    private  VkApiClient vkApiClient;

    public VkChecker(int appId, String accessToken)
    {
        serviceActor = new ServiceActor(appId, accessToken);
        TransportClient transportClient = HttpTransportClient.getInstance();
        vkApiClient = new VkApiClient(transportClient);
    }

    public Pair<Integer, String> getUserIdAndScreenName(String s) throws ClientException, ApiException
    {
        List<UserXtrCounters> lst = vkApiClient.users().get(serviceActor).userIds(s).fields(UserField.SCREEN_NAME).execute();
        return new Pair<>(lst.get(0).getId(), lst.get(0).getScreenName());
    }

    public String getGroupScreenName(Integer id) throws ApiException, ClientException
    {
        List<GroupFull> lst = vkApiClient.groups().getById(serviceActor).groupId(id.toString()).fields(GroupField.SCREEN_NAME).execute();
        if (lst.size() == 0)
            return null;
        else
            return lst.get(0).getScreenName();

    }

    public boolean isMember(Integer userId, String groupId)
    {
        try
        {
            return (vkApiClient.groups().isMember(serviceActor, groupId).userId(userId).execute().getValue() != 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
