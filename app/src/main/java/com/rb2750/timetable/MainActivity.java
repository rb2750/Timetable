package com.rb2750.timetable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private BottomNavigationView bottomBar;
    private static HashMap<Integer, HashMap<Integer, Period[]>> dayPeriods = new HashMap<>();
    public static int selected = 0;

    public static int me = 1;
    public static int other = 2;

    public static int dayOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        bottomBar = (BottomNavigationView) findViewById(R.id.navigation);

        selected = bottomBar.getSelectedItemId();

        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (bottomBar.getSelectedItemId() == item.getItemId()) return false;
                selected = item.getItemId();
                tabLayout.removeAllTabs();
                mViewPager.setAdapter(mSectionsPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
                selectToday();
                return true;
            }
        });

        me = bottomBar.getMenu().getItem(0).getItemId();
        other = bottomBar.getMenu().getItem(1).getItemId();

        createMeData();
        createOtherData();

        selectToday();
    }

    public void selectToday() {
        Calendar c = Calendar.getInstance(Locale.UK);
        c.setTime(new Date());

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 2;

        MainActivity.dayOfWeek = dayOfWeek;

        if (tabLayout.getTabCount() - 1 >= dayOfWeek && dayOfWeek > 0)
            tabLayout.getTabAt(dayOfWeek).select();
        else
            tabLayout.getTabAt(0).select();
    }

    public static void createDay(int person, int day, Period... periods) {
        if (!dayPeriods.containsKey(person)) dayPeriods.put(person, new HashMap<Integer, Period[]>());

        HashMap<Integer, Period[]> periodMap = dayPeriods.get(person);
        periodMap.put(day, periods);
    }

    public static void createMeData() {
        int day = 1;
        createDay(me, day,
                new Period("Form", "Sharjeel", "Computing Lab 4", day, 10),
                new Period("IT", "Martyn", "Computing Lab 2", day, 50),
                new Period("IT", "Martyn", "Computing Lab 2", day, 50),
                new Period("VMG", "Sharjeel", "Computing Lab 4", day, 20),
                new Period("Break", "", "", day, 20),
                new Period("IT", "Martyn", "Computing Lab 2", day, 50),
                new Period("Free", "", "", day, 50),
                new Period("Free", "", "", day, 45),
                new Period("Lunch", "", "", day, 45),
                new Period("Maths", "Philip", "Learning Base 13", day, 50),
                new Period("Maths", "Philip", "Learning Base 13", day, 50)
        );
        day = 2;
        createDay(me, day,
                new Period("Form", "Sharjeel", "Computing Lab 4", day, 10),
                new Period("Computer Science", "Colin", "Computing Lab 3", day, 50),
                new Period("Computer Science", "Colin", "Computing Lab 3", day, 50),
                new Period("VMG", "Sharjeel", "Computing Lab 4", day, 20),
                new Period("Break", "", "", day, 20),
                new Period("Computer Science", "Colin", "Computing Lab 3", day, 50),
                new Period("Free", "", "", day, 50),
                new Period("Free", "", "", day, 45),
                new Period("Lunch", "", "", day, 45),
                new Period("Free", "", "", day, 50),
                new Period("Free", "", "", day, 50)
        );
        day = 3;
        createDay(me, day,
                new Period("Form", "Sharjeel", "Computing Lab 4", day, 10),
                new Period("Physics", "Darren", "City", day, 50),
                new Period("Physics", "Darren", "City", day, 50),
                new Period("VMG", "Sharjeel", "Computing Lab 4", day, 20),
                new Period("Break", "", "", day, 20),
                new Period("Physics", "Rory", "City", day, 50),
                new Period("Free", "", "", day, 50),
                new Period("Free", "", "", day, 45),
                new Period("Lunch", "", "", day, 45),
                new Period("Free", "", "", day, 50),
                new Period("Free", "", "", day, 50)
        );
        day = 4;
        createDay(me, day,
                new Period("Form", "Sharjeel", "Computing Lab 4", day, 10),
                new Period("Maths", "Robyn", "Learning Base 12", day, 50),
                new Period("Maths", "Robyn", "Learning Base 12", day, 50),
                new Period("VMG", "Sharjeel", "Computing Lab 4", day, 20),
                new Period("Break", "", "", day, 20),
                new Period("Maths", "Robyn", "Learning Base 12", day, 50),
                new Period("Free", "", "", day, 50),
                new Period("Free", "", "", day, 45),
                new Period("Lunch", "", "", day, 45),
                new Period("Computer Science", "Colin", "Computing Lab 3", day, 50),
                new Period("Computer Science", "Colin", "Computing Lab 3", day, 50)
        );
        day = 5;
        createDay(me, day,
                new Period("Free", "", "", day, 10),
                new Period("Free", "", "", day, 50),
                new Period("Free", "", "", day, 50),
                new Period("Free", "", "", day, 20),
                new Period("Break", "", "", day, 20),
                new Period("IT", "Martyn", "Computing Lab 2", day, 50),
                new Period("IT", "Martyn", "Computing Lab 2", day, 50),
                new Period("IT", "Martyn", "Computing Lab 2", day, 45),
                new Period("Lunch", "", "", day, 45),
                new Period("Physics", "Rory", "City", day, 50),
                new Period("Physics", "Rory", "City", day, 50)
        );
    }

    public static void createOtherData() {

    }

    public static List<DataObject> createDataObjects(int day) {
        return createDataObjects(day, selected);
    }

    public static List<DataObject> createDataObjects(int day, int person) {
        List<DataObject> result = new ArrayList<>();
        if (!dayPeriods.get(person).containsKey(day)) return result;
        try {
            int startHour = 8;
            int minute = person == me ? 30 : 55;

            Calendar c = Calendar.getInstance(Locale.UK);
            c.setTime(new Date());

            int currentHour = c.get(Calendar.HOUR_OF_DAY);
            int currentMinute = c.get(Calendar.MINUTE);
            int currentMinutes = (currentHour * 60) + currentMinute;

            for (Period period : dayPeriods.get(person).get(day)) {
                if (period == null) continue;
                int start_Hour = startHour + (minute / 60);
                int start_Minute = minute % 60;
                minute += period.getLength();
                int end_Hour = startHour + (minute / 60);
                int end_Minute = minute % 60;

                int start_Minutes = (start_Hour * 60) + start_Minute;
                int end_Minutes = (end_Hour * 60) + end_Minute;

                int color = Color.WHITE;
                boolean current = false;
                if (currentMinutes > end_Minutes || dayOfWeek != day - 1)
                    color = Color.parseColor("#E0E0E0");
                else if (currentMinutes >= start_Minutes && currentMinutes <= end_Minutes) {
                    color = Color.parseColor("#B3E5FC");
                    current = true;
                }

                DataObject object = new DataObject(period.getSubject(), period.getTeacher(), period.getLength(), period.getDay(), start_Hour, start_Minute, end_Hour, end_Minute, period.getClassLocation(), color, current);
                result.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings)
//        {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.timetable, container, false);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(inflater.getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new MyRecyclerViewAdapter(new ArrayList<DataObject>());
            mRecyclerView.setAdapter(mAdapter);

            int dayNumber = getArguments().getInt(ARG_SECTION_NUMBER);

            List<DataObject> objects = createDataObjects(dayNumber);
            for (int i = 0; i < objects.size(); i++)
                ((MyRecyclerViewAdapter) mAdapter).addItem(objects.get(i));
            ((MyRecyclerViewAdapter) mAdapter).setOnItemLongClickListener(new MyRecyclerViewAdapter.LongClickListener() {
                @Override
                public void onItemLongClick(int position, View v) {
                    Toast.makeText(inflater.getContext(), position + "", Toast.LENGTH_SHORT).show();
                }
            });
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Mon";
                case 1:
                    return "Tues";
                case 2:
                    return "Wed";
                case 3:
                    return "Thurs";
                case 4:
                    return "Fri";
                default:
                    return "Mon";
            }
        }
    }
}
