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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment2 extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
   // private DummyContent.DummyItem mItem;
    private String presN;
    List<multaad> vs = new ArrayList<>();
    

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment2() {
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
				//Toast.makeText(getActivity(), mItem.content+"", Toast.LENGTH_LONG).show();
                appBarLayout.setTitle("Multa # "+presN);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.item_detail2, container, false);
		final TextView idm=(TextView) rootView.findViewById(R.id.item_detail2);
		final TextView id_d=(TextView) rootView.findViewById(R.id.dev);
		final Button boton=(Button)rootView.findViewById(R.id.dd);
		

        // Show the dummy content as text in a TextView.
        
        
            ip i=new ip();
            String ip=i.ip();
            String Url="http://"+ip+"/vmultasf.php";
            //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


            JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo = null;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                jo = response.getJSONObject(i);
                                vs.add(new multaad(jo.getString("idm"), jo.getString("fecha_p"),jo.getString("fecha_d"),jo.getString("id_d"), jo.getString("vmulta"), jo.getString("multap"),jo.getString("idp"), jo.getString("codigo"), jo.getString("nombres"), jo.getString("apellidos"), jo.getString("tipo_u"), jo.getString("codigol"), jo.getString("titulo"), jo.getString("valorl"), jo.getString("tipo_coleccion") ));
                                
                            } catch (JSONException e) {
                              //  Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

                            }
                        }
                        int n=0;
                        for (int i=0;i<vs.size();i++){
                            if(vs.get(i).getIdm().equals(presN)){

                                n=i;
                            }

                        }
                        //Toast.makeText(getActivity(), presN, Toast.LENGTH_LONG).show();

                        idm.setText(vs.get(n).getIdm());
                        ((TextView) rootView.findViewById(R.id.ltl)).setText(vs.get(n).getTitulo());
                        ((TextView) rootView.findViewById(R.id.cdl)).setText(vs.get(n).getCodigoL());
                        ((TextView) rootView.findViewById(R.id.vl)).setText(vs.get(n).getValorL());
                        ((TextView) rootView.findViewById(R.id.clcl)).setText(vs.get(n).getTipoC());

                        ((TextView) rootView.findViewById(R.id.cu)).setText(vs.get(n).getCodigoU());
                        ((TextView) rootView.findViewById(R.id.un)).setText(vs.get(n).getNombres());
                        ((TextView) rootView.findViewById(R.id.ua)).setText(vs.get(n).getApellidos());
                        ((TextView) rootView.findViewById(R.id.tu)).setText(vs.get(n).getTipoU());
						((TextView) rootView.findViewById(R.id.pre)).setText(vs.get(n).getIdp());
						((TextView) rootView.findViewById(R.id.fap)).setText(vs.get(n).getFechap());
						id_d.setText(vs.get(n).getId_d());
						((TextView) rootView.findViewById(R.id.fad)).setText(" "+vs.get(n).getFechad());
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date fechaInicial = null;

						try
						{
							fechaInicial = dateFormat.parse(vs.get(n).getFechap());
						}
						catch (ParseException e)
						{}
						Date fechaFinal = null;
						try
						{
							fechaFinal = dateFormat.parse(vs.get(n).getFechad());
						}
						catch (ParseException e)
						{}	
						int dias=(int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
						if(vs.get(n).getTipoC().equals("sala"))
						{
							dias=dias-10;
						}else{
							dias=dias-3;
						}
						((TextView) rootView.findViewById(R.id.dd1)).setText(""+dias);
						
						String tt="";
						if(vs.get(n).getVmulta().equals("0")){
							tt="No Tiene multa";
						}
						else{
							tt="Valor de la Multa: "+vs.get(n).getVmulta()+"\nEstado de la Muta: ";
							if(vs.get(n).getMultap().equals("no")){
								tt=tt+"No Pagada";
							}
							else{
								tt=tt+"Pagada";	
								boton.setVisibility(View.INVISIBLE);
							}
						}
						((TextView) rootView.findViewById(R.id.multa)).setText(tt);

                       
                        




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
			
			
			//boton
			
		boton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					ip i=new ip();
					String ip=i.ip();
					String Url="http://"+ip+"/updatem.php?codigo="+idm.getText().toString();
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
											//Toast.makeText(getActivity(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

										}},2000);
							}
						});
					RequestQueue requestQueue;
					requestQueue= Volley.newRequestQueue(getActivity());
					requestQueue.add(jsonArrayRequest);
					
					String Url1="http://"+ip+"/updated.php?codigo="+id_d.getText().toString();
					JsonArrayRequest jsonArrayRequest1=new JsonArrayRequest(Url1, new Response.Listener<JSONArray>() {

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
											//Toast.makeText(getActivity(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

										}},2000);
							}
						});
					RequestQueue requestQueue1;
					requestQueue1= Volley.newRequestQueue(getActivity());
					requestQueue1.add(jsonArrayRequest1);
					
					
					((TextView) rootView.findViewById(R.id.multa)).setText("Pagada");
					
					boton.setVisibility(View.INVISIBLE);
					
				}
				
				
			});
        

			
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
			
        return rootView;
    }
    
    public void idpp(String ii){
        presN=ii;
	}
}
