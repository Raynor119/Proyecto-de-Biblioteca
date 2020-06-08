package com.pixels.Gestion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class registrar extends AppCompatActivity
{
	public EditText usuario,nombre,contraseña;
	Button bm;
	
	@Override
	protected void onCreate(Bundle SavedInstacestate){
		super.onCreate(SavedInstacestate);
		setContentView(R.layout.activity_registrar);
		usuario=(EditText)findViewById(R.id.usurt);
		nombre=(EditText)findViewById(R.id.nombt);
		contraseña=(EditText)findViewById(R.id.contrnt);
		bm=(Button)findViewById(R.id.btnm);
		
		bm.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
			
				BasedeDatosU bas =new BasedeDatosU(getApplicationContext());
			String usur=String.valueOf(usuario.getText().toString());
			String contr=String.valueOf(contraseña.getText().toString());
			String nonb=String.valueOf(nombre.getText().toString());
			if(usur.equals("")||contr.equals("")||nonb.equals("")){
				Toast.makeText(getApplicationContext(), "digite todos los campos", Toast.LENGTH_LONG).show();
			}else
			{
				baseI bs= new baseI(getApplicationContext());
				B_hoja bs1=new B_hoja(getApplicationContext());
				B_experice bs2= new B_experice(getApplicationContext());
				
				//bs1.agreH(usur,"","","","","","","","","","","","");
				//bs2.agreE(usur,"","","","","","","","");
				//bas.agregarE(usur,contr,nonb);
				//bs.agrE(usur,"0");
				ip c= new ip();
				String ipt=c.ip();
				
				servicioU("http://"+ipt+":80/AppAndroid/tb_usuarios.php");
				servicioH("http://"+ipt+":80/AppAndroid/h_datos.php");
				servicioE("http://"+ipt+":80/AppAndroid/h_estudios.php");
				servicioEx("http://"+ipt+":80/AppAndroid/h_experiencia.php");
				servicoIni("http://"+ipt+":80/AppAndroid/inicio_bd.php");
				Toast.makeText(getApplicationContext(), "usuario Agregado",Toast.LENGTH_LONG).show();

				finish();
			}
		   }
		});
		}

	
	
		
		
	
		
	private void servicioU(String URL){

		StringRequest strindd=new StringRequest(Request.Method.POST,URL,new Response.Listener<String>(){



				

				public void onResponse(String response){

					

				}
				


			},new Response.ErrorListener(){



				

				public void onErrorResponse(VolleyError error){

					Toast.makeText(getApplicationContext(),"Conexion Fallida",Toast.LENGTH_SHORT).show();





				}

			

			
	}){
		
			protected Map<String, String> getParams() throws AuthFailureError {

				Map<String, String> parametros=new HashMap<String, String>();
				String usur=String.valueOf(usuario.getText().toString());
				String contr=String.valueOf(contraseña.getText().toString());
				String nonb=String.valueOf(nombre.getText().toString());
				parametros.put("usuario",usur);
				parametros.put("contrasena",contr);
				parametros.put("nombre",nonb);
				parametros.put("tipo","0");

				return parametros;
			}
	};
		RequestQueue requestQueue= Volley.newRequestQueue(this);
		
		requestQueue.add(strindd);
	}
	
	
	
	private void servicioH(String URL){

		StringRequest strindd=new StringRequest(Request.Method.POST,URL,new Response.Listener<String>(){



				

				public void onResponse(String response){

					

				}



			},new Response.ErrorListener(){



				

				public void onErrorResponse(VolleyError error){

					Toast.makeText(getApplicationContext(),"Conexion Fallida",Toast.LENGTH_SHORT).show();





				}




			}){

			protected Map<String, String> getParams() throws AuthFailureError {

				Map<String, String> parametros=new HashMap<String, String>();
				String usur=String.valueOf(usuario.getText().toString());
				
				parametros.put("usuario",usur);
				parametros.put("nombre","");
				parametros.put("apellido","");
				parametros.put("fechaden","");
				parametros.put("email","");
				parametros.put("lugarden","");
				parametros.put("edad","");
				parametros.put("cedulac","");
				parametros.put("lugarex","");
				parametros.put("ocupacion","");
				parametros.put("direcc","");
				parametros.put("estadoc","");
				parametros.put("celular","");
				return parametros;
			}
		};
		RequestQueue requestQueue= Volley.newRequestQueue(this);
		
		requestQueue.add(strindd);
	}
	
	
	
	private void servicioE(String URL){

		StringRequest strindd=new StringRequest(Request.Method.POST,URL,new Response.Listener<String>(){



				

				public void onResponse(String response){

					

				}



			},new Response.ErrorListener(){



				

				public void onErrorResponse(VolleyError error){

					Toast.makeText(getApplicationContext(),"Conexion Fallida",Toast.LENGTH_SHORT).show();





				}




			}){

			protected Map<String, String> getParams() throws AuthFailureError {

				Map<String, String> parametros=new HashMap<String, String>();
				String usur=String.valueOf(usuario.getText().toString());
				
				parametros.put("usuario",usur);
				parametros.put("primarios","");
				parametros.put("secundarios","");
				parametros.put("superiores","");
				parametros.put("otros","");
				return parametros;
			}
		};
		RequestQueue requestQueue= Volley.newRequestQueue(this);
		
		requestQueue.add(strindd);
	}

	
	
	private void servicioEx(String URL){

		StringRequest strindd=new StringRequest(Request.Method.POST,URL,new Response.Listener<String>(){





				public void onResponse(String response){

					

				}



			},new Response.ErrorListener(){





				public void onErrorResponse(VolleyError error){

					Toast.makeText(getApplicationContext(),"Conexion Fallida",Toast.LENGTH_SHORT).show();





				}




			}){

			protected Map<String, String> getParams() throws AuthFailureError {

				Map<String, String> parametros=new HashMap<String, String>();
				String usur=String.valueOf(usuario.getText().toString());

				parametros.put("usuario",usur);
				parametros.put("celularrl","");
				parametros.put("nombrerl","");
				parametros.put("celularrf","");
				parametros.put("nombrerf","");
				return parametros;
			}
		};
		RequestQueue requestQueue= Volley.newRequestQueue(this);
		
		requestQueue.add(strindd);
	}
	
	private void servicoIni(String URL){

		StringRequest strindd=new StringRequest(Request.Method.POST,URL,new Response.Listener<String>(){





				public void onResponse(String response){
 
					

				}



			},new Response.ErrorListener(){





				public void onErrorResponse(VolleyError error){

					Toast.makeText(getApplicationContext(),"Conexion Fallida",Toast.LENGTH_SHORT).show();





				}




			}){

			protected Map<String, String> getParams() throws AuthFailureError {

				Map<String, String> parametros=new HashMap<String, String>();
				String usur=String.valueOf(usuario.getText().toString());

				parametros.put("usuario",usur);
				parametros.put("opcion","Usuario");
				parametros.put("dato","0");
				
				return parametros;
			}
		};
		RequestQueue requestQueue= Volley.newRequestQueue(this);
		
		requestQueue.add(strindd);
	}
	
	

}
