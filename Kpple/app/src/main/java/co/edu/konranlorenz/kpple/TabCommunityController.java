package co.edu.konranlorenz.kpple;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import activities.CalificationCommunityActivity;
import activities.CommentListActivity;
import activities.GalleryFragment;
import activities.PostViewer;
import activities.PostViewerComunityFragment;
import activities.PostViewerFragment;
import activities.TabBlank;
import entities.Community;
import lib.FirebaseFunctions;

public class TabCommunityController extends AppCompatActivity implements GalleryFragment.OnFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ImageView imgCommunity;
    private TextView txtCommunityName;
    private ImageView imgFollow;
    private ImageView imgcalificar;
    private TextView tCalification;
    private String idParam;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_community_controller);
        FirebaseFunctions firebaseFunctions = new FirebaseFunctions();

        imgCommunity = findViewById(R.id.img_comunity);
        txtCommunityName = findViewById(R.id.txtcomunityName);
        imgcalificar = findViewById(R.id.img_calificar_star);
        tCalification = findViewById(R.id.txt_value_rating);
        Intent intent = getIntent();
        Bundle bdiduser = intent.getExtras();
        if(bdiduser != null)
        {
            String uiduser = (String) bdiduser.get("idcommunity");
            idParam = uiduser;
            /*Log.i("TABLOGComunnity",uiduser);*/
            DatabaseReference refuserCurrent = firebaseFunctions.getReferenceCommunityByID(uiduser);
            refuserCurrent.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String nameUser = "NameComunnityD";
                    String url = "";
                    String calif ="";
                    Community commu = dataSnapshot.getValue(Community.class);
                    nameUser=commu.getName();
                    url = commu.getUrlImage();
                    calif = ""+commu.getCalification();

                    txtCommunityName.setText(nameUser);
                    tCalification.setText(calif);
                    Glide.with(getApplicationContext())
                            .load(url)
                            .into(imgCommunity);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intentpr = new Intent(TabCommunityController.this,CommentListActivity.class);
                startActivity(intentpr);
            }
        });

        imgcalificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TabCommunityController.this,CalificationCommunityActivity.class);
                intent.putExtra("idcommunity", idParam);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_community_controller, menu);
        return true;
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

    @Override
    public void onFragmentInteraction(Uri uri) {
        
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab_community_controller, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    /*PostViewerComunityFragment postViewerComunityFragment = new PostViewerComunityFragment();
                    return postViewerComunityFragment;*/
                    PostViewerFragment postViewer = new PostViewerFragment();
                    return postViewer;
                case 1:
                    GalleryFragment tab1 = new GalleryFragment();
                    return tab1;
                case 2:
                    TabBlank tab2 = new TabBlank();
                    return tab2;
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
