package com.id12533030.lifediary.view;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LENOVO on 2016/6/1.
 */
public class ArrayListFragment extends ListFragment {
    int mNum;
    private static final String NUM_KEY = "num";
    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    public static ArrayListFragment newInstance(int num) {
        ArrayListFragment f = new ArrayListFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt(NUM_KEY, num);
        f.setArguments(args);

        return f;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt(NUM_KEY) : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        MainFragment mf = new MainFragment();
//        if (getArguments().getInt(NUM_KEY) == 3){
//            TextView tv = (TextView) mf.getView().findViewById(R.id.fragment_main_title_textview);
//            tv.setText("This is assignment 3");
//        }
//        return mf.onCreateView(inflater, container, savedInstanceState);
        return new MainFragment().onCreateView(inflater, container, savedInstanceState);
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setListAdapter(new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1));
//    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        Log.i("FragmentList", "Item clicked: " + id);
//    }
}
