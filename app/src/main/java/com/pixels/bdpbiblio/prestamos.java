package com.pixels.bdpbiblio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.widget.TextView;import android.widget.Toast; import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.VolleyError;import com.android.volley.toolbox.JsonArrayRequest;import com.android.volley.toolbox.Volley; import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject; import java.util.ArrayList;import java.util.List;



import java.util.List;
import java.util.ArrayList;
import java.util.zip.Inflater;
import android.widget.EditText;
import android.view.animation.LayoutAnimationController;
import android.view.animation.AnimationUtils;
import android.transition.Fade;
import android.view.animation.DecelerateInterpolator;
import android.transition.Slide;
import android.view.Gravity;
import android.transition.Transition;
import android.support.v4.app.ActivityOptionsCompat;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class prestamos extends AppCompatActivity {

     List<vprestamo> vs = new ArrayList<>();
	List<idd> vq = new ArrayList<>();
    List<idd> vss = new ArrayList<>();
   //  * Whether or not the activity is in two-pane mode, i.e. running on a tablet
    // * device.
	private Transition transicion;
	public static final long duracion=1000;
	private final prestamos mParentActivity=this;
   //  */
    private boolean mTwoPane;
	int kl=0;
	private Context content=this;
	int prestmo=0;
	int contedl=0;
	int devueltos=0;
	EditText codig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamo);
		Slide falden=new Slide(Gravity.START);
		falden.setDuration(MainActivity.duracion);
		falden.setInterpolator(new DecelerateInterpolator());
		getWindow().setEnterTransition(falden);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
				AlertDialog.Builder alert= new AlertDialog.Builder(prestamos.this);
				alert.setMessage("El Usuario es de la Universidad")
					.setCancelable(false)
					.setPositiveButton("si", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,int which){
							AlertDialog.Builder alertt= new AlertDialog.Builder(prestamos.this);
							//alertt.setMessage("El Usuario ya se habia Registrado")
							View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialogocu, null);
							codig= (EditText) view.findViewById(R.id.codigou);
							alertt.setView(view)
								.setCancelable(false)
								.setPositiveButton("si", new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface dialog,int which){
										contedl=0;
										
										
										
										ip i=new ip();
										String ip=i.ip();
										String Url="http://"+ip+"/hayu.php";
										//Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


										JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

												@Override
												public void onResponse(JSONArray response) {
													JSONObject jo = null;
													int si=0;
													for (int i = 0; i < response.length(); i++) {
														try {
															jo = response.getJSONObject(i);
															
															if(codig.getText().toString().equals(jo.getString("codigo"))){
																si=1;
															}
															
														} catch (JSONException e) {
															Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

														}
													}
													
													if(si==1){
														
														
														
														ip i=new ip();
														String ip=i.ip();
														String Url="http://"+ip+"/vdip.php";
														JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

																@Override
																public void onResponse(JSONArray response) {
																	vss = new ArrayList<>();
																	JSONObject jo = null;
																	for (int i = 0; i < response.length(); i++) {
																		try {
																			jo = response.getJSONObject(i);

																			vss.add(new idd(jo.getString("id_p")));
																		} catch (JSONException e) {
																			//  Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

																		}
																	}

																	ip i=new ip();
																	String ip=i.ip();
																	String Url="http://"+ip+"/vprestamo.php";
																	//Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


																	JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

																			@Override
																			public void onResponse(JSONArray response) {
																				vs = new ArrayList<>();
																				String ns="";
																				JSONObject jo = null;
																				final String nn=codig.getText().toString();

																				for (int b = 0; b < response.length(); b++) {
																					try {
																						jo = response.getJSONObject(b);
																						if (jo.getString("idp").equals(ns)){
																						}else{
																							if(jo.getString("codigo").equals(nn)){
																							ns=jo.getString("idp");
																							vs.add(new vprestamo(jo.getString("idp"), jo.getString("fecha"), jo.getString("codigo"), jo.getString("nombres"), jo.getString("apellidos"), jo.getString("tipo_u"), jo.getString("codigol"), jo.getString("titulo"), jo.getString("valorl"), jo.getString("tipo_coleccion") ));
																							}
																						}

																					} catch (JSONException e) {
																						// Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

																					}
																				}
																				for(int x=0;x<vss.size();x++){
																					for(int b=0;b<vs.size();b++){
																						if(vs.get(b).getIdp().equals(vss.get(x).getId_p())){
																							vs.remove(b);
																						}
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
																	requestQueue= Volley.newRequestQueue(getApplicationContext());
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
														RequestQueue requestQueue;
														requestQueue= Volley.newRequestQueue(getApplicationContext());
														requestQueue.add(jsonArrayRequest);
														
														
														
														new android.os.Handler().postDelayed(new Runnable() {


																@Override
																public void run() {
																	if(vs.size()==0){
																	
																	}else{
																		ip i=new ip();
																		String ip=i.ip();
																		String Url3="http://"+ip+"/libxpre.php";
																		JsonArrayRequest jsonArrayRequest3=new JsonArrayRequest(Url3, new Response.Listener<JSONArray>() {

																				@Override
																				public void onResponse(JSONArray response) {
																					vq = new ArrayList<>();

																					JSONObject jo = null;
																					final String nn=codig.getText().toString();

																					for (int b = 0; b < response.length(); b++) {
																						int ns=0;
																						try {
																							jo = response.getJSONObject(b);
																							if(vs.get(ns).getIdp().equals( jo.getString("idp"))){
																								vq.add(new idd(jo.getString("idp")));

																							}else{
																								ns++;
																								if(ns<vs.size()){
																									if(vs.get(ns).getIdp().equals( jo.getString("idp"))){
																										vq.add(new idd(jo.getString("idp")));
																									}
																								}
																							}

																						}

																						catch (JSONException e) {
																							// Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

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

																		//q=x;
																		//final String m=vs.get(x).getIdp();
																		//System.out.println(m);


																		RequestQueue requestQueue3;
																		requestQueue3= Volley.newRequestQueue(getApplicationContext());
																		requestQueue3.add(jsonArrayRequest3);



																		for(int x=0;x<vq.size();x++){
																			String qq=vq.get(x).getId_p();
																			System.out.println(qq);
																		}
																	}

																	new android.os.Handler().postDelayed(new Runnable() {


																			@Override
																			public void run() {
														if(vq.size()<3){
															//Toast.makeText(getApplicationContext(),"entro del for4 2:"+vq.size(),Toast.LENGTH_LONG).show();
															final String nn=codig.getText().toString();
															Intent intent=new Intent(prestamos.this,agregarprestamou.class);
															intent.putExtra("codigo",nn);
															int cod=contedl;
															//Toast.makeText(getApplicationContext(),""+cod,Toast.LENGTH_LONG).show();
															intent.putExtra("saca",vq.size()+"");
															transicion=new Slide(Gravity.START);
															transicion.setDuration(duracion);
															transicion.setInterpolator(new DecelerateInterpolator());
															getWindow().setExitTransition(transicion);
															startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(mParentActivity).toBundle());

															finish();

														}else{
															Toast.makeText(getApplicationContext(),"El Usuario no puede Sacar mas Libros hasta que los devuelva",Toast.LENGTH_LONG).show();


														}

																			}},2000);

																}},2000);
														
														
														
													}else{
														
														Toast.makeText(getApplicationContext(), "el usuario no esta registrado", Toast.LENGTH_LONG).show();
														
													}
													
													
													
													
													
													
													
													


												}
											}, new Response.ErrorListener() {
												@Override
												public void onErrorResponse(VolleyError error) {
													new android.os.Handler().postDelayed(new Runnable() {


															@Override
															public void run() {
																Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

															}},2000);
												}
											});
										RequestQueue requestQueue;
										requestQueue= Volley.newRequestQueue(getApplicationContext());
										requestQueue.add(jsonArrayRequest);
										
										

										

									}

								})
								.setNegativeButton("cancelar", new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface dialog,int which){




									}

								});
							AlertDialog titulo=alertt.create();
							titulo.setTitle("Codigo del Usuario");
							titulo.show();
							
							
							
						}

					})
					.setNegativeButton("no", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,int which){
							Intent intent=new Intent(prestamos.this,agregarpretano.class);
							transicion=new Slide(Gravity.START);
							transicion.setDuration(duracion);
							transicion.setInterpolator(new DecelerateInterpolator());
							getWindow().setExitTransition(transicion);

							startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(mParentActivity).toBundle());
							
							finish();

							
							
						}

					});
				AlertDialog titulo=alert.create();
				titulo.setTitle("Pregunta");
				titulo.show();

				
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
		ip i=new ip();
        String ip=i.ip();
        String Url="http://"+ip+"/vdip.php";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jo = null;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jo = response.getJSONObject(i);

                            vss.add(new idd(jo.getString("id_p")));
                        } catch (JSONException e) {
                          //  Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

                        }
                    }
                    
                    ip i=new ip();
                    String ip=i.ip();
                    String Url="http://"+ip+"/vprestamo.php";
                    //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


                    JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {

								String ns="";
                                JSONObject jo = null;

                                for (int b = 0; b < response.length(); b++) {
                                    try {
                                        jo = response.getJSONObject(b);
										if (jo.getString("idp").equals(ns)){
										}else{
											ns=jo.getString("idp");
											vs.add(new vprestamo(jo.getString("idp"), jo.getString("fecha"), jo.getString("codigo"), jo.getString("nombres"), jo.getString("apellidos"), jo.getString("tipo_u"), jo.getString("codigol"), jo.getString("titulo"), jo.getString("valorl"), jo.getString("tipo_coleccion") ));
											
										}
                                       
                                    } catch (JSONException e) {
                                       // Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

                                    }
                                }
                                for(int x=0;x<vss.size();x++){
                                    for(int b=0;b<vs.size();b++){
                                        if(vs.get(b).getIdp().equals(vss.get(x).getId_p())){
                                            vs.remove(b);
                                        }
                                    }
                                }
								for(int x=0;x<vs.size();x++){
									String m=vs.get(x).getIdp();
									System.out.println(m);

								}
                                if(vs.size()==0){
                                    Toast.makeText(getApplicationContext(), "No hay prestamos", Toast.LENGTH_LONG).show();
                                    
                                }else{
									

                                recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(prestamos.this, vs, mTwoPane));
								animacion(recyclerView);
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
                    requestQueue= Volley.newRequestQueue(getApplicationContext());
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
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
		
        
    }
	private void animacion(RecyclerView recyclerView){
		Context context=recyclerView.getContext();
		LayoutAnimationController animacion= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_from_right);
		recyclerView.setLayoutAnimation(animacion);
		recyclerView.getAdapter().notifyDataSetChanged();
		recyclerView.scheduleLayoutAnimation();
	}
	

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final prestamos mParentActivity;
        private final List<vprestamo> vprestamos;
		private Transition transicion;
		public static final long duracion=1000;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vprestamo item = (vprestamo) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
					Context context = view.getContext();
				
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.getIdp());
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
					fragment.idpp(item.getIdp());
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
					
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.getIdp());
					intent.putExtra("ippt",item.getIdp());
					transicion=new Slide(Gravity.START);
					transicion.setDuration(duracion);
					transicion.setInterpolator(new DecelerateInterpolator());
					mParentActivity.getWindow().setExitTransition(transicion);

                    context.startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(mParentActivity).toBundle());
                }
            }
        };

        SimpleItemRecyclerViewAdapter(prestamos parent,
                                      List<vprestamo> items,
                                      boolean twoPane) {
            vprestamos = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(vprestamos.get(position).getIdp());
            holder.mContentView.setText(vprestamos.get(position).getIdp());
			holder.nn.setText(vprestamos.get(position).getNombres());
            holder.mm.setText(vprestamos.get(position).getTipoU());
            holder.itemView.setTag(vprestamos.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return vprestamos.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;
			final TextView nn;
            final TextView mm;
			

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
				nn = (TextView) view.findViewById(R.id.nm);
				mm = (TextView) view.findViewById(R.id.tp);
            }
        }
    }
	
	
}
