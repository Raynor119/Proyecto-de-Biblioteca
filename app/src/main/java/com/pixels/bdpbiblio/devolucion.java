package com.pixels.bdpbiblio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.DecelerateInterpolator;
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
public class devolucion extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
	List<vdevolucion> vs = new ArrayList<>();
    private boolean mTwoPane;
	private final devolucion mParentActivity=this;
	EditText codig;
	private Transition transicion;
	public static final long duracion=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devolucion);
		Slide falden=new Slide(Gravity.START);
		falden.setDuration(MainActivity.duracion);
		falden.setInterpolator(new DecelerateInterpolator());
		getWindow().setEnterTransition(falden);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					
					
					AlertDialog.Builder alertt= new AlertDialog.Builder(devolucion.this);
					//alertt.setMessage("El Usuario ya se habia Registrado")
					View viewe = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialogocu, null);
					codig= (EditText) viewe.findViewById(R.id.codigou);
					alertt.setView(viewe)
						.setCancelable(false)
						.setPositiveButton("si", new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface dialog,int which){
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
												//si hay usuario
												Intent intent=new Intent(devolucion.this,pretamod.class);
                                                intent.putExtra("codigo",codig.getText().toString());
                                                transicion=new Slide(Gravity.START);
												transicion.setDuration(duracion);
												transicion.setInterpolator(new DecelerateInterpolator());
												mParentActivity.getWindow().setExitTransition(transicion);
												startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(mParentActivity).toBundle());
												finish();
												
												
												
												
												
												
												
												
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
														finish();
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
		String Url="http://"+ip+"/vdevolucion.php";
		//Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

				@Override
				public void onResponse(JSONArray response) {
					JSONObject jo = null;
					for (int i = 0; i < response.length(); i++) {
						try {
							jo = response.getJSONObject(i);
							vs.add(new vdevolucion(jo.getString("idd"), jo.getString("fecha"),jo.getString("fecha_p"), jo.getString("multa"), jo.getString("multa_d"),jo.getString("idp"), jo.getString("codigo"), jo.getString("nombres"), jo.getString("apellidos"), jo.getString("tipo_u")));

						} catch (JSONException e) {
							//Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

						}
					}
                    if(vs.size()==0){
                        Toast.makeText(getApplicationContext(), "No hay devoluciones", Toast.LENGTH_LONG).show();

                    }else{
                        
					
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(devolucion.this, vs, mTwoPane));
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
        requestQueue= Volley.newRequestQueue(this);
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

        private final devolucion mParentActivity;
        private final List<vdevolucion> mValues;
        private final boolean mTwoPane;
		private Transition transicion;
		public static final long duracion=1000;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
				vdevolucion item = (vdevolucion) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment1.ARG_ITEM_ID, item.getIdd());
                    ItemDetailFragment1 fragment = new ItemDetailFragment1();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment)
						.commit();
                    fragment.idpp(item.getIdd());
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity1.class);
                    intent.putExtra(ItemDetailFragment1.ARG_ITEM_ID, item.getIdd());
					intent.putExtra("ippt",item.getIdd());
                    //Toast.makeText(context, item.getIdd(),Toast.LENGTH_LONG).show();
                    transicion=new Slide(Gravity.START);
					transicion.setDuration(duracion);
					transicion.setInterpolator(new DecelerateInterpolator());
					mParentActivity.getWindow().setExitTransition(transicion);
                    context.startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(mParentActivity).toBundle());
                    
                }
            }
        };

        SimpleItemRecyclerViewAdapter(devolucion parent,
                                      List<vdevolucion> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_list_content1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).getIdd());
            holder.mContentView.setText(mValues.get(position).getIdd());
			holder.nn.setText(mValues.get(position).getNombres());
            holder.mm.setText(mValues.get(position).getTipoU());
			holder.cop.setText(mValues.get(position).getIdp());
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;
			final TextView nn;
            final TextView mm,cop;


            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text1);
                mContentView = (TextView) view.findViewById(R.id.content1);
				nn = (TextView) view.findViewById(R.id.nm);
				mm = (TextView) view.findViewById(R.id.tp);
				cop=(TextView) view.findViewById(R.id.cpp);
            }
        }
    }
}
