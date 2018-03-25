package helpers.instagram;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Instagram
{
    public Long getId(String name)
    {
        String url = "http://127.0.0.1:8081";
        try
        {
            URL myUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Type", "GetUserName");
            con.setRequestProperty("Name", name);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();
            String ans = response.toString();
            if (ans.toCharArray()[0] == 'Y')
                return Long.valueOf(ans.substring(1));
            else
                return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isFollower(Long FollowerId, Long FollowedId)
    {
        String url = "http://127.0.0.1:8081";
        try
        {
            URL myUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Type", "Check");
            con.setRequestProperty("FollowerId", FollowerId.toString());
            con.setRequestProperty("FollowedId", FollowedId.toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();
            return response.toString().equals("YES");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
