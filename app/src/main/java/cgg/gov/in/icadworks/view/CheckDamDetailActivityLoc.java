package cgg.gov.in.icadworks.view;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamData;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamResponse;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.model.response.ot.OTData;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.util.Utilities;
import uk.co.senab.photoview.PhotoViewAttacher;

public class CheckDamDetailActivityLoc extends LocBaseActivity {
    CheckDamData checkDamData;
    @BindView(R.id.distTV)
    CustomFontTextView distTV;
    @BindView(R.id.manTV)
    CustomFontTextView manTV;
    @BindView(R.id.vilTV)
    CustomFontTextView vilTV;
    @BindView(R.id.conTV)
    CustomFontTextView conTV;
    @BindView(R.id.latTv)
    CustomFontTextView latTv;
    @BindView(R.id.lonTV)
    CustomFontTextView lonTV;
    @BindView(R.id.details_layout)
    LinearLayout detailsLayout;
    @BindView(R.id.otIDTV)
    CustomFontTextView otIDTV;
    @BindView(R.id.otNameTV)
    CustomFontTextView otNameTV;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.proTv)
    CustomFontTextView proTv;
    @BindView(R.id.resTV)
    CustomFontTextView resTV;
    @BindView(R.id.canalTV)
    CustomFontTextView canalTV;
    @BindView(R.id.ventTV)
    CustomFontTextView ventTV;
    @BindView(R.id.dischargeTV)
    CustomFontTextView dischargeTV;
    @BindView(R.id.tanksTV)
    CustomFontTextView tanksTV;
    @BindView(R.id.cumTV)
    CustomFontTextView cumTV;
    @BindView(R.id.foundationTV)
    CustomFontTextView foundationTV;
    @BindView(R.id.supStrTV)
    CustomFontTextView supStrTV;
    @BindView(R.id.shutterTV)
    CustomFontTextView shutterTV;

    private String foundationVal, superStrVal, shutterVal;
    private String foundationTextVal, superTextVal, shutterTextVal;

    private double otLat, otLng;
    private double distance = 0.0;
    private int defSelection;
    private EmployeeDetailss employeeDetailss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cd_details_activity);
        ButterKnife.bind(this);

        try {
            getSupportActionBar().show();
            getSupportActionBar().setTitle("Checkdam Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Gson gson = new Gson();
            SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
            String string = sharedPreferences.getString("LOGIN_DATA", "");
            defSelection = sharedPreferences.getInt("DEFAULT_SELECTION", -1);

            employeeDetailss = gson.fromJson(string, EmployeeDetailss.class);
            if (defSelection >= 0 && employeeDetailss != null && employeeDetailss.getEmployeeDetail() != null) {
                if (employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation() != null) {
                    if (employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation().equalsIgnoreCase("AEE")
                            || employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation().equalsIgnoreCase("AE")) {
                        fab.setVisibility(View.VISIBLE);
                    } else {
                        fab.setVisibility(View.GONE);
                    }
                }
            } else {
                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
            }


            checkDamData = getIntent().getParcelableExtra("CD_ITEM_DATA");

            if (checkDamData.getLatitude().contains("-")) {
                String[] strings = checkDamData.getLatitude().split("-");
                otLat = ConvertDegreeAngleToDouble(Double.valueOf(strings[0]), Double.valueOf(strings[1]), Double.valueOf(strings[2]));
            }

            if (checkDamData.getLongitude().contains("-")) {
                String[] strings = checkDamData.getLongitude().split("-");
                otLng = ConvertDegreeAngleToDouble(Double.valueOf(strings[0]), Double.valueOf(strings[1]), Double.valueOf(strings[2]));
            }

            if (!(otLat > 0 && otLng > 0)) {
                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
            }

//            distTV.setText(checkDamData.getDistrictname());
//            manTV.setText(checkDamData.getMandalname());
//            vilTV.setText(checkDamData.getVillagename());
//            conTV.setText(checkDamData.getAssemblyname());
//            latTv.setText(checkDamData.getLatitude());
//            lonTV.setText(checkDamData.getLongitude());
//
//
//            otIDTV.setText(checkDamData.getStructureId());
//            otNameTV.setText(checkDamData.getStructurename());
//            proTv.setText(checkDamData.getProjectname());
//            resTV.setText(checkDamData.getReservoirname());
//            canalTV.setText(checkDamData.getCanalname());
//            ventTV.setText(checkDamData.getVentDiaInMm() + "mm");
//            dischargeTV.setText(checkDamData.getDischargeInCusecs() + "cusecs");
//            tanksTV.setText(checkDamData.getTanksToBeFedCount());
//            cumTV.setText(checkDamData.getCumulativeCapacityOfTankInMcft() + "mcft");

            if (checkDamData.getGetItemStatusData() != null && checkDamData.getGetItemStatusData().size() > 0) {
                for (int z = 0; z < checkDamData.getGetItemStatusData().size(); z++) {
                    if (checkDamData.getGetItemStatusData().get(z).getIrrWorkId().equalsIgnoreCase("1")) {
                        foundationTV.setText(checkDamData.getGetItemStatusData().get(z).getStatusName());
                        foundationVal = checkDamData.getGetItemStatusData().get(z).getStatusId();
                        foundationTextVal = checkDamData.getGetItemStatusData().get(z).getStatusName();
                    }

                    if (checkDamData.getGetItemStatusData().get(z).getIrrWorkId().equalsIgnoreCase("2")) {
                        supStrTV.setText(checkDamData.getGetItemStatusData().get(z).getStatusName());
                        superStrVal = checkDamData.getGetItemStatusData().get(z).getStatusId();
                        superTextVal = checkDamData.getGetItemStatusData().get(z).getStatusName();
                    }

                    if (checkDamData.getGetItemStatusData().get(z).getIrrWorkId().equalsIgnoreCase("3")) {
                        shutterTV.setText(checkDamData.getGetItemStatusData().get(z).getStatusName());
                        shutterVal = checkDamData.getGetItemStatusData().get(z).getStatusId();
                        shutterTextVal = checkDamData.getGetItemStatusData().get(z).getStatusName();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
        }


    }

    public double ConvertDegreeAngleToDouble(double degrees, double minutes, double seconds) {
        //Decimal degrees =
        //   whole number of degrees,
        //   plus minutes divided by 60,
        //   plus seconds divided by 3600
        return degrees + (minutes / 60) + (seconds / 3600);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
//        if (checkDamData.getAgmtStatus() == null) {
//            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something) + ". No value found for agreement status", false);
//            return;
//        }
//
//        if (checkDamData.getAgmtStatus().equalsIgnoreCase("NO")) {
//            Utilities.showCustomNetworkAlert(this, checkDamData.getAgmtMsg(), false);
//            return;
//        }

        distance = 0.0;
        if (mCurrentLocation != null && mCurrentLocation.getLatitude() > 0 && mCurrentLocation.getLongitude() > 0) {
            Location src = new Location("Src");
            src.setLatitude(mCurrentLocation.getLatitude());
            src.setLongitude(mCurrentLocation.getLongitude());

            Location dest = new Location("Dest");
            String otLatStr = roundDecimal(otLat, 7);
            dest.setLatitude(Double.parseDouble(otLatStr));
            String otLngStr = roundDecimal(otLng, 7);
            dest.setLongitude(Double.parseDouble(otLngStr));

            distance = calcDistanceKm(src, dest);
            if (distance > 0) {
                String finalDistance = roundDecimal(distance, 7);
                distance = Double.parseDouble(finalDistance);
                Log.i("DISTANCE", "onViewClicked: " + distance);
            }

            if (distance > 100) {
//                if (otData.getRadiusMsg() == null || TextUtils.isEmpty(otData.getRadiusMsg()))
//                    Utilities.showCustomNetworkAlert(this, "Sorry, Status update not allowed, You are not within the 100 meter radius of selected OT", false);
//                else
//                    Utilities.showCustomNetworkAlert(this, otData.getRadiusMsg(), false);

            } else if (distance > 0 && distance <= 100) {
                SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String cdDataStr = gson.toJson(checkDamData);

                editor.putString("ITEM_DATA_FIN", cdDataStr);
                editor.putString("FOUNDATION_VAL", foundationVal);
                editor.putString("SUPER_STR_VAL", superStrVal);
                editor.putString("SHUTTER_VAL", shutterVal);
                editor.putString("FOUNDATION_TEXT_VAL", foundationTextVal);
                editor.putString("SUPER_TEXT_VAL", superTextVal);
                editor.putString("SHUTTER_TEXT_VAL", shutterTextVal);
                editor.commit();

                startActivity(new Intent(CheckDamDetailActivityLoc.this, CDUploadDetailActivityLoc.class));
            } else {
                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
            }
        } else {
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something) + " Unable to get location", false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ot_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        if (checkDamData != null && checkDamData.getPhotoPath() != null && !TextUtils.isEmpty(checkDamData.getPhotoPath().trim())) {
//            menu.findItem(R.id.image_view).setVisible(true);
//        } else {
//            menu.findItem(R.id.image_view).setVisible(false);
//        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.image_view:
//                if (otData.getPhotoPath() != null && !TextUtils.isEmpty(otData.getPhotoPath())) {
//                    displayDialogBox(otData.getPhotoPath());
//                } else {
//                    Toast.makeText(this, "No preview found", Toast.LENGTH_SHORT).show();
//                }
                return true;
            case R.id.image_share:
                try {
                    ScrollView abstractView = getWindow().getDecorView().findViewById(R.id.scrlView);
                    Utilities.takeSCImage(this, abstractView,
                            employeeDetailss.getEmployeeDetail().get(defSelection).getEmpName() + "OT Data");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.nav_map:
                if (!(otLat > 0 && otLng > 0)) {
                    Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
                } else {

                    SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();

                    ArrayList<CheckDamData> arrayList = new ArrayList<>();
                    arrayList.add(checkDamData);

                    CheckDamResponse tempOtResponse = new CheckDamResponse();
                    tempOtResponse.setData(arrayList);
                    editor.putString("CD_DATA", gson.toJson(tempOtResponse));
                    editor.putString("FROM_CLASS", "CD");
                    editor.commit();

                    startActivity(new Intent(this, CDMapsActivity.class));
                }
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayDialogBox(String photo) {
        final Dialog dialog = new Dialog(CheckDamDetailActivityLoc.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);
        final ImageView imageView = dialog.findViewById(R.id.image);
        ImageView close = dialog.findViewById(R.id.close);
        final ProgressBar pb = dialog.findViewById(R.id.progressbar);
        pb.setVisibility(View.VISIBLE);


        Picasso.with(CheckDamDetailActivityLoc.this).load(photo)
                .error(R.drawable.no_image).into(imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                pb.setVisibility(View.GONE);
                PhotoViewAttacher pAttacher;
                pAttacher = new PhotoViewAttacher(imageView);
                pAttacher.update();
            }

            @Override
            public void onError() {
                pb.setVisibility(View.GONE);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }

    private double calcDistanceKm(Location src, Location dest) {
        return src.distanceTo(dest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGpsSwitchStateReceiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                mCurrentLocation = locationResult.getLastLocation();
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mGpsSwitchStateReceiver);
    }

    private BroadcastReceiver mGpsSwitchStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
                    callPermissions();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    public static String roundDecimal(double value, int places) {
        String format = "%." + places + "f";
        return String.format(format, value);
    }

}
