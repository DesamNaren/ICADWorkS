//package cgg.gov.in.icadworks.fragment;
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.IntentSender;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.drawable.Drawable;
//import android.location.Location;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.content.ContextCompat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.PendingResult;
//import com.google.android.gms.common.api.ResultCallback;
//import com.google.android.gms.common.api.Status;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.location.LocationSettingsResult;
//import com.google.android.gms.location.LocationSettingsStatusCodes;
//import com.google.android.gms.maps.CameraUpdate;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptor;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.CameraPosition;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.gson.Gson;
//import com.google.gson.JsonSyntaxException;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//import cgg.gov.in.icadworks.R;
//import cgg.gov.in.icadworks.adapter.MapAdaptor;
//import cgg.gov.in.icadworks.model.response.ot.OTData;
//import cgg.gov.in.icadworks.model.response.ot.OTResponse;
//import cgg.gov.in.icadworks.util.ConnectionDetector;
//import cgg.gov.in.icadworks.view.MapsActivity;
//
//import static android.app.Activity.RESULT_OK;
//
//public class OTMapFragment extends FragmentActivity implements OnMapReadyCallback,
//        GoogleApiClient.ConnectionCallbacks,
//        GoogleApiClient.OnConnectionFailedListener,
//        LocationListener {
//
//    private GoogleMap mMap;
//    double latitude;
//    double longitude;
//
//    double newMarkerLatitude;
//    double newMarkerLongitude;
//    private Marker markername;
//
//    GoogleApiClient mGoogleApiClient;
//    Location mLastLocation;
//    Marker mCurrLocationMarker;
//    LocationRequest mLocationRequest;
//    private static final int REQUEST_LOCATION_TURN_ON = 2000;
//    boolean markerClicked;
//    TextView tvLocInfo;
//    Button submitBtn;
//    private static final int PERMISSION_REQUEST_CODE = 200;
//    private OTResponse otResponse;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for getBaseContext() fragment
//        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
//        requestPermissions();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            checkLocationPermission();
//        }
//
//        //Check if Google Play Services Available or not
//        try {
//            if (!CheckGooglePlayServices()) {
//                //finish();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            turnOnLocation();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            Gson gson = new Gson();
//            SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
//            String otData = sharedPreferences.getString("OT_DATA", "");
//            otResponse = gson.fromJson(otData, OTResponse.class);
//        } catch (JsonSyntaxException e) {
//            e.printStackTrace();
//        }
//
//        return rootView;
//    }
//
//    private void setMarkers(OTResponse otResponse) {
//        try {
//            for (int i = 0; i < otResponse.getData().size(); i++) {
//                double lat = 0, lng = 0;
//                MarkerOptions markerOptions = new MarkerOptions();
//                OTData otData = otResponse.getData().get(i);
//                if (otData.getLatitude() != null && otData.getLongitude() != null) {
//
//                    if(otData.getLatitude().contains("-")){
//                        String[] strings = otData.getLatitude().split("-");
//                        lat = ConvertDegreeAngleToDouble(Double.valueOf(strings[0]), Double.valueOf(strings[1]), Double.valueOf(strings[2]));
//                    }
//
//                    if(otData.getLongitude().contains("-")){
//                        String[] strings = otData.getLongitude().split("-");
//                        lng = ConvertDegreeAngleToDouble(Double.valueOf(strings[0]), Double.valueOf(strings[1]), Double.valueOf(strings[2]));
//                    }
//
//                    LatLng latLng = new LatLng(lat, lng);
//
//                    markerOptions.position(latLng);
//                    String finals = " OT ID: " + otData.getStructureId() + "\n" + " OT Name: " + otData.getStructurename() + "\n Location: " + otData.getVillagename() ;
//
//                    markerOptions.title(finals);
//                    if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//                        mMap.setMyLocationEnabled(true);
//                    }
//
//
//                    for(int x=0;x<otResponse.getData().get(i).getGetItemStatusData().size();x++){
//                        if(otResponse.getData().get(i).getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("1")
//                                && otResponse.getData().get(i).getGetItemStatusData().get(x).getStatusId().contains("1")){
//                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                        }
//
//                        if(otResponse.getData().get(i).getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("1")
//                                && otResponse.getData().get(i).getGetItemStatusData().get(x).getStatusId().contains("2")){
//                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
//                        }
//
//                        if(otResponse.getData().get(i).getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("1")
//                                && otResponse.getData().get(i).getGetItemStatusData().get(x).getStatusId().contains("3")){
//                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//                        }
//
//                        if(otResponse.getData().get(i).getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("2")
//                                && otResponse.getData().get(i).getGetItemStatusData().get(x).getStatusId().contains("1")){
//                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                        }
//
//                        if(otResponse.getData().get(i).getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("2")
//                                && otResponse.getData().get(i).getGetItemStatusData().get(x).getStatusId().contains("2")){
//                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
//                        }
//
//                        if(otResponse.getData().get(i).getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("2")
//                                && otResponse.getData().get(i).getGetItemStatusData().get(x).getStatusId().contains("3")){
//                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//                        }
//
//
//                        if(otResponse.getData().get(i).getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("3")
//                                && otResponse.getData().get(i).getGetItemStatusData().get(x).getStatusId().contains("1")){
//                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                        }
//
//                        if(otResponse.getData().get(i).getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("3")
//                                && otResponse.getData().get(i).getGetItemStatusData().get(x).getStatusId().contains("2")){
//                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
//                        }
//
//                        if(otResponse.getData().get(i).getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("3")
//                                && otResponse.getData().get(i).getGetItemStatusData().get(x).getStatusId().contains("3")){
//                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//                        }
//
//                    }
//
////                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                    //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_parking));
//
//                    markername = mMap.addMarker(markerOptions);
//                    final int finalI = i;
//                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//                        @Override
//                        public void onInfoWindowClick(Marker marker) {
//                            Uri.Builder builder = new Uri.Builder();
//                            builder.scheme("https").authority("www.google.com").appendPath("maps").appendPath("dir").appendPath("").appendQueryParameter("api", "1").appendQueryParameter("destination", marker.getPosition().latitude + "," + marker.getPosition().longitude);
//                            String url = builder.build().toString();
//                            Intent i = new Intent(Intent.ACTION_VIEW);
//                            i.setData(Uri.parse(url));
//                            startActivity(i);
//
//                        }
//                    });
//                    //move map camera
//                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                    mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
//                } else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
//                    //                builder.setMessage(otData.getStatus());
//                    builder.setCancelable(false);
//                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//                    builder.show();
//
//                }
//            }
//
//            MapAdaptor customInfoWindow = new MapAdaptor(getBaseContext());
//            mMap.setInfoWindowAdapter((GoogleMap.InfoWindowAdapter) customInfoWindow);
//
//
//            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                @Override
//                public boolean onMarkerClick(Marker marker) {
//                    markername.showInfoWindow();
//
//                    return false;
//                }
//            });
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void turnOnLocation() {
//        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(getBaseContext()).addApi(LocationServices.API).build();
//        googleApiClient.connect();
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(10000);
//        locationRequest.setFastestInterval(10000 / 2);
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
//        builder.setAlwaysShow(true);
//
//        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
//        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
//            @Override
//            public void onResult(LocationSettingsResult result) {
//                final Status status = result.getStatus();
//                switch (status.getStatusCode()) {
//                    case LocationSettingsStatusCodes.SUCCESS:
//                        if (ConnectionDetector.isConnectedToInternet(getBaseContext())) {
//                            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                                    .findFragmentById(R.id.map);
//                            mapFragment.getMapAsync(getBaseContext());
//
//
//                        } else {
//                            AlertDialog.Builder alert = new AlertDialog.Builder(getBaseContext());
//                            alert.setCancelable(false);
//                            alert.setMessage(getResources().getString(R.string.no_internet));
//                            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                    finish();
//                                }
//                            });
//                            alert.show();
//                        }
//
//                        break;
//                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                        try {
//                            status.startResolutionForResult(getBaseContext(), REQUEST_LOCATION_TURN_ON);
//                        } catch (IntentSender.SendIntentException e) {
//                            Toast.makeText(getBaseContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
//                        }
//                        break;
//                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                        Toast.makeText(getBaseContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
//                        break;
//                    default: {
//                        Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        startActivity(i);
//                        break;
//                    }
//                }
//            }
//        });
//
//    }
//
//    private boolean CheckGooglePlayServices() {
//        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
//        int result = googleAPI.isGooglePlayServicesAvailable(getBaseContext());
//        if (result != ConnectionResult.SUCCESS) {
//            if (googleAPI.isUserResolvableError(result)) {
//                googleAPI.getErrorDialog(getBaseContext(), result, 0).show();
//            }
//            return false;
//        }
//        return true;
//    }
//
//
//    private boolean requestPermissions() {
//        boolean requestFlag = false;
//        if (ContextCompat.checkSelfPermission(getBaseContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(getBaseContext(),
//                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSION_REQUEST_CODE);
//            requestFlag = false;
//        } else {
//            requestFlag = true;
//        }
//        return requestFlag;
//    }
//
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//
//        //Initialize Google Play Services
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(getBaseContext(),
//                    Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                buildGoogleApiClient();
//                mMap.setMyLocationEnabled(true);
//
//                setMarkers(otResponse);
//            }
//        } else {
//            buildGoogleApiClient();
//            mMap.setMyLocationEnabled(true);
//        }
//
//        CameraUpdate point = CameraUpdateFactory.newLatLng(new LatLng(17.3850, 78.4867));
//        mMap.moveCamera(point);
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(9));
//    }
//
//    protected synchronized void buildGoogleApiClient() {
//        mGoogleApiClient = new GoogleApiClient.Builder(getBaseContext())
//                .addConnectionCallbacks(getBaseContext())
//                .addOnConnectionFailedListener(getBaseContext())
//                .addApi(LocationServices.API)
//                .build();
//        mGoogleApiClient.connect();
//    }
//
//    @Override
//    public void onConnected(Bundle bundle) {
//        mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(1000);
//        mLocationRequest.setFastestInterval(1000);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//        if (ContextCompat.checkSelfPermission(getBaseContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, getBaseContext());
//        }
//    }
//
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        mLastLocation = location;
//        if (mCurrLocationMarker != null) {
//            mCurrLocationMarker.remove();
//        }
//
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("You are here");
//        // markerOptions.snippet("ward_name\ncirclename\nZone");
////        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
////        mCurrLocationMarker = mMap.addMarker(markerOptions);
//
//        //move map camera
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
//
//        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, getBaseContext());
//        }
//    }
//
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//
//    }
//
//    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
//
//    public boolean checkLocationPermission() {
//        if (ContextCompat.checkSelfPermission(getBaseContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(getBaseContext(),
//                    Manifest.permission.ACCESS_FINE_LOCATION)) {
//                ActivityCompat.requestPermissions(getBaseContext(),
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_LOCATION);
//
//
//            } else {
//                ActivityCompat.requestPermissions(getBaseContext(),
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_LOCATION);
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_LOCATION: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    if (ContextCompat.checkSelfPermission(getBaseContext(),
//                            Manifest.permission.ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//
//                        if (mGoogleApiClient == null) {
//                            buildGoogleApiClient();
//                        }
//                        mMap.setMyLocationEnabled(true);
//                    }
//
//                } else {
//                }
//                return;
//            }
//        }
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_LOCATION_TURN_ON) {
//            if (resultCode == RESULT_OK) {
//                Intent intent = getBaseContext().getIntent();
//                getBaseContext().finish();
//                startActivity(intent);
//            } else {
//                new AlertDialog.Builder(getBaseContext()).setCancelable(false).setTitle("Please turn on the location to continue").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        turnOnLocation();
//
//                    }
//                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        getBaseContext().finish();
//                    }
//                }).show();
//
//
//            }
//        }
//    }
//
//    public double ConvertDegreeAngleToDouble( double degrees, double minutes, double seconds ) {
//        //Decimal degrees =
//        //   whole number of degrees,
//        //   plus minutes divided by 60,
//        //   plus seconds divided by 3600
//
//        return degrees + (minutes/60) + (seconds/3600);
//    }
//
//}
