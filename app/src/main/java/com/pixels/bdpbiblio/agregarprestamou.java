package com.pixels.bdpbiblio;

import android.support.v7.app.*;
import android.os.*;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.content.IntentSender;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.DialogInterface;import android.content.Intent;import android.support.design.widget.NavigationView;import android.support.v4.view.GravityCompat;import android.support.v4.widget.DrawerLayout;import android.support.v7.app.ActionBarDrawerToggle;import android.support.v7.app.AlertDialog;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.support.v7.widget.Toolbar;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.widget.TextView;import android.widget.Toast; import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.VolleyError;import com.android.volley.toolbox.JsonArrayRequest;import com.android.volley.toolbox.Volley; import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject; import java.util.ArrayList;import java.util.List;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.ArrayList;
import com.android.volley.toolbox.JsonArrayRequest;

public class agregarprestamou extends AppCompatActivity implements View.OnClickListener {
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private static final String BARRA = "/";
	List<nombrel> vs = new ArrayList<>();
	public final Calendar c = Calendar.getInstance();

    //Fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    //Widgets
    EditText etFecha, etHora;
	
    ImageButton ibObtenerFecha, ibObtenerHora;
	private Spinner mSpinner;
	String codig;
	public String [] vec= new String[3];
   
   
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agcu);
		Bundle extra = getIntent().getExtras();
		codig=extra.getString("codigo");
		etFecha = (EditText) findViewById(R.id.et_mostrar_fecha_picker);
		ibObtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);
		ibObtenerFecha.setOnClickListener(this);
		ip i=new ip();
		String ip=i.ip();
		String Url="http://"+ip+"/busuario.php?codigo="+codig;
		//Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

				@Override
				public void onResponse(JSONArray response) {
					JSONObject jo = null;
					for (int i = 0; i < response.length(); i++) {
						try {
							jo = response.getJSONObject(i);
							((TextView) findViewById(R.id.cu)).setText(jo.getString("codigo"));
							((TextView) findViewById(R.id.un)).setText(jo.getString("nombres"));
							((TextView) findViewById(R.id.ua)).setText(jo.getString("apellidos"));
							((TextView) findViewById(R.id.tl)).setText(jo.getString("telefono"));
							((TextView) findViewById(R.id.tu)).setText(jo.getString("tipo_u"));
							
						} catch (JSONException e) {
							Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

						}
					}

					




				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					new android.os.Handler().postDelayed(new Runnable() {


							@Override
							public void run() {
								Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();
								finish();
							}},2000);
				}
			});
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
		
		
		
		
		
		
		
		
		
	}
	@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_obtener_fecha:
                obtenerFecha();
                break;
        }
    }
	private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

					final int mesActual = month + 1;

					String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
					String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

					etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


				}
			},anio, mes, dia);

        recogerFecha.show();

    }
	
	public void onclic(View view){
		
		ip i=new ip();
		String ip=i.ip();
		String Url="http://"+ip+"/nlibros.php";
		//Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();
		for(int bi=0;bi<vec.length;bi++){
			vec[bi]=null;
		}

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

				@Override
				public void onResponse(JSONArray response) {
					JSONObject jo = null;
					for (int i = 0; i < response.length(); i++) {
						try {
							jo = response.getJSONObject(i);
							vs.add(new nombrel(jo.getString("titulo")));
						} catch (JSONException e) {
							Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

						}
					}

					final String [] listItems = new String [vs.size()];
					for(int i=0;i<vs.size();i++){
						listItems[i]=vs.get(i).getNombreL();
					}
					final boolean[] checkedItems;
					final ArrayList<Integer> mUserItems = new ArrayList<>();
					checkedItems = new boolean[listItems.length];
					AlertDialog.Builder mBuilder = new AlertDialog.Builder(agregarprestamou.this);
					mBuilder.setTitle("Libros");
					mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
// if (isChecked) {
// if (!mUserItems.contains(position)) {
// mUserItems.add(position);
// }
// } else if (mUserItems.contains(position)) {
// mUserItems.remove(position);
// }
								if(isChecked){
									mUserItems.add(position);
								}else{
									mUserItems.remove((Integer.valueOf(position)));
								}
							}
						});

					mBuilder.setCancelable(false);
					mBuilder.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int which) {
								String item = "";
								
								int n=0;
								for (int i = 0; i < mUserItems.size(); i++) {
									item = item + listItems[mUserItems.get(i)];
									if (i != mUserItems.size() - 1) {
										vec[n]=item;
										item ="";
										n++;
									}
								}
								vec[n]=item;
								if(mUserItems.size()> 3){
									Toast.makeText(getApplicationContext(), "No puede ser mas de tres libros",Toast.LENGTH_LONG).show();
								}else{
									item="";
									
									for(int i=0;i<vec.length;i++){
										item=item+"Libro #"+(i+1)+": "+vec[i]+"\n";
									}
									Toast.makeText(getApplicationContext(), item,Toast.LENGTH_LONG).show();
									TextView lib=(TextView)findViewById(R.id.Libross);
									lib.setText(item);
								}//mItemSelected.setText(item);
							}
						});

					//mBuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
					//	@Override
					//public void onClick(DialogInterface dialogInterface, int i) {
					//		dialogInterface.dismiss();
					//	}
					//	});

					mBuilder.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int which) {
								for (int i = 0; i < checkedItems.length; i++) {
									checkedItems[i] = false;
									mUserItems.clear();
									//Toast.makeText(getApplicationContext(), "",Toast.LENGTH_LONG).show();
									
								}
							}
						});

					AlertDialog mDialog = mBuilder.create();
					mDialog.show();

					



				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					new android.os.Handler().postDelayed(new Runnable() {


							@Override
							public void run() {
								Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();
								finish();
							}},2000);
				}
			});
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
		
	}
	public void onlic(View view){
		String nn="";
		
		//Toast.makeText(getApplicationContext(), nn,Toast.LENGTH_LONG).show();
		
	}
	
	

}

