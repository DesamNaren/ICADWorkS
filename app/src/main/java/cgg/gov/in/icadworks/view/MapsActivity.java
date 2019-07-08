package cgg.gov.in.icadworks.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.adapter.MapAdaptor;
import cgg.gov.in.icadworks.model.response.ot.OTData;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.Utilities;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    @BindView(R.id.switchView)
    Button switchView;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    private GoogleMap mMap;
    double latitude;
    double longitude;

    private Marker markername;
    MarkerOptions markerOptions;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    private static final int REQUEST_LOCATION_TURN_ON = 2000;
    boolean markerClicked;
    TextView tvLocInfo;
    Button submitBtn;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private OTResponse otResponse;
    double distance = 0.0;
    String fromClass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        ButterKnife.bind(this);

        requestPermissions();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        try {
            if (!CheckGooglePlayServices()) {
                Toast.makeText(this, "Sorry, Google play services not available in your device", Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            turnOnLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Gson gson = new Gson();
            SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
            String otData = sharedPreferences.getString("OT_DATA", "");
            fromClass = sharedPreferences.getString("FROM_CLASS", "");
            try {
                if (fromClass.equalsIgnoreCase("DASH")) {
                    switchView.setText("SHOW LIST");

                } else if (fromClass.equalsIgnoreCase("OT")) {
                    switchView.setText("OT DETAILS");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            otResponse = gson.fromJson(otData, OTResponse.class);
            progressBar.setVisibility(View.VISIBLE);
            switchView.setVisibility(View.GONE);


        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

    }


    private void setMarkers(final OTResponse otResponse) {
        try {
            if (otResponse.getData().size() > 0) {

                for (int i = 0; i < otResponse.getData().size(); i++) {
                    double lat = 0, lng = 0;
                    final MarkerOptions markerOptions = new MarkerOptions();
                    final OTData otData = otResponse.getData().get(i);
                    if (otData.getLatitude() != null && otData.getLongitude() != null) {

                        Log.e("Lat-Long", otData.getLatitude() + "_" + otData.getLongitude());

                        String latitude = otData.getLatitude().trim().replace("--", "-");
                        String longitude = otData.getLongitude().trim().replace("--", "-");

                        if (latitude.contains("-")) {
                            String[] strings = otData.getLatitude().split("-");
                            lat = ConvertDegreeAngleToDouble(Double.valueOf(strings[0]), Double.valueOf(strings[1]), Double.valueOf(strings[2]));
                        } else {
                            lat = Double.valueOf(latitude);
                        }

                        if (longitude.contains("-")) {
                            String[] strings = otData.getLongitude().split("-");
                            lng = ConvertDegreeAngleToDouble(Double.valueOf(strings[0]), Double.valueOf(strings[1]), Double.valueOf(strings[2]));
                        } else {
                            lng = Double.valueOf(longitude);
                        }

                        LatLng latLng = new LatLng(lat, lng);

                        markerOptions.position(latLng);
                        String otName = "";
                        if (!TextUtils.isEmpty(otData.getStructurename())) {
                            otName = otData.getStructurename();
                        }

                        String finals = " Chain ID: " + otData.getStructureId() + "\n\n" +
                                " OT Name: " + otName + "\n\n" +
                                " OT Number: " + otData.getStructureNo();

                        markerOptions.title(finals);

                        if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {
                            mMap.setMyLocationEnabled(true);
                        }


                        if (otResponse.getData().get(i).getGetItemStatusData() != null && otResponse.getData().get(i).getGetItemStatusData().size() > 0) {
                                int statusId = 0;

                                for (int y = 0; y < otResponse.getData().get(i).getGetItemStatusData().size(); y++) {
                                    statusId += Integer.valueOf(otResponse.getData().get(i).getGetItemStatusData().get(y).getStatusId());
                                }

                                if (statusId > 0 && statusId == 3) {
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.red_ot));
                                }

                                if (statusId > 3 && statusId <= 8) {
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.orange_ot));
                                }

                                if (statusId == 9) {
                                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.green_ot));
                                }


                            if (statusId > 0 && statusId < 3) {
                                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.red_ot));
                            }

                        }


                        markername = mMap.addMarker(markerOptions);

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                markername.showInfoWindow();

                                String title = marker.getTitle().trim();
                                String chainIDVal = title.substring(title.lastIndexOf("\n") + 1);
                                chainIDVal = chainIDVal.substring(chainIDVal.indexOf(":") + 1, chainIDVal.length());


                                for (int i = 0; i < otResponse.getData().size(); i++) {
                                    if (chainIDVal.trim().equalsIgnoreCase(otResponse.getData().get(i).getStructureNo().trim())) {
                                        startActivity(new Intent(MapsActivity.this, OTDetailActivityLoc.class)
                                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                                .putExtra("ITEM_DATA", otResponse.getData().get(i)));
                                        break;
                                    }
                                }
                            }
                        });
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(9));
                    }
                }

            }

            progressBar.setVisibility(View.GONE);
            switchView.setVisibility(View.VISIBLE);

            MapAdaptor customInfoWindow = new MapAdaptor(MapsActivity.this);
            mMap.setInfoWindowAdapter((GoogleMap.InfoWindowAdapter) customInfoWindow);

            if (fromClass.equalsIgnoreCase("OT")) {
                mMap.getUiSettings().setMapToolbarEnabled(true);
                markername.showInfoWindow();
            }

        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);
            switchView.setVisibility(View.VISIBLE);
            e.printStackTrace();
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
        }

    }

    private void turnOnLocation() {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).build();
        googleApiClient.connect();
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        if (ConnectionDetector.isConnectedToInternet(MapsActivity.this)) {
                            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                    .findFragmentById(R.id.map);
                            mapFragment.getMapAsync(MapsActivity.this);


                        } else {
                            AlertDialog.Builder alert = new AlertDialog.Builder(MapsActivity.this);
                            alert.setCancelable(false);
                            alert.setMessage(getResources().getString(R.string.no_internet));
                            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                            alert.show();
                        }

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(MapsActivity.this, REQUEST_LOCATION_TURN_ON);
                        } catch (IntentSender.SendIntentException e) {
                            Toast.makeText(MapsActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Toast.makeText(MapsActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        break;
                    default: {
                        Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(i);
                        break;
                    }
                }
            }
        });

    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result, 0).show();
            }
            return false;
        }
        return true;
    }

    private boolean requestPermissions() {
        boolean requestFlag = false;
        if (ContextCompat.checkSelfPermission(MapsActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
            requestFlag = false;
        } else {
            requestFlag = true;
        }
        return requestFlag;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);

                setMarkers(otResponse);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        CameraUpdate point = CameraUpdateFactory.newLatLng(new LatLng(17.3850, 78.4867));
        if (fromClass.equalsIgnoreCase("DASH")) {
            mMap.moveCamera(point);
        }
        mMap.animateCamera(CameraUpdateFactory.zoomTo(9));
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("You are here");
        // markerOptions.snippet("ward_name\ncirclename\nZone");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
//        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        if (fromClass.equalsIgnoreCase("DASH")) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
        mMap.animateCamera(CameraUpdateFactory.zoomTo(9));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {
                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_LOCATION_TURN_ON) {
            if (resultCode == RESULT_OK) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            } else {
                new AlertDialog.Builder(MapsActivity.this).setCancelable(false).setTitle("Please turn on the location to continue").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        turnOnLocation();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).show();


            }
        }
    }

    public double ConvertDegreeAngleToDouble(double degrees, double minutes, double seconds) {
        //Decimal degrees =
        //   whole number of degrees,
        //   plus minutes divided by 60,
        //   plus seconds divided by 3600
        return degrees + (minutes / 60) + (seconds / 3600);
    }

    @OnClick(R.id.switchView)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}