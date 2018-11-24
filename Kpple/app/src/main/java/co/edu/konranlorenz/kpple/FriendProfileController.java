package co.edu.konranlorenz.kpple;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import activities.PostViewerFragment;
import activities.TabBlank;
import entities.Block;
import entities.Request;
import lib.FirebaseFunctions;

public class FriendProfileController extends AppCompatActivity {

    private static final String MESSAGE_KEY = "user";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ImageView imgCouple;
    private TextView txtFriendName;
    private ImageView imgFollow;
    private ImageView imgBlock;

    private FirebaseDatabase database;
    private DatabaseReference mDatabaseRef, followRef, friendRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        Intent intent = getIntent();
        Bundle bdiduser = intent.getExtras();

        final String userID = (String) bdiduser.get("iduser");

        //final String userID = "27M4a845YeRSWejMijro3FWGIRj2";
        database = FirebaseDatabase.getInstance();
        mDatabaseRef = database.getReference("User/" + userID);
        followRef = database.getReference("Following");
        friendRef = FirebaseDatabase.getInstance().getReference("Friendship");
        FirebaseUser user = mAuth.getCurrentUser();
        mAuth = FirebaseAuth.getInstance();


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

        imgCouple = findViewById(R.id.imgCouple);
        txtFriendName = findViewById(R.id.txtFriendName);
        imgFollow = findViewById(R.id.imgFollow);
        imgBlock = (ImageView) findViewById(R.id.imgView_block);

        imgFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendRef = FirebaseDatabase.getInstance().getReference("Couple");
                Request reqFriend = new Request(user.getUid(),"No","2");
                friendRef.child(userID).child(user.getUid()).setValue(reqFriend);
                FancyToast.makeText(getBaseContext(), "Request couple send succesfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,true).show();
                startActivity(new Intent(getBaseContext(), TabPrincipalController.class));
                followRef.child(user.getUid()).child(userID).setValue("Yes");
                FancyToast.makeText(getBaseContext(), "Now you are following", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,true).show();
            }
        });

        imgBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFunctions fbFunctions = new FirebaseFunctions();
                String idUser = fbFunctions.getIdUsuarioFire();
                DatabaseReference refBlock = fbFunctions.getReferenceBlockByid(idUser);
                FancyToast.makeText(getBaseContext(), "Has bloqueado al usuario", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,true).show();
                Block block = new Block(idUser,userID,true);
                refBlock.push().setValue(block);
            }
        });

        loadUserInformation();

    }

    private void loadUserInformation() {
        //Intent intent = getIntent();
        //String idFriend = intent.getStringExtra(MESSAGE_KEY);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getKey();
                String friendName = dataSnapshot.child("name").getValue().toString();
                String urlImgProfile = dataSnapshot.child("urlImgProfile").getValue().toString();
                Glide.with(getBaseContext())
                        .load(urlImgProfile)
                        .into(imgCouple);
                txtFriendName.setText(friendName);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
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
        private static final String MESSAGE_KEY = "user";

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

            View rootView = inflater.inflate(R.layout.fragment_friend_profile, container, false);
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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    PostViewerFragment postViewer = new PostViewerFragment();
                    return postViewer;
                case 1:
                    TabBlank tab1 = new TabBlank();
                    return tab1;
                case 2:
                    TabBlank tab2 = new TabBlank();
                    return tab2;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
