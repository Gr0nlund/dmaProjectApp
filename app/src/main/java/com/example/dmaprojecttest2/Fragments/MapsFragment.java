package com.example.dmaprojecttest2.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dmaprojecttest2.MainActivity;
import com.example.dmaprojecttest2.R;
import com.example.dmaprojecttest2.db.HTTPfetchType;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.MalformedURLException;

public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    public static View view;
    GoogleMap map;
    MapView mapView;

    public MapsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_maps, container, false);

        try{
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapsView);
            mapFragment.getMapAsync(MapsFragment.this);
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = view.findViewById(R.id.mapsView);

        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    private Marker myMarker;
    LatLng aaHavn = new LatLng(57.047708,9.930646);

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;

        googleMap.setOnMarkerClickListener(this);

        myMarker = googleMap.addMarker(new MarkerOptions()
                .position(aaHavn)
                .title("1")
                .icon(bitmapDescriptorFromVector(getActivity(), R.drawable.ic_dumpster)));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(aaHavn, 18));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if (marker.equals(myMarker))
        {
            //gets data from database. Everything else is done in the onPostExecute method
            HTTPfetchType asyncTask = new HTTPfetchType(myMarker.getTitle());
            MenuFragment.dumpsterId = Integer.parseInt(myMarker.getTitle());
            asyncTask.execute();

            /*if(MainActivity.fetchTypeResult.get(0)[0] == null){
                //MainActivity.fetchTypeResult.get(0)[0] = "null";
                Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getContext(), MainActivity.fetchTypeResult.get(0)[0], Toast.LENGTH_SHORT).show();
            }*/

            //handle click here
            /*FragmentActivity activity = (FragmentActivity)view.getContext();
            FragmentManager manager = activity.getSupportFragmentManager();

            if (manager.findFragmentById(R.id.frameLayout_menu) != null) {
                MainActivity.destroyMenuFragment(view);
            }
            try {
                MainActivity.createMenuFragment(view);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
        }
        return true;
    }

    //adapted from https://stackoverflow.com/questions/42365658/custom-marker-in-google-maps-in-android-with-vector-asset-icon
    //needed to get vector icon from res/drawable
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
