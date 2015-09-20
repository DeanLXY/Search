package com.example.search;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CollectionFragment extends Fragment {

     @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);

    }
     @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
    		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
 
    	return inflater.inflate(R.layout.collection_fragment, container, false);
    }
}
