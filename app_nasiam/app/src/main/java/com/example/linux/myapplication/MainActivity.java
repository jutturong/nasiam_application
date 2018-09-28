package com.example.linux.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity  extends  TabActivity {


   // public static String ip="http://10.87.196.113/";
   // http://kkucleft.kku.ac.th/json2/selProvince.php
   //  public static String ip="http://kkucleft.kku.ac.th/";

  //  public static String ip_main_system="http://10.87.196.170/app_admin/";
    //https://kkucleft.kku.ac.th/app_admin/
    public static String ip_main_system="https://kkucleft.kku.ac.th/app_admin/";

    //check  desktop admin  =>https://kkucleft.kku.ac.th/app_admin/
    // user:jutturong
    // ps: 123456
    // db=cleft2
    // tb= `tb_userandroid`
    //
    /*
          txtUser.setText("jutturong"); //sim login
          txtPass.setText("ju12345"); //sim login
     */

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
   // private EditText  edtDate;
    private int year, month, day;

    private Spinner spin1,spinner3,spinner1,spinner2;


    public   String[] iplTeam;

    //String url = "http://10.87.196.113/json2/selProvince.php";
  //  String url =   ip  +  "json2/selProvince.php";

    InputStream is=null;

    String result=null;
    String line=null;

    String Id_per;
    Intent intent;

  //  int id_weight;
    public String[]  arr_prov_id=new String[1000];


    EditText name;
    EditText lastname;
    EditText id_card;
    EditText  telephone;
    RadioGroup  id_sex;
    RadioButton m_sex,w_sex;
    TextView   birthdate;
    EditText   address;
  //  AutoCompleteTextView   province_id;
    EditText   diagnosis;

    //insert  value
    String  strname;
    String  strlastname;
    String   strid_card;
    String strtelephone;




    String  val_sex;
    String  str_m_sex; //1=เพศชาย
    String   str_w_sex; //2=เพศหญิง


    String  str_id_sex1;
    String strbirthdate;
    String  straddress;
    String  strprovince_id;
    String   strdiagnosis;





    public   String[]  arrProv=new String[1000];

     public   String[]   allprovince=new String[1000];

    int code;
    String  testjson;

    String  stauts_insert = "บันทึกสำเร็จ";


    /* progress bar */
    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();


    //public  String urlinsert="http://10.87.196.113/json2/insertPatient.php";
   //  public  String urlinsert=  ip +  "json2/insertPatient.php";
   // public  String urlinsert="http://kkucleft.kku.ac.th/app_admin/index.php/welcome/insertPatient";


   //http://10.87.196.170/app_admin/index.php/welcome/checklogin

//ip_main_system
  //  public  String urlinsert="http://10.87.196.170/app_admin/index.php/welcome/insertPatient2";
public  String urlinsert=  ip_main_system  + "index.php/welcome/insertPatient2";


    //---- Autocomplete  PROVINCE จัวงหว
    private AutoCompleteTextView autocomplete1;
    private  List<String> arrList = new ArrayList<String>();
    private  ArrayAdapter<String> adapter;

    //----- spinner  text ---
    String[] arr = { "---เลือก---","ปากแหว่ง", "เพดานโหว่", "ปากแหว่งและเพดานโหว่", "อื่นๆ" };


    //tabbar
    TabHost mTabHost;

    //เพิ่ม field
    String  strdetail_diagnosis;  //ระบุการวินิจฉัยโรค อื่นๆ
    EditText detail_diagnosis;

    String  strinformative_name;  //ชื่อผู้ให้ ข้อมูล
    EditText informative_name;

    String  strinformative_lastname; //นามสกุลผู้ให้ข้อมูล
    EditText informative_lastname;

    EditText informative_tel; //เบอร์โทรศัพท์ผู้ให้ข้อมูล
    String  strinformative_tel;

    ImageButton img1; //image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);  //Main program OK
       // setContentView(R.layout.loginpage);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //fix ด้านตั้ง


        intent=getIntent();
       // Id_per=intent.getStringExtra("Id_per");

/*    //check id_per from  table intent
        final AlertDialog.Builder adb=new AlertDialog.Builder(this);
        AlertDialog ad=adb.create();
        ad.setMessage(Id_per);
        ad.show();
 */

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

         mTabHost = (TabHost) findViewById(android.R.id.tabhost);
         mTabHost = getTabHost();
         mTabHost = getTabHost();
         mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Page 1").setContent(R.id.tab1));
         mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Page 2").setContent(R.id.tab2));
        // mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("TAB 3").setContent(R.id.tab3));

        dateView = (TextView) findViewById(R.id.birthdate);
       // edtDate = (EditText) findViewById(R.id.edtDate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR) ;
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);


        //String url = "http://10.87.196.113/json2/selProvince.php";
        //callJSON(); //CALL JSON MYSQL SERVER  for  spinner





        name=(EditText)findViewById(R.id.name);
        lastname=(EditText)findViewById(R.id.lastname);
        id_card=(EditText)findViewById(R.id.id_card);
        telephone=(EditText)findViewById(R.id.telephone);

