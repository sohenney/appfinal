package com.trashgo.app.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.trashgo.app.MainActivity;
import com.trashgo.app.Model.PloggingData;
import com.trashgo.app.PloggingActivity;
import com.trashgo.app.R;
import com.trashgo.app.activity_profile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 구글맵 추가 - pkdgood
 * 커스텀 핀, 경로 그리기 - pkdgood
 */
public class MapsFragment extends Fragment {

    String ploggingDataStr = null;
    PloggingData ploggingData = null;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            if(ploggingDataStr!=null) {
                ObjectMapper om = new ObjectMapper();
                try {
                    ploggingData = om.readValue(ploggingDataStr, PloggingData.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Log.println(Log.INFO, "MAP", ploggingData.toString());
            }
            LatLng cbnu = new LatLng(36.62827, 127.458843);

            List<LatLng> latLngList = new ArrayList<>();
            if(ploggingData != null) {
                ploggingData.latLngList.forEach(o ->
                        {
                            latLngList.add(new LatLng(o.latitude, o.logitude));
                        }
                );

                // Storing it inside array of strings
                LatLng[] arr = new LatLng[latLngList.size()];
                arr = latLngList.toArray(new LatLng[0]);

                // Converting ArrayList to Array
                // using get() method
//                for (int i = 0; i < latLngList.size(); i++)
//                    arr[i] = latLngList.get(i);





                Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(arr)
                );

//                latLngList.get(0);

                googleMap.addMarker(new MarkerOptions().position(new LatLng(36.6259, 127.4543)).title("줍줍!").icon(BitmapDescriptorFactory.fromResource(R.drawable.empty_trash_64)));
                googleMap.addMarker(new MarkerOptions().position(latLngList.get(0)).title("출발"));
                googleMap.addMarker(new MarkerOptions().position(latLngList.get(latLngList.size()-1)).title("도착"));
                googleMap.addPolyline(new PolylineOptions().add(arr));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng((latLngList.get(0).latitude + latLngList.get(latLngList.size()-1).latitude)/2,
                                (latLngList.get(0).longitude + latLngList.get(latLngList.size()-1).longitude)/2), 15));
            } else {
                googleMap.addMarker(new MarkerOptions().position(cbnu).title("Marker in CBNU"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cbnu, 15));
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ObjectMapper om = new ObjectMapper();
        if(this.getArguments()!= null) {
            if (this.getArguments().getString("ploggingData") != null) {
                ploggingDataStr = this.getArguments().getString("ploggingData");
            }
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}