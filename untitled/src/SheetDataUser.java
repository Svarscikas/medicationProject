import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.DataOutputStream;




public class SheetDataUser {
    private  String USER_AGENT = "Mozilla/5.0";
    //private String formule="="&\"bandymas\""&"&ROW()";
    private  String GET_URL = "https://script.google.com/macros/s/AKfycbwa7fydst-VtNtxs1k1bfSg_Ivvi6XptgixFDoitS79PbRwfnzn/exec";//?a=2018-06-27&b=bandymas";
    private  String WRITE_PARAMS;
    private  String READ_PARAMS = "?action=read";
    private  String jsonString;

    public SheetDataUser() throws IOException {
        sendReadGET();
    }

    public SheetDataUser(String writeParams,Boolean returnAllData) throws IOException {
//        if(returnAllData){
        WRITE_PARAMS="?action=write&"+writeParams;
        sendWriteGET(returnAllData);
//        }
    }

    public void setWRITE_PARAMS(String WRITE_PARAMS) {
        this.WRITE_PARAMS = WRITE_PARAMS;
    }
//    public SheetDataUser(String data) {
//        this.data = data;
//    }

    public String getJsonString() {
        return jsonString;
    }

    private  void sendWriteGET(Boolean returnAll) throws IOException {
        URL obj = new URL(GET_URL+WRITE_PARAMS+"&returnAllData="+new Boolean(returnAll).toString());
        System.out.println(GET_URL+WRITE_PARAMS+"&returnAllData="+new Boolean(returnAll).toString());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        //String err=          con.getErrorStream().toString();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            //System.out.println(response.toString());
            jsonString=response.toString();

        } else {
            System.out.println("GET request not worked");
            String err=          con.getErrorStream().toString();
            System.out.println(err);
        }

    }


    private  void sendReadGET() throws IOException {
        URL obj = new URL(GET_URL+READ_PARAMS);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        //String err=          con.getErrorStream().toString();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            //System.out.println(response.toString());
            jsonString=response.toString();

        } else {
            System.out.println("GET request not worked");
            String err=          con.getErrorStream().toString();
            System.out.println(err);
        }

    }
}