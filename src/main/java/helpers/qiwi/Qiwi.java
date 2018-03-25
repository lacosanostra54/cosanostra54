package helpers.qiwi;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Qiwi
{
    //a53f0ece4038bfb15670e03a2ecc71ec
    private String accessToken;

    public Qiwi(String newAccessToken)
    {
       accessToken = newAccessToken;
    }

    public QiwiPaymentResponse sendMoneyToQiwi(double amount, String accountNumber, String comment) throws IOException
    {
        QiwiPaymentRequest qiwiPaymentRequest = new QiwiPaymentRequest(Long.toString(System.currentTimeMillis()),
                new Sum(amount), new PaymentMethod(),
                new Fields(accountNumber), comment);
        String strQiwiPaymentRequest = JSON.toJSONString(qiwiPaymentRequest);
        System.out.println(strQiwiPaymentRequest);
        String url = "https://edge.qiwi.com/sinap/api/v2/terms/99/payments";
        URL myUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + accessToken);

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.write (strQiwiPaymentRequest.getBytes());
        }

        StringBuilder content;

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {

            String line;
            content = new StringBuilder();

            while ((line = in.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        }

        QiwiPaymentResponse paymentResponse = JSON.parseObject(content.toString(), QiwiPaymentResponse.class);
        System.out.println(paymentResponse.getComment());
        con.disconnect();
        return paymentResponse;
    }

}