/*

        id_sex=(RadioGroup)findViewById(R.id.id_sex);
        m_sex=(RadioButton)findViewById(R.id.id_sex1);
        w_sex=(RadioButton)findViewById(R.id.id_sex2);

*/

        spinner2=(Spinner)findViewById(R.id.spinner2); //เลิอกเพศ

        birthdate=(TextView)findViewById(R.id.birthdate);
        address=(EditText)findViewById(R.id.address);
       // province_id=(Spinner)findViewById(R.id.province_id);

         //เลิอกจังหวัด
        autoProvince();  //call  autocomplete province
       // autocomplete1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, arrList);

      //  autocomplete1.setAdapter(adapter);

     //    province_id=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);



        // diagnosis=(EditText)findViewById(R.id.diagnosis);
       // diagnosis=(Spinner)findViewById(R.id.spinner1);
         //เลือกจังหวัด
        spinner3=(Spinner)findViewById(R.id.spinner3); //เลิอกเพศ
        spinner3.setAdapter(adapter);




        spinner1=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> arrAd=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,arr);
        arrAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrAd);

        //ระบุการวินิจฉัยโรค อื่นๆ
        detail_diagnosis=(EditText)findViewById(R.id.detail_diagnosis);
       // detail_diagnosis.setEnabled(false);
       // strdetail_diagnosis=detail_diagnosis.getText().toString();



        //ชื่อผู้ให้ ข้อมูล
        informative_name=(EditText)findViewById(R.id.informative_name);
      //  informative_name.setText("วิชิต");
       // strinformative_name=informative_name.getText().toString();

        ////นามสกุลผู้ให้ข้อมูล
        informative_lastname=(EditText)findViewById(R.id.informative_lastname);
      //  informative_lastname.setText("ศรีเชียง");
       // strinformative_lastname=informative_lastname.getText().toString();


        //เบอร์โทรศัพท์ผู้ให้ข้อมูล
        informative_tel=(EditText)findViewById(R.id.informative_tel);
       // informative_tel.setText("0858539042");
       // strinformative_tel=informative_tel.getText().toString();


        //testing insert field  ทดสอบข้อมูล
        name.setText("กานดา");
        lastname.setText("บุญประครอง");
        id_card.setText("362510478524");
        telephone.setText("0855241258");
         address.setText("857/74 ถ.ชาตุผดุง ต.ในเมือง อ.เมือง");
     //   diagnosis.setText("เพดานโหว่");
        detail_diagnosis.setText("มีภาวะอื่นร่วมด้วย");
        informative_name.setText("จัตุรงค์");
        informative_lastname.setText("เจริญฤทธิ์");
        informative_tel.setText("0868539079");


      //insert  to table


        /*  //disable  spinner
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner1.getSelectedItem().toString() == "อื่นๆ") {
                    detail_diagnosis.setEnabled(true);
                    detail_diagnosis.setText("เป็นนอกเหนือจากที่ระบุ");
                    strdetail_diagnosis=detail_diagnosis.getText().toString();
                } else {
                    detail_diagnosis.setText("");
                    detail_diagnosis.setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
*/





        Button btn_save=(Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strname = name.getText().toString();


                strlastname = lastname.getText().toString();
                strid_card = id_card.getText().toString();
                strtelephone = telephone.getText().toString();

            //    val_sex = ((RadioButton) findViewById(id_sex.getCheckedRadioButtonId())).getText().toString(); //เพศ
            //    str_m_sex=m_sex.toString();


                strbirthdate = birthdate.getText().toString();
                straddress = address.getText().toString();
                //   strprovince_id = province_id.getSelectedItem().toString();
              //  strprovince_id = province_id.getText().toString();
                //  strdiagnosis = diagnosis.getText().toString();
                strdiagnosis = spinner1.getSelectedItem().toString();
                // testalert("testing to click!!");

                strdetail_diagnosis=detail_diagnosis.getText().toString();

                strinformative_name=informative_name.getText().toString();

                strinformative_lastname=informative_lastname.getText().toString();

                strinformative_tel=informative_tel.getText().toString();


                //insertPatient();

                insertPatient(v);




            }
        });

    }




   private void insertPatient(View v)
   {

       ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
       nameValuePairs.add(new BasicNameValuePair("name", strname));
       nameValuePairs.add(new BasicNameValuePair("lastname", strlastname));
       nameValuePairs.add(new BasicNameValuePair("id_card", strid_card));
       nameValuePairs.add(new BasicNameValuePair("telephone", strtelephone));
       nameValuePairs.add(new BasicNameValuePair("id_sex", String.valueOf(spinner2.getSelectedItem()) ));
       nameValuePairs.add(new BasicNameValuePair("birthdate", strbirthdate ));
       nameValuePairs.add(new BasicNameValuePair("address", straddress ));
       nameValuePairs.add(new BasicNameValuePair("province_id", String.valueOf(spinner3.getSelectedItem()) ));
           //ภาวะโรคร่วม
       nameValuePairs.add(new BasicNameValuePair("diagnosis", String.valueOf(spinner1.getSelectedItem()) ));
       //spinner1
       nameValuePairs.add(new BasicNameValuePair("detail_diagnosis", strdetail_diagnosis ));

       //    "info_name"=>$informative_name,
       nameValuePairs.add(new BasicNameValuePair("info_name", strinformative_name ));

       //informative_lastname
       nameValuePairs.add(new BasicNameValuePair("informative_lastname", strinformative_lastname ));

       nameValuePairs.add(new BasicNameValuePair("informative_tel", strinformative_tel ));
//informative_tel


       try{
           HttpClient httpclient = new DefaultHttpClient();
           //HttpPost httppost = new HttpPost(url);
           //ip_main_system
         //  HttpPost httppost = new HttpPost("http://10.87.196.170/app_admin/index.php/welcome/insertPatient2");
           HttpPost httppost = new HttpPost(  ip_main_system +"index.php/welcome/insertPatient2");

           // httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
           HttpResponse response = httpclient.execute(httppost);
           HttpEntity entity = response.getEntity();
           is = entity.getContent();
           Log.e("pass 1", "connection success ");

       }catch (Exception e)
       {
           Log.e("Fail 1",e.toString());
           Toast.makeText(getApplicationContext(),"Invalid IP",Toast.LENGTH_LONG);
       }

       try{
           BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
           StringBuilder sb = new StringBuilder();
           while ((line = reader.readLine()) != null)
           {
               sb.append(line + "\n");
           }
           is.close();
           result = sb.toString();
           Log.e("pass 2", "connection success ");
       }catch (Exception e)
       {
           Log.e("Fail 2",e.toString());

       }

       try{

           JSONObject json_data = new JSONObject(result);

            /*


             $data=array(
               "name"=>$name,
              "lastname"=>$lastname,
              "id_card"=>$id_card,
            "telephone"=>$telephone,
                "id_sex"=>  $id_va_sex,
                   "birthdate"=>$conv_dmy,
                   "address"=>$address,
                   "province_id"=> $id_prov,
                   "diagnosis"=>$diagnosis,
                  "detail_diagnosis"=>$detail_diagnosis,
                   "info_name"=>$informative_name,
                      "informative_lastname"=>$informative_lastname,

                    "informative_tel"=>$informative_tel,

          );



            // echo json_encode(array("name"=>$name));
            //  echo  json_encode($data);



           String  ck_name=json_data.getString("name");
           String   ck_lastname=json_data.getString("lastname");
           String   ck_id_card=json_data.getString("id_card");
           String   ck_telephone=json_data.getString("telephone");
           String   ck_id_sex=json_data.getString("id_sex");
           String   ck_birthdate=json_data.getString("birthdate");
           String   ck_address=json_data.getString("address");
           String   ck_province_id=json_data.getString("province_id");
           String   ck_diagnosis=json_data.getString("diagnosis");
           String   ck_detail_diagnosis=json_data.getString("detail_diagnosis");
           String   ck_informative_name=json_data.getString("info_name");
           String   ck_informative_lastname=json_data.getString("informative_lastname");
           String   ck_strinformative_tel=json_data.getString("informative_tel");

           Toast.makeText(  getBaseContext(),  ck_name + '/' + ck_lastname  + '/' +  ck_id_card + '/' + ck_telephone + '/' + ck_id_sex  +  '/' +  ck_birthdate + '/' + ck_address + '/' + ck_province_id + '/' + ck_diagnosis + '/' + ck_detail_diagnosis + '/' + ck_informative_name + '/' + ck_informative_lastname + '/' +  ck_strinformative_tel ,Toast.LENGTH_SHORT).show();

           */

            // echo json_encode(array("success"=>1));

           //String  success  =   json_data.getString("success");
          // String  success  =   json_data.getString("success");
           Integer success = json_data.getInt("success");
          // Integer  int1 =

          // Toast.makeText(  getBaseContext(),  success.toString() ,Toast.LENGTH_SHORT).show();


           if( success == 1 )
           {
               progressBar(v); //แสดงสถานะการบันทึก
              // Toast.makeText(  getBaseContext(),  "บันทึกข้อมูลแล้ว" ,Toast.LENGTH_SHORT).show();

           }
           else
           {
               Toast.makeText(  getBaseContext(),  "ไม่สามารถบันทึกข้อมูลได้" ,Toast.LENGTH_SHORT).show();
           }



       }catch (Exception e)
       {

           Log.e("Fail 3",e.toString());
       }


   }


 public void progressBar(View v)
 {
     progressBar =new ProgressDialog(v.getContext());
     progressBar.setCancelable(true);
     progressBar.setMessage("กำลังดำเนินการบันทึกข้อมูล...");
     progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
     progressBar.setProgress(0);
     progressBar.setMax(100);
     progressBar.show();

        progressBarStatus = 0;
     new Thread(new Runnable() {
         public void run() {
             while (progressBarStatus < 100) {

                 // process some tasks
                 progressBarStatus = DoWork();

                 try {
                     Thread.sleep(15); //ยิ่งตัวเลขน้อยยิ่งเร็ว
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }

                 // Update the progress bar
                 progressBarHandler.post(new Runnable() {
                     public void run() {
                         progressBar.setProgress(progressBarStatus);
                        // Toast.makeText(  getBaseContext(),  "บันทึกข้อมูลแล้ว" ,Toast.LENGTH_SHORT).show();
                     }
                 });
             }

         }
     }).start();


 }


    // DoWork & Set Status Progress Bar
    public int DoWork() {

        // Do some work EG: Save , Download , Insert , ..
        // **** Work
        // **** Work
        // **** Work
        // **** Work
        progressBarStatus++; // Work process and return status

        if( progressBarStatus < 100)
        {
            return progressBarStatus;
        }

        // When Finish
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progressBar.dismiss();

        return 100;

    }






    public void testalert(String str)
    {
         final AlertDialog.Builder adb=new AlertDialog.Builder(this);
        AlertDialog ad=adb.create();
        ad.setMessage(str);
        ad.show();
    }


    public void  autoProvince() //ใช้เรียกจังหวัด
    {
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        try{
            //String url = "http://10.87.196.113/json2/selProvince.php";
            //ip_main_system

          //  String url="http://10.87.196.170/app_admin/index.php/welcome/json_province_backend";
            String url= ip_main_system + "index.php/welcome/json_province_backend";

            JSONArray data = new  JSONArray(getHttpPost(url,params));  //post value in table
            for(int i=0;i<data.length();i++)
            {
                JSONObject c = data.getJSONObject(i);
                // id_weight=c.getInt("id_weight");
                arr_prov_id[i]=c.getString("PROVINCE_ID");
                arrProv[i]=c.getString("PROVINCE_NAME");

                allprovince[i]=arr_prov_id[i]+ "-" + arrProv[i];

                arrList.add(   arrProv[i] + "-" + arr_prov_id[i] );
                //arrList.add(   arrProv[i]  );
            }

            /*
            Log.e("auto complete province","");

            final AlertDialog.Builder adb=new AlertDialog.Builder(this);
            AlertDialog ad=adb.create();
            ad.setMessage("autocomplete province " + arrProv );
            ad.show();
            */

        }catch (JSONException e) {
            // e.printStackTrace();
            Log.e("false autocomplete", e.toString());

        }

    }



     public void callJSON() //autocomplete  เรียกจังหว้ด
     {
         List<NameValuePair> params=new ArrayList<NameValuePair>();
         try{


             //String url = "http://10.87.196.113/json2/selProvince.php";
            // String url ="http://10.87.196.170/app_admin/index.php/welcome/json_province_backend";

             //ip_main_system
             String url = ip_main_system + "index.php/welcome/json_province_backend";

             JSONArray data = new  JSONArray(getHttpPost(url,params));  //post value in table
             for(int i=0;i<data.length();i++)
             {
                 JSONObject c = data.getJSONObject(i);
                // id_weight=c.getInt("id_weight");
                  arr_prov_id[i]=c.getString("PROVINCE_ID");
                  arrProv[i]=c.getString("PROVINCE_NAME");
                 // allprovince[i]=arr_prov_id[i]+ "-" + arrProv[i];
                 allprovince[i]=arr_prov_id[i];
             }
           //  String[] iplTeam= {"KKR", "CSK", "RR", "KXIP", "RR", "MI" };
          //   spin1=(Spinner) findViewById(R.id.province_id); //OK
            // ArrayAdapter adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,  allprovince  );
             ArrayAdapter adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,  arrProv );
             adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
             spin1.setAdapter(adapter);


         }catch (JSONException e)
         {
             // e.printStackTrace();
             Log.e("try fail 1",e.toString());
             final AlertDialog.Builder adb=new AlertDialog.Builder(this);
             AlertDialog ad=adb.create();
             ad.setMessage("fail connect  SERVER !!");
             ad.show();
         }
     }






    public String getJSONUrl(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Download OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }



    public String getHttpPost(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();




        } catch (IOException e) {
            e.printStackTrace();




        }
        return str.toString();
    }






    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year  , month, day);
        }
        return null;
    }



    private DatePickerDialog.OnDateSetListener myDateListener
            = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1  , arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {


        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year+543));


        //edtDate.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));

    }



    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        //Toast.makeText(getApplicationContext(), " เลือกวันที่  ", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);



        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
