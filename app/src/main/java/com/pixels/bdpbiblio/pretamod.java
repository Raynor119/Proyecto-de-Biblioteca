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

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class pretamod extends AppCompatActivity {

	List<vprestamo> vs = new ArrayList<>();
	//  * Whether or not the activity is in two-pane mode, i.e. running on a tablet
    // * device.
	//  */
    List<idd> vss = new ArrayList<>();
    private boolean mTwoPane;
	private Context content=this;
	int prestmo=0;
	int devueltos=0;
	EditText codig;
    String cod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamod);
        Bundle extra = getIntent().getExtras();
        
        cod=extra.getString("codigo");
        

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
        String Url="http://"+ip+"/vdipd.php?codigo="+cod;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jo = null;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jo = response.getJSONObject(i);

                            vss.add(new idd(jo.getString("id_p")));
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

                        }
                    }

                    ip i=new ip();
                    String ip=i.ip();
                    String Url="http://"+ip+"/vprestamod.php?codigo="+cod;
                    //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


                    JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {


                                JSONObject jo = null;

                                for (int b = 0; b < response.length(); b++) {
                                    try {
                                        jo = response.getJSONObject(b);


                                        vs.add(new vprestamo(jo.getString("idp"), jo.getString("fecha"), jo.getString("codigo"), jo.getString("nombres"), jo.getString("apellidos"), jo.getString("tipo_u"), jo.getString("codigol"), jo.getString("titulo"), jo.getString("valorl"), jo.getString("tipo_coleccion") ));

                                    } catch (JSONException e) {
                                        Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

                                    }
                                }
                                for(int x=0;x<vss.size();x++){
                                    for(int b=0;b<vs.size();b++){
                                        if(vs.get(b).getIdp().equals(vss.get(x).getId_p())){
                                            vs.remove(b);
                                        }
                                    }
                                }


                                recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(pretamod.this, vs, mTwoPane));





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


    public  class SimpleItemRecyclerViewAdapter
	extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final pretamod mParentActivity;
        private final List<vprestamo> vprestamos;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vprestamo item = (vprestamo) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
					Context context = view.getContext();

                    arguments.putString(ItemDetailFragmentd.ARG_ITEM_ID, item.getIdp());
                    ItemDetailFragmentd fragment = new ItemDetailFragmentd();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment)
						.commit();
					fragment.idpp(item.getIdp());
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivityd.class);

                    intent.putExtra(ItemDetailFragmentd.ARG_ITEM_ID, item.getIdp());
					intent.putExtra("ippt",item.getIdp());

                    context.startActivity(intent);
                    finish();
                }
            }
        };

        SimpleItemRecyclerViewAdapter(pretamod parent,
                                      List<vprestamo> items,
                                      boolean twoPane) {
            vprestamos = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_list_contentd, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(vprestamos.get(position).getIdp());
            holder.mContentView.setText(vprestamos.get(position).getIdp());
			holder.nn.setText(vprestamos.get(position).getNombres());
            holder.mm.setText(vprestamos.get(position).getTipoU());
            holder.titu.setText(vprestamos.get(position).getTitulo());
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
            final TextView titu;
            final TextView mm;


            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
				nn = (TextView) view.findViewById(R.id.nm);
                titu=(TextView) view.findViewById(R.id.titu);
				mm = (TextView) view.findViewById(R.id.tp);
            }
        }
    }


}
