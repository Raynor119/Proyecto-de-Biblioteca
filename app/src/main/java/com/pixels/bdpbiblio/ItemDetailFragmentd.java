package com.pixels.bdpbiblio;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.DialogInterface;import android.content.Intent;import android.support.design.widget.NavigationView;import android.support.v4.view.GravityCompat;import android.support.v4.widget.DrawerLayout;import android.support.v7.app.ActionBarDrawerToggle;import android.support.v7.app.AlertDialog;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.support.v7.widget.Toolbar;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.widget.TextView;import android.widget.Toast; import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.VolleyError;import com.android.volley.toolbox.JsonArrayRequest;import com.android.volley.toolbox.Volley; import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject; import java.util.ArrayList;import java.util.List;


import android.widget.*;
import android.view.View.OnClickListener;
import java.util.Calendar;
import android.content.Context;
import android.app.DatePickerDialog;
import java.text.ParseException;import java.text.SimpleDateFormat;import java.util.Date;
/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragmentd extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private static final String BARRA = "/";
    public final Calendar c = Calendar.getInstance();
    String fechad;

    //Fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    ArrayList<Integer> mUserItems ;
    //Hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    //Widgets
    EditText etFecha, etHora;

    ImageButton ibObtenerFecha, ibObtenerHora;
    List<vprestamo> vs = new ArrayList<>();
    List<fecha> vd = new ArrayList<>();
    List<idd> vdd = new ArrayList<>();
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
int dias=3;
    String presN="";
    Context nn=getActivity();
    String tipoL;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragmentd() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.


            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {

                appBarLayout.setTitle("Prestamo # "+presN);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.item_detaild, container, false);
        final TextView codi=(TextView) rootView.findViewById(R.id.item_detail);
        final TextView val=  (TextView) rootView.findViewById(R.id.vl);
        
        
        etFecha = (EditText) rootView.findViewById(R.id.et_mostrar_fecha_picker);
        ibObtenerFecha = (ImageButton) rootView.findViewById(R.id.ib_obtener_fecha);
		ibObtenerFecha.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1)
                {
                    obtenerFecha();
                }
                
            
        });

        // Show the dummy content as text in a TextView.

        //Toast.makeText(getActivity(), mItem.details+"prestamos", Toast.LENGTH_LONG).show();



        ip i=new ip();
        String ip=i.ip();
        if(presN.equals("")){
            presN=pretamod.co();
        }
        String Url="http://"+ip+"/vprestamodr.php?codigo="+presN;
       // Toast.makeText(getActivity(), presN,Toast.LENGTH_LONG).show();
       
      

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jo = null;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jo = response.getJSONObject(i);
                            vs.add(new vprestamo(jo.getString("idp"), jo.getString("fecha"), jo.getString("codigo"), jo.getString("nombres"), jo.getString("apellidos"), jo.getString("tipo_u"), jo.getString("codigol"), jo.getString("titulo"), jo.getString("valorl"), jo.getString("tipo_coleccion") ));

                        } catch (JSONException e) {
                            Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

                        }
                    }
                    int n=0;
                    for (int i=0;i<vs.size();i++){
                        if(vs.get(i).getIdp().equals(presN)){

                            n=i;
                        }

                    }
                   codi.setText(vs.get(n).getIdp());
                    ((TextView) rootView.findViewById(R.id.ltl)).setText(vs.get(n).getTitulo());
                    ((TextView) rootView.findViewById(R.id.cdl)).setText(vs.get(n).getCodigoL());
                    ((TextView) rootView.findViewById(R.id.vl)).setText(vs.get(n).getValorL());
                    val.setText(vs.get(n).getValorL());
                    ((TextView) rootView.findViewById(R.id.clcl)).setText(vs.get(n).getTipoC());
                    ((TextView) rootView.findViewById(R.id.cu)).setText(vs.get(n).getCodigoU());
                    ((TextView) rootView.findViewById(R.id.un)).setText(vs.get(n).getNombres());
                    ((TextView) rootView.findViewById(R.id.ua)).setText(vs.get(n).getApellidos());
                    ((TextView) rootView.findViewById(R.id.tu)).setText(vs.get(n).getTipoU());

                   fechad= vs.get(n).getFecha();
                   tipoL=vs.get(n).getTipoC();



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    new android.os.Handler().postDelayed(new Runnable() {


                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

                            }},2000);
                }
            });
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);

        LinearLayout l=(LinearLayout)rootView.findViewById(R.id.dialogg);
        l.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1)
                {

                    final List<libro> ll = new ArrayList<>();
                    TextView Texs =(TextView) rootView.findViewById(R.id.cdl);
                    ip i=new ip();
                    String ip=i.ip();
                    String Url="http://"+ip+"/libro.php?codigo="+Texs.getText().toString();
                    //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


                    JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                JSONObject jo = null;
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        jo = response.getJSONObject(i);
                                        ll.add(new libro(jo.getString("codigo"), jo.getString("titulo"), jo.getString("edicion"), jo.getString("ciudad"), jo.getString("anno"), jo.getString("editorial"), jo.getString("descripcion"), jo.getString("valorl"), jo.getString("nombres"), jo.getString("apellidos") ));

                                    } catch (JSONException e) {
                                        Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

                                    }
                                }
                                AlertDialog.Builder alertt= new AlertDialog.Builder(getActivity());
                                //alertt.setMessage("El Usuario ya se habia Registrado")
                                final View vie = LayoutInflater.from(getActivity()).inflate(R.layout.librodialog, null);

                                alertt.setView(vie)
                                    .setCancelable(false)
                                    .setPositiveButton("Salir", new DialogInterface.OnClickListener(){
                                        @Override
                                        public void onClick(DialogInterface dialog,int which){



                                        }       
                                    });

                                AlertDialog titulo=alertt.create();
                                titulo.setTitle("Libro "+ll.get(0).getTitulo());
                                titulo.show();
                                ((TextView) vie.findViewById(R.id.cdl)).setText(ll.get(0).getCodigo());
                                ((TextView) vie.findViewById(R.id.tiulo)).setText(ll.get(0).getTitulo());
                                ((TextView) vie.findViewById(R.id.edicion)).setText(ll.get(0).getEdicion());
                                ((TextView) vie.findViewById(R.id.ciudad)).setText(ll.get(0).getCiudad());
                                ((TextView) vie.findViewById(R.id.annon)).setText(ll.get(0).getAnno());
                                ((TextView) vie.findViewById(R.id.editorial)).setText(ll.get(0).getEditorial());
                                ((TextView) vie.findViewById(R.id.valor)).setText(ll.get(0).getValorl());
                                ((TextView) vie.findViewById(R.id.dec)).setText(ll.get(0).getDescripcion());
                                //Toast.makeText(getActivity(), ll.size()+"", Toast.LENGTH_LONG).show();
                                if(ll.size()==1){
                                    ((TextView) vie.findViewById(R.id.autores)).setText("Autor del Libro: ");
                                    ((TextView) vie.findViewById(R.id.autor)).setText(""+ll.get(0).getNombres()+" "+ll.get(0).getApellidos());

                                }else{
                                    String m= "";
                                    for(int i=0;i<ll.size();i++){
                                        m=m+ll.get(i).getNombres()+" "+ll.get(i).getApellidos()+",";
                                    }
                                    ((TextView) vie.findViewById(R.id.autores)).setText("Autores del Libro: ");
                                    ((TextView) vie.findViewById(R.id.autor)).setText(m);


                                }   








                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                new android.os.Handler().postDelayed(new Runnable() {


                                        @Override
                                        public void run() {
                                            Toast.makeText(getActivity(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

                                        }},2000);
                            }
                        });
                    RequestQueue requestQueue;
                    requestQueue= Volley.newRequestQueue(getActivity());
                    requestQueue.add(jsonArrayRequest);

                }


            });
            
            
            Button dv =(Button) rootView.findViewById(R.id.dd);
        dv.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1)
                {
                    if(etFecha.getText().toString().equals("")){
                        Toast.makeText(getActivity(), "Seleccione la Fecha de Devolucion",Toast.LENGTH_LONG).show();
                        
                    }else{
                        
                        if(tipoL.equals("sala")){
                             dias=10;
                            
                            
                            
                        }else{
                             dias=3;
                        }
                        
                        ip i=new ip();
                        String ip=i.ip();
                        String Url="http://"+ip+"/fechade.php?codigo="+codi.getText().toString()+"&dia="+dias;
                        //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


                        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                                @Override
                                public void onResponse(JSONArray response) {
                                    JSONObject jo = null;
                                    for (int i = 0; i < response.length(); i++) {
                                        try {
                                            jo = response.getJSONObject(i);
                                            vd.add(new fecha(jo.getString("fecha")));

                                        } catch (JSONException e) {
                                            Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

                                        }
                                        
                                    }   
                                    String fec="";

                                    String di="",m="",an="";

                                    char nns;
                                    String n=etFecha.getText().toString();
                                    int l=0;
                                    
                                    m=""+n.charAt(0)+n.charAt(1);
                                    di=""+n.charAt(3)+n.charAt(4);
                                    an=""+n.charAt(6)+n.charAt(7)+n.charAt(8)+n.charAt(9);
                                   
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date fechaInicial = null;
                                    
                                    try
                                    {
                                        fechaInicial = dateFormat.parse(vd.get(0).getFecha());
                                    }
                                    catch (ParseException e)
                                    {}
                                    Date fechaFinal = null;
                                    try
                                    {
                                       fechaFinal = dateFormat.parse(an+"-"+m+"-"+di);
                                    }
                                    catch (ParseException e)
                                    {}	
                                   int dias=(int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);		
                                    //"Hay "+dias+" dias de diferencia"
                                    
                                    //Toast.makeText(getActivity(),"Hay "+dias+" dias de diferencia", Toast.LENGTH_LONG).show();
                                    //Toast.makeText(getActivity(),""+l, Toast.LENGTH_LONG).show();
                                    
                                    if(dias <= 0){
                                        // no aplica multas
                                        ip i=new ip();
                                        String ip=i.ip();
                                        String Url="http://"+ip+"/insertd.php?id_p="+codi.getText()+"&fecha="+etFecha.getText().toString()+"&multa=0&d=";
                                        //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


                                        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                                                @Override
                                                public void onResponse(JSONArray response) {
                                                    JSONObject jo = null;
                                                    
                                                    
                                                    
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    new android.os.Handler().postDelayed(new Runnable() {


                                                            @Override
                                                            public void run() {
                                                                //Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

                                                            }},2000);
                                                }
                                            });
                                        RequestQueue requestQueue;
                                        requestQueue= Volley.newRequestQueue(getActivity());
                                        requestQueue.add(jsonArrayRequest);
                                        
                                        
                                        getActivity().finish();
                                        
                                        
                                    }else{
                                        
                                      final  int multaa=1000*dias;
                                        
                                        String nnn=val.getText().toString();
                                       int valorl=Integer.parseInt(nnn+"");
                                        //Toast.makeText(getActivity(), nnn,Toast.LENGTH_LONG).show();
                                        
                                       
                                        final int cintodiez=(valorl*110)/100;
                                        if(multaa>cintodiez){
                                            
                                            //si la multa es manor de 110 
                                            Toast.makeText(getActivity(), " es 110",Toast.LENGTH_LONG).show();
                                            
                                            ip i=new ip();
                                            String ip=i.ip();
                                            String Url="http://"+ip+"/insertd.php?id_p="+codi.getText()+"&fecha="+etFecha.getText().toString()+"&multa="+cintodiez+"&d=no";
                                            //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


                                            JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                                                    @Override
                                                    public void onResponse(JSONArray response) {
                                                        JSONObject jo = null;



                                                    }
                                                }, new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        new android.os.Handler().postDelayed(new Runnable() {


                                                                @Override
                                                                public void run() {
                                                                    //Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

                                                                }},2000);
                                                    }
                                                });
                                            RequestQueue requestQueue;
                                            requestQueue= Volley.newRequestQueue(getActivity());
                                            requestQueue.add(jsonArrayRequest);
                                            
                                        
                                                       
                                                        String Url1="http://"+ip+"/iddvp.php?codigo="+codi.getText();
                                                        //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


                                                        JsonArrayRequest jsonArrayRequest1=new JsonArrayRequest(Url1, new Response.Listener<JSONArray>() {

                                                                @Override
                                                                public void onResponse(JSONArray response) {
                                                                    JSONObject jo = null;
                                                                    for (int i = 0; i < response.length(); i++) {
                                                                        try {
                                                                            jo = response.getJSONObject(i);
                                                                            vdd.add(new idd(jo.getString("idd")));

                                                                        } catch (JSONException e) {
                                                                            Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

                                                                        }

                                                                    }   

                                                                    
                                                                    
                                                                    
                                                                    ip i=new ip();
                                                                    String ip=i.ip();
                                                                    String Url="http://"+ip+"/insertm.php?id_d="+vdd.get(0).getId_p()+"&vmulta="+cintodiez+"&multap=no";
                                                                    //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


                                                                    JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                                                                            @Override
                                                                            public void onResponse(JSONArray response) {
                                                                                JSONObject jo = null;



                                                                            }
                                                                        }, new Response.ErrorListener() {
                                                                            @Override
                                                                            public void onErrorResponse(VolleyError error) {
                                                                                new android.os.Handler().postDelayed(new Runnable() {


                                                                                        @Override
                                                                                        public void run() {
                                                                                            //Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

                                                                                        }},2000);
                                                                            }
                                                                        });
                                                                    RequestQueue requestQueue;
                                                                    requestQueue= Volley.newRequestQueue(getActivity());
                                                                    requestQueue.add(jsonArrayRequest);


                                                                }
                                                            }, new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {
                                                                    new android.os.Handler().postDelayed(new Runnable() {


                                                                            @Override
                                                                            public void run() {
                                                                                //Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

                                                                            }},2000);
                                                                }
                                                            });
                                                       // RequestQueue requestQueue1;
                                                        requestQueue= Volley.newRequestQueue(getActivity());
                                                        requestQueue.add(jsonArrayRequest1);
                                                        
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            getActivity().finish();
                                            
                                        }else{
                                            Toast.makeText(getActivity(), " no es 110",Toast.LENGTH_LONG).show();
                                            
                                            //sino supera el 110 porsiento
                                            
                                            ip i=new ip();
                                            String ip=i.ip();
                                            String Url="http://"+ip+"/insertd.php?id_p="+codi.getText()+"&fecha="+etFecha.getText().toString()+"&multa="+multaa+"&d=no";
                                            //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


                                            JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                                                    @Override
                                                    public void onResponse(JSONArray response) {
                                                        JSONObject jo = null;



                                                    }
                                                }, new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        new android.os.Handler().postDelayed(new Runnable() {


                                                                @Override
                                                                public void run() {
                                                                    //Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

                                                                }},2000);
                                                    }
                                                });
                                            RequestQueue requestQueue;
                                            requestQueue= Volley.newRequestQueue(getActivity());
                                            requestQueue.add(jsonArrayRequest);
                                          
                                                        
                                                        String Url1="http://"+ip+"/iddvp.php?codigo="+codi.getText();
                                                        //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


                                                        JsonArrayRequest jsonArrayRequest2=new JsonArrayRequest(Url1, new Response.Listener<JSONArray>() {

                                                                @Override
                                                                public void onResponse(JSONArray response) {
                                                                    JSONObject jo = null;
                                                                    for (int i = 0; i < response.length(); i++) {
                                                                        try {
                                                                            jo = response.getJSONObject(i);
                                                                            vdd.add(new idd(jo.getString("idd")));

                                                                        } catch (JSONException e) {
                                                                            Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

                                                                        }

                                                                    }   




                                                                    ip i=new ip();
                                                                    String ip=i.ip();
                                                                    String Url="http://"+ip+"/insertm.php?id_d="+vdd.get(0).getId_p()+"&vmulta="+multaa+"&multap=no";
                                                                    //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


                                                                    JsonArrayRequest jsonArrayRequest1=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                                                                            @Override
                                                                            public void onResponse(JSONArray response) {
                                                                                JSONObject jo = null;



                                                                            }
                                                                        }, new Response.ErrorListener() {
                                                                            @Override
                                                                            public void onErrorResponse(VolleyError error) {
                                                                                new android.os.Handler().postDelayed(new Runnable() {


                                                                                        @Override
                                                                                        public void run() {
                                                                                            //Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

                                                                                        }},2000);
                                                                            }
                                                                        });
                                                                    RequestQueue requestQueue2;
                                                                    requestQueue2= Volley.newRequestQueue(getActivity());
                                                                    requestQueue2.add(jsonArrayRequest1);


                                                                }
                                                            }, new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {
                                                                    new android.os.Handler().postDelayed(new Runnable() {


                                                                            @Override
                                                                            public void run() {
                                                                                //Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

                                                                            }},2000);
                                                                }
                                                            });
                                                     //   RequestQueue requestQueue1;
                                                        requestQueue= Volley.newRequestQueue(getActivity());
                                                        requestQueue.add(jsonArrayRequest2);

                                                

                                            
                                            getActivity().finish();
                                        }
                                        
                                        
                                        
                                        
                                    }
                                    
                        
                                    
                                    
                                    
                                    
                                 }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    new android.os.Handler().postDelayed(new Runnable() {


                                            @Override
                                            public void run() {
                                                //Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

                                            }},2000);
                                }
                            });
                        RequestQueue requestQueue;
                        requestQueue= Volley.newRequestQueue(getActivity());
                        requestQueue.add(jsonArrayRequest);
                        
                                    
                        
                    //Toast.makeText(getActivity(), codi.getText().toString()+" fecha: "+fechad+"  tipo c:"+tipoL,Toast.LENGTH_LONG).show();
                    }
                   
                    
                }
                
                
                
            });
            

        return rootView;
    }
   
  
   
    public void idpp(String ii){
        presN=ii;
    }
    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    final int mesActual = month + 1;

                    String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                    String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

                    etFecha.setText(mesFormateado + BARRA + diaFormateado + BARRA + year);


                }
            },anio, mes, dia);

        recogerFecha.show();

    }
	


}
