package stu.edu.vn.cuoikyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

import stu.edu.vn.cuoikyandroid.adapters.HangHoaAdapter;
import stu.edu.vn.cuoikyandroid.store.HangHoa;

public class MainActivity2 extends AppCompatActivity {

    EditText edtSearch;
    Button btnSearch;
    ListView lv;
    HangHoaAdapter adapter;
    ArrayList<HangHoa> dshh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        lv = findViewById(R.id.lv);
        dshh = new ArrayList<>();
        adapter = new HangHoaAdapter(this, R.layout.hanghoa, dshh);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetHangHoa();
            }
        });
    }

    public void GetHangHoa(){
        String URL = "http://172.17.9.177/api/gethanghoa.php";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);

        Uri.Builder builder = Uri.parse(URL).buildUpon();
        builder.appendQueryParameter("loaihanghoa", edtSearch.getText().toString());
        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            dshh.clear();
                            JSONArray jsonArray = new JSONArray(response);
                            Toast.makeText(MainActivity2.this, jsonArray.toString(), Toast.LENGTH_SHORT).show();
                            int length = jsonArray.length();
                            for (int i = 0; i < length; i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                HangHoa hh = new HangHoa(Integer.parseInt(object.getString("ma")), Integer.parseInt(object.getString("gia")), object.getString("ten"));
                                dshh.add(hh);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (Exception error) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity2.this, "INTERNAL SERVER ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        requestQueue.add(request);
    }
}