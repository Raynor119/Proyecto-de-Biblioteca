package com.pixels.bdpbiblio;

import android.app.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.content.*;
import android.transition.Transition;
import android.transition.Explode;
import android.view.animation.DecelerateInterpolator;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Slide;

public class MainActivity extends AppCompatActivity 
{
	public static final long duracion=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	
	private Transition transicion;
	public void onclic1(View view){
		Intent intent = new Intent(MainActivity.this,prestamos.class);
		transicion=new Slide(Gravity.START);
		transicion.setDuration(duracion);
		transicion.setInterpolator(new DecelerateInterpolator());
		getWindow().setExitTransition(transicion);
		startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
	}
	
	public void onclic3(View view){
		Intent intent = new Intent(MainActivity.this,devolucion.class);
		transicion=new Slide(Gravity.START);
		transicion.setDuration(duracion);
		transicion.setInterpolator(new DecelerateInterpolator());
		getWindow().setExitTransition(transicion);
		startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
	}
	public void onclicksalir(View view){
		finish();
	}
}
