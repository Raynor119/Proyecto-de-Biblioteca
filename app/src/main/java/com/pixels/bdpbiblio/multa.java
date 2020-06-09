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

import com.pixels.bdpbiblio.dummy.DummyContent;

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



public class multa extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    List<multaa> vs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multa);

       

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        String Url="http://"+ip+"/vmultas.php";
        //Toast.makeText(getApplicationContext(), Url,Toast.LENGTH_LONG).show();


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jo = null;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jo = response.getJSONObject(i);
                            vs.add(new multaa(jo.getString("idm"), jo.getString("id_d"), jo.getString("vmulta"), jo.getString("multap"),jo.getString("idp"), jo.getString("codigo"), jo.getString("nombres"), jo.getString("apellidos"), jo.getString("tipo_u"), jo.getString("codigol"), jo.getString("titulo"), jo.getString("valorl"), jo.getString("tipo_coleccion") ));

                        } catch (JSONException e) {
                            //Toast.makeText(getApplicationContext(), "error de Bd", Toast.LENGTH_LONG).show();

                        }
                    }
                    if(vs.size()==0){
                        Toast.makeText(getApplicationContext(), "No hay devoluciones", Toast.LENGTH_LONG).show();

                    }else{

                        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(multa.this, vs, mTwoPane));
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

    public static class SimpleItemRecyclerViewAdapter
	extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final multa mParentActivity;
        private final List<multaa> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multaa item = (multaa) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment2.ARG_ITEM_ID, item.getIdm());
                    ItemDetailFragment2 fragment = new ItemDetailFragment2();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment)
						.commit();
                    fragment.idpp(item.getIdm());
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity2.class);
                    intent.putExtra(ItemDetailFragment2.ARG_ITEM_ID, item.getIdm());
                    intent.putExtra("ippt",item.getIdm());
                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(multa parent,
                                      List<multaa> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_list_content2, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).getIdm());
            holder.mContentView.setText(mValues.get(position).getIdm());
            holder.nn.setText(mValues.get(position).getNombres());
            holder.mm.setText(mValues.get(position).getTipoU());
			holder.cop.setText(mValues.get(position).getIdp());
            holder.idp.setText(mValues.get(position).getId_d());
            holder.vm.setText(mValues.get(position).getVmulta());
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
            final TextView mm,cop,idp,vm;
            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text2);
                mContentView = (TextView) view.findViewById(R.id.content2);
                nn = (TextView) view.findViewById(R.id.nm);
                mm = (TextView) view.findViewById(R.id.tp);
				cop=(TextView) view.findViewById(R.id.cpp);
                idp=(TextView) view.findViewById(R.id.id_p);
                vm=(TextView) view.findViewById(R.id.vm);
                
            }
        }
    }
}
