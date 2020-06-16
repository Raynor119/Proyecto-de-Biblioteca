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
import android.transition.Transition;
import android.view.animation.LayoutAnimationController;
import android.view.animation.AnimationUtils;
import android.support.annotation.Size;
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
	int diasala;

    ImageButton ibObtenerFecha, ibObtenerHora;
	List<libro> ll;
    List<vprestamo> vs = new ArrayList<>();
    List<fecha> vd = new ArrayList<>();
    List<idd> vdd = new ArrayList<>();
	String [] fech= new String [6];
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

                appBarLayout.setTitle("Prestamso # "+presN);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.item_detaild, container, false);
        final TextView codi=(TextView) rootView.findViewById(R.id.item_detail);
       // final TextView val=  (TextView) rootView.findViewById(R.id.vl);
        final RecyclerView recyclerView1 = rootView.findViewById(R.id.item_list);
        assert recyclerView1 != null;
        
        etFecha = (EditText) rootView.findViewById(R.id.et_mostrar_fecha_picker);
        ibObtenerFecha = (ImageButton) rootView.findViewById(R.id.ib_obtener_fecha);
		ibObtenerFecha.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1)
                {
					ip i=new ip();
					String ip=i.ip();
					
					
					
                    obtenerFecha();
                }
                
            
        });

        // Show the dummy content as text in a TextView.

        //Toast.makeText(getActivity(), mItem.details+"prestamos", Toast.LENGTH_LONG).show();

		if(presN.equals("")){ 
		presN=pretamod.co(); 
		}
		
		ip i=new ip();
		String ip=i.ip();
		String Url="http://"+ip+"/vprestamodr.php?codigo="+presN;
		//Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

				@Override
				public void onResponse(JSONArray response) {
					JSONObject jo = null;
					int cont=0;
					for (int i = 0; i < response.length(); i++) {
						try {
							int n=0;

							String kk;
							jo = response.getJSONObject(i);
							vs.add(new vprestamo(jo.getString("idp"), jo.getString("fecha"), jo.getString("codigo"), jo.getString("nombres"), jo.getString("apellidos"), jo.getString("tipo_u"), jo.getString("codigol"), jo.getString("titulo"), jo.getString("valorl"), jo.getString("tipo_coleccion") ));
							kk=vs.get(cont).getIdp();
							if(vs.get(cont).getIdp().equals(presN)||presN.equals("")){
								if(presN.equals("")){
									presN=kk;
								}
								cont++;

								n=i;
							}else{
								vs.remove(cont);
							}
						} catch (JSONException e) {
							//Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

						}
					}

					int t=vs.size();
					int n=0;
					//int cont=0;
					if(t==0){}else{
						if(cont>1){
							((TextView) rootView.findViewById(R.id.ll)).setText("Libros Prestados");
						}else{
							((TextView) rootView.findViewById(R.id.ll)).setText("Libro Prestado");
						}
						((TextView) rootView.findViewById(R.id.item_detail)).setText(vs.get(n).getIdp());

						//((TextView) rootView.findViewById(R.id.ltl)).setText(vs.get(n).getTitulo());
						//((TextView) rootView.findViewById(R.id.cdl)).setText(vs.get(n).getCodigoL());
						//((TextView) rootView.findViewById(R.id.vl)).setText(vs.get(n).getValorL());
						//((TextView) rootView.findViewById(R.id.clcl)).setText(vs.get(n).getTipoC());
						recyclerView1.setAdapter(new SimpleItemRecyclerViewAdapter(ItemDetailFragmentd.this, vs));
						animacion(recyclerView1);
						((TextView) rootView.findViewById(R.id.cu)).setText(vs.get(n).getCodigoU());
						((TextView) rootView.findViewById(R.id.un)).setText(vs.get(n).getNombres());
						((TextView) rootView.findViewById(R.id.ua)).setText(vs.get(n).getApellidos());
						((TextView) rootView.findViewById(R.id.tu)).setText(vs.get(n).getTipoU());
						//Toast.makeText(getActivity(), ""+vs.size(),Toast.LENGTH_LONG).show();
						
						//((TextView) rootView.findViewById(R.id.fap)).setText(vs.get(n).getFecha());
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
		

		
        
            
            Button dv =(Button) rootView.findViewById(R.id.dd);
        dv.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1)
                {
                    if(etFecha.getText().toString().equals("")){
                        Toast.makeText(getActivity(), "Seleccione la Fecha de Devolucion",Toast.LENGTH_LONG).show();
                        
                    }else{
						
						ip i=new ip();
						String ip=i.ip();
						String Url="http://"+ip+"/vprestamodr.php?codigo="+presN;
						//Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


						JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

								@Override
								public void onResponse(JSONArray response) {
									JSONObject jo = null;
									int cont=0;
									for (int i = 0; i < response.length(); i++) {
										try {
											int n=0;

											String kk;
											jo = response.getJSONObject(i);
											vs.add(new vprestamo(jo.getString("idp"), jo.getString("fecha"), jo.getString("codigo"), jo.getString("nombres"), jo.getString("apellidos"), jo.getString("tipo_u"), jo.getString("codigol"), jo.getString("titulo"), jo.getString("valorl"), jo.getString("tipo_coleccion") ));
											kk=vs.get(cont).getIdp();
											if(vs.get(cont).getIdp().equals(presN)||presN.equals("")){
												if(presN.equals("")){
													presN=kk;
												}
												cont++;

												n=i;
											}else{
												vs.remove(cont);
											}
										} catch (JSONException e) {
											//Toast.makeText(getActivity(), "error de Bd", Toast.LENGTH_LONG).show();

										}
									}
								
									ip i=new ip();
									String ip=i.ip();
						String Urle="http://"+ip+"/vdia.php";
						JsonArrayRequest jj=new JsonArrayRequest(Urle, new Response.Listener<JSONArray>(){

								@Override
								public void onResponse(JSONArray p1)
								{
									JSONObject jo = null;
									for (int i = 0; i < p1.length(); i++) {
										try {
											jo = p1.getJSONObject(i);
											fech[0]=jo.getString("dias_sala");
											fech[1]=jo.getString("dias_reserva");
											fech[2]=jo.getString("dias_u");
											fech[3]=jo.getString("dias_d");
											fech[4]=jo.getString("dias_a");
											fech[5]=jo.getString("dias_o");



										} catch (JSONException e) {

										}

									}
									ip i=new ip();
									String ip=i.ip();
									dias=0;



									if(vs.size()>1){
										int x=0;
										int r=0;
										int s=0;
										while(x<vs.size()){
											if (vs.get(x).getTipoC().equals("sala")){
												if(r==0){
													if(s==1){
														dias=Integer.parseInt(fech[0])-dias;
														r=1;
													}else{
													dias=Integer.parseInt(fech[0]);
													r=1;
													}
												}
											}else{
												if(s==0){
													if(r==1){
														dias=dias-Integer.parseInt(fech[1]);
														s=1;
													}else{
														dias=Integer.parseInt(fech[1]);
														s=1;
													}
												}
											}
											x=x+1;
										}
										if(vs.get(0).getTipoU().equals("estudiante")){
											dias=dias+Integer.parseInt(fech[2]);
										}
										if(vs.get(0).getTipoU().equals("docentes")){
											dias=dias+Integer.parseInt(fech[3]);
										}
										if(vs.get(0).getTipoU().equals("administrativo")){
											dias=dias+Integer.parseInt(fech[4]);
										}
										if(vs.get(0).getTipoU().equals("otros")){
											dias=dias+Integer.parseInt(fech[5]);
										}
										
									}else{
										if(vs.get(0).getTipoC().equals("sala")){
											dias=Integer.parseInt(fech[0]);
										}else{
											dias=Integer.parseInt(fech[1]);
										}
										if(vs.get(0).getTipoU().equals("estudiante")){
											dias=dias+Integer.parseInt(fech[2]);
										}
										if(vs.get(0).getTipoU().equals("docentes")){
											dias=dias+Integer.parseInt(fech[3]);
										}
										if(vs.get(0).getTipoU().equals("administrativo")){
											dias=dias+Integer.parseInt(fech[4]);
										}
										if(vs.get(0).getTipoU().equals("otros")){
											dias=dias+Integer.parseInt(fech[5]);
										}
									}

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

													final int multaa=1000*dias;

													int c=0;
													int cosnt=0;

													while(c<vs.size()){
														cosnt=cosnt+Integer.parseInt(vs.get(c).getValorL());
														c=c+1;
													}
													int valorl=cosnt;
//Toast.makeText(getActivity(), nnn,Toast.LENGTH_LONG).show();


													final int cintodiez=(valorl*110)/100;
													if(multaa>cintodiez){

//si la multa es manor de 110 
// Toast.makeText(getActivity(), " es 110",Toast.LENGTH_LONG).show();

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
														
														getActivity().finish();

													}else{
//Toast.makeText(getActivity(), " no es 110",Toast.LENGTH_LONG).show();

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
									
									
								


								
								}
							}, new Response.ErrorListener(){

								@Override
								public void onErrorResponse(VolleyError p1)
								{
									// TODO: Implement this method
								}


							});
						RequestQueue requestQueue2;
						requestQueue2= Volley.newRequestQueue(getActivity());
						requestQueue2.add(jj);
								}}, new Response.ErrorListener() {
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
                   
                    
                }
                
                
                
            });
            
		
		
        return rootView;
    }
	private void animacion(RecyclerView recyclerView){
		Context context=recyclerView.getContext();
		LayoutAnimationController animacion= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_from_right);
		recyclerView.setLayoutAnimation(animacion);
		recyclerView.getAdapter().notifyDataSetChanged();
		recyclerView.scheduleLayoutAnimation();
	}
	
	
	public class SimpleItemRecyclerViewAdapter
	extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemDetailFragmentd mParentActivity;
        private final List<vprestamo> vprestamos;
		private Transition transicion;
		public static final long duracion=1000;

        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

				TextView Texs =(TextView) view.findViewById(R.id.cdl);
				ip i=new ip();
				String ip=i.ip();
				ll= new ArrayList<>();
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
        };

        SimpleItemRecyclerViewAdapter(ItemDetailFragmentd parent,
                                      List<vprestamo> items) {
            vprestamos = items;
            mParentActivity = parent;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.libros, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.tl.setText(vprestamos.get(position).getTitulo());
            holder.cd.setText(vprestamos.get(position).getCodigoL());
			holder.vll.setText(vprestamos.get(position).getValorL());
            holder.tpoc.setText(vprestamos.get(position).getTipoC());
            holder.itemView.setTag(vprestamos.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return vprestamos.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView tl,cd,vll,tpoc;



            ViewHolder(View view) {
                super(view);

				tl=(TextView) view.findViewById(R.id.ltl);
				cd=(TextView) view.findViewById(R.id.cdl);
				vll=(TextView) view.findViewById(R.id.vl);
				tpoc=(TextView) view.findViewById(R.id.clcl);

            }
        }
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
