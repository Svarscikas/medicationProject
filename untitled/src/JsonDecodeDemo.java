import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.lang.reflect.Array;


public class JsonDecodeDemo {
    private JSONParser parser = new JSONParser();
    private  String s= "[[\"Data\",\"Numeris\",\"Žodis\",\"Skaičius\"]," +
            "[\"2018-05-11T00:00:00.000Z\",4,\"bandymas\",13]," +
            "[\"2018-06-13T00:00:00.000Z\",5,\"Abc\",20]," +
            "[\"2018-06-02T00:00:00.000Z\",16,\"llO\",175.444]," +
            "[\"2018-05-04T00:00:00.000Z\",13,\"425\",222.2]," +
            "[\"2018-06-27T00:00:00.000Z\",0,\"3007\",111.3]," +
            "[\"2018-06-28T00:00:00.000Z\",14,\"test\",14.23]," +
            "[\"2018-06-16T00:00:00.000Z\",44,\"aaa654\",66.66]," +
            "[\"2018-05-31T00:00:00.000Z\",46,\"bNBG\",331.01]," +
            "[\"2018-06-30T00:00:00.000Z\",134,\"AAAAA\",15]," +
            "[\"2018-07-01T00:00:00.000Z\",41,\"BBBBBBBBBBBBBBBBB\",7803]," +
            "[\"2018-06-15T00:00:00.000Z\",54,\"22222\",0.01]]";
    private Object objRow;
    private JSONArray rowarray;
    private String rowstring;
    private Object obj;
    private JSONArray cells;
    private Object[][] arr;
    //arr = new Object[1][1];
    private int rowCount;
    private int colCount;
    private String [] header;

    public JsonDecodeDemo(String jsonString){
        this.s=jsonString;
        JsonToArrstart();

    }
    public JsonDecodeDemo(){
        JsonToArrstart();
    }
    public String[] getHeader(){
        return header;
    }

    public Object [][] getArr(){
        return arr;
    }

    private  void  JsonToArrstart(){
        try{
            objRow = parser.parse(s);
            rowarray = (JSONArray)objRow;
            rowCount=rowarray.size();
            System.out.println("rowCount:"+rowCount);
            rowstring = rowarray.get(0).toString();
            //System.out.println(rowstring);
            obj=parser.parse(rowstring);
            cells=(JSONArray)obj;
            colCount=cells.size();
            header=new String[colCount];
            for (int i=0;i<colCount; i++){
                header[i]=cells.get(i).toString();
            }
            //System.out.println(header.toString());
            //System.out.println(header[0]);
            //System.out.println(header[1]);
            arr=new Object[rowCount-1][colCount];


            //System.out.println("The 2nd element of array");
            //System.out.println(rowarray.get(3));

            for (int row=1;row<rowCount;row++){
                //System.out.println(rowarray.get(row).toString());
                rowstring = rowarray.get(row).toString() ;
//                System.out.println("eil - "+row+rowstring);
                obj=parser.parse(rowstring);
                cells=(JSONArray)obj;
                for (int col=0;col<cells.size();col++) {
                    if (col==2){
                        arr[row-1][col] = cells.get(col).toString().split("T")[0];

                    }else {arr[row-1][col] = cells.get(col);
                        // Executes when the Boolean expression is false
                    }

                }

            }

            //System.out.println(arr[5][0]);
            //System.out.println(arr[5][1]);
            //return arr;
        }catch(ParseException pe){

            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }
//        System.out.println(getHeader()[1]);
        //       System.out.println(getArr()[5][0]);
    }
}