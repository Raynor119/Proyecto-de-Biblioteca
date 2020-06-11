package com.pixels.bdpbiblio;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.DecelerateInterpolator;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity1 extends AppCompatActivity {
	String ipt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
		Bundle extra = getIntent().getExtras();
		ipt=extra.getString("ippt");
		Slide falden=new Slide(Gravity.START);
		falden.setDuration(MainActivity.duracion);
		falden.setInterpolator(new DecelerateInterpolator());
		getWindow().setEnterTransition(falden);
		


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment1.ARG_ITEM_ID,
								getIntent().getStringExtra(ItemDetailFragment1.ARG_ITEM_ID));
            ItemDetailFragment1 fragment = new ItemDetailFragment1();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
				.add(R.id.item_detail_container, fragment)
				.commit();
			fragment.idpp(ipt);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, devolucion.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
