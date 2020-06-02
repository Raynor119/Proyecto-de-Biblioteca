package com.pixels.bdpbiblio;

import android.app.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.content.*;

public class MainActivity extends AppCompatActivity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	public void onclic1(View view){
		Intent intent = new Intent(MainActivity.this,prestamos.class);
		startActivity(intent);
	}
	public void onclic2(View view){
		Intent intent = new Intent(MainActivity.this,multa.class);
		startActivity(intent);
	}
	public void onclic3(View view){
		Intent intent = new Intent(MainActivity.this,devolucion.class);
		startActivity(intent);
	}
	public void onclicksalir(View view){
		finish();
	}
}
