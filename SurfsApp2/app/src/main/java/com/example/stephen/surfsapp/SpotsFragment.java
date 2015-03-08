package com.example.stephen.surfsapp;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpotsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    // hold onto position clicked on the Nav Drawer
    int pos;

    // interface reference variable
    NavigationDrawerFragment.FragmentTransactions fragmentTransactions;

    public SpotsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("Fragment ", "onCreateView called");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spots, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        // creates spots array from string-array xml file from resources
        Resources res = getResources();
        String[] spots = res.getStringArray(R.array.spots);

        // set / bind list adapter to fragment, set a listener for the list view
        setListAdapter(new ArrayAdapter<>(getActivity(), R.layout.row, R.id.textView, spots));
        getListView().setOnItemClickListener(this);

        // this returns a ref to main activity initializing to the interface property
        fragmentTransactions = (NavigationDrawerFragment.FragmentTransactions) getActivity();

        Bundle extras = getArguments();
        pos = extras.getInt("pos");
        Log.i("Bundle ", " value " + pos);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(pos == 0) {
            Log.i("Btn 0 ", "clicked " + pos);

            ForecastFragment foreFrag = new ForecastFragment();
            fragmentTransactions.fragmentTransaction(foreFrag, pos);
        }
        else if(pos == 1) {
            Log.i("Btn 1 ", "clicked " + pos);

            ChartsFragment chartsFrag = new ChartsFragment();
            fragmentTransactions.fragmentTransaction(chartsFrag, pos);
        }
        else if(pos == 2) {
            Log.i("Btn 2 ", "clicked " + pos);

            TidesFragment tidesFrag = new TidesFragment();
            fragmentTransactions.fragmentTransaction(tidesFrag, pos);
        }

/*
   // Use for position of List to return data for spot in list

        if(position == 0){

            Log.i("Hello", "The Peak Bundoran");
        }
        else{
            Log.i("Hello", "Other Clicked");
        }
*/

    }
}



// From Spots Activity use this for Charts Fragment to implement Page Viewer

// Also see Charts and MyAdapterCharts from FYP project and Charts and Spots.xml files for ViewPager
///////////////////////////////////////////////////////////////////////////////////

/*
package com.surfsapp.stephen.surfsapp;

        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;

        import com.android.volley.toolbox.Volley;



//public class Spots extends ActionBarActivity implements MyListFragment.OnFragmentInteractionListener, ChartsFragment.OnFragmentInteractionListener, LastSpotsFragment.OnFragmentInteractionListener {

    ViewPager viewPager = null;


    //instantiate a db object
    // TaskListDatabase task = new TaskListDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots);

        viewPager = (ViewPager) findViewById(R.id.pager_switch_fragments);
        FragmentManager fragMan = getSupportFragmentManager();

        viewPager.setAdapter(new MyAdapter(fragMan));


        /*

        //try catch statement to open database
        try {
            task.open();
            Log.i("Db", "Opened");
        }
        catch (SQLException sqlExc) {

        }

        */

/*
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.spots_actionbar, menu);

//        MenuInflater spotsActionBar = getMenuInflater();
//        spotsActionBar.inflate(R.menu.menu_spots, menu);

        return (super.onCreateOptionsMenu(menu));

//        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // this method comes from the interface inside MyListFragment in FYP project
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class MyAdapter extends FragmentPagerAdapter {

        Fragment newFrag = null;

        public MyAdapter(FragmentManager fragMan) {
            super(fragMan);
        }


        @Override
        public Fragment getItem(int pos) {

            Log.d("Ste", "get item called");

            if(pos == 0)
                newFrag = new MyListFragment();

            if(pos == 1) {

                newFrag = new ChartsFragment();

            if(pos == 2)
                newFrag = new LastSpotsFragment();

            return newFrag;
        }

        @Override
        public int getCount() {
            Log.d("Ste", "get count called");
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int pos) {

            if(pos == 0)
                return "Spots";
            if(pos == 1)
                return "Charts";
            if(pos == 2)
                return "Last Spots";

            return null;
        }
    }
}

*/