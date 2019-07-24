//package cgg.gov.in.icadworks.view;
//
//import android.Manifest;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.location.LocationManager;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.FileProvider;
//import android.support.v7.widget.CardView;
//import android.text.TextUtils;
//import android.text.format.DateFormat;
//import android.util.Base64;
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationResult;
//import com.google.gson.Gson;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Locale;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import cgg.gov.in.icadworks.R;
//import cgg.gov.in.icadworks.custom.CustomFontTextView;
//import cgg.gov.in.icadworks.interfaces.UpdateOTView;
//import cgg.gov.in.icadworks.localdb.DatabaseHelper;
//import cgg.gov.in.icadworks.model.request.OTItemReq;
//import cgg.gov.in.icadworks.model.request.OTUpdateRequest;
//import cgg.gov.in.icadworks.model.response.itemStatus.ItemStatusResponse;
//import cgg.gov.in.icadworks.model.response.ot.OTData;
//import cgg.gov.in.icadworks.model.response.ot.OTUpdateResponse;
//import cgg.gov.in.icadworks.presenter.UpdateOTPresenter;
//import cgg.gov.in.icadworks.util.Utilities;
//
//public class UploadDetailActivityLocOffline extends LocBaseActivity implements UpdateOTView {
//
//    private static final int REQUEST_WRITE_PERMISSION = 20;
//    private static final int PHOTO_REQUEST = 10;
//    File imageFile;
//
//    @BindView(R.id.otIDTV)
//    CustomFontTextView otIDTV;
//    @BindView(R.id.otNameTV)
//    CustomFontTextView otNameTV;
//    @BindView(R.id.cardItem)
//    CardView cardItem;
//    @BindView(R.id.imageIV)
//    ImageView imageIV;
//    @BindView(R.id.submitBtn)
//    CustomFontTextView submitBtn;
//    @BindView(R.id.foundationSpinner)
//    Spinner foundationSpinner;
//    @BindView(R.id.supStrSpinner)
//    Spinner supStrSpinner;
//    @BindView(R.id.shuttersSpinner)
//    Spinner shuttersSpinner;
//    @BindView(R.id.foundationCard)
//    CardView foundationCard;
//    @BindView(R.id.superCard)
//    CardView superCard;
//    @BindView(R.id.shutterCard)
//    CardView shutterCard;
//    @BindView(R.id.progress)
//    ProgressBar progress;
//    private OTData otData;
//
//    private UpdateOTPresenter updateOTPresenter;
//
//    ItemStatusResponse itemStatusResponse;
//    String foundationStatusID, superStrID, shutterStatusID;
//    String foundationTextVal, superTextVal, shutterTextVal;
//    private String defUsername, defUserPwd;
//    private String photoStr;
//    private String stoType;
//    private DatabaseHelper db;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.uplolad_ot_detail);
//        ButterKnife.bind(this);
//
//        db = new DatabaseHelper(this);
//
//        try {
//            getSupportActionBar().show();
//            getSupportActionBar().setTitle("Work Progress");
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        updateOTPresenter = new UpdateOTPresenter();
//        updateOTPresenter.attachView(this);
//
//        foundationSpinner.setOnItemSelectedListener(onSpinnerClick);
//        supStrSpinner.setOnItemSelectedListener(onSpinnerClick);
//        shuttersSpinner.setOnItemSelectedListener(onSpinnerClick);
//
//        try {
//            Gson gson = new Gson();
//            SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
//            defUsername = sharedPreferences.getString("DEFAULT_USER_NAME", "");
//            defUserPwd = sharedPreferences.getString("DEFAULT_USER_PWD", "");
//
//            String otDataStr = sharedPreferences.getString("ITEM_DATA_FIN", "");
//            otData = gson.fromJson(otDataStr, OTData.class);
//
//            otIDTV.setText(otData.getStructureId());
//            otNameTV.setText(otData.getStructurename());
//
//            foundationStatusID = sharedPreferences.getString("FOUNDATION_VAL", "");
//            superStrID = sharedPreferences.getString("SUPER_STR_VAL", "");
//            shutterStatusID = sharedPreferences.getString("SHUTTER_VAL", "");
//            stoType = sharedPreferences.getString("STO_TYPE", "");
//
//            if (stoType!=null&& stoType.equalsIgnoreCase("offline")) {
//                submitBtn.setText("Save");
//            }
//
//            foundationTextVal = sharedPreferences.getString("FOUNDATION_TEXT_VAL", "");
//            superTextVal = sharedPreferences.getString("SUPER_TEXT_VAL", "");
//            shutterTextVal = sharedPreferences.getString("SHUTTER_TEXT_VAL", "");
//
//            String statusMasterData = sharedPreferences.getString("STATUS_MASTER_DATA", "");
//            itemStatusResponse = gson.fromJson(statusMasterData, ItemStatusResponse.class);
//
//            ArrayList<String> statusTypes = new ArrayList<>();
//            if (itemStatusResponse != null && itemStatusResponse.getData() != null && itemStatusResponse.getData().size() > 0) {
//                for (int z = 0; z < itemStatusResponse.getData().size(); z++) {
//                    statusTypes.add(itemStatusResponse.getData().get(z).getStatusName());
//                }
//
//                Utilities.loadSpinnerSetSelectedItem(this, statusTypes, foundationSpinner, "");
//                Utilities.loadSpinnerSetSelectedItem(this, statusTypes, supStrSpinner, "");
//                Utilities.loadSpinnerSetSelectedItem(this, statusTypes, shuttersSpinner, "");
//
//
//                if (!TextUtils.isEmpty(foundationStatusID)) {
//                    String foundationStatusName = null;
//                    for (int z = 0; z < itemStatusResponse.getData().size(); z++) {
//                        if (itemStatusResponse.getData().get(z).getStatusId().equalsIgnoreCase(foundationStatusID)) {
//                            foundationStatusName = itemStatusResponse.getData().get(z).getStatusName();
//                            break;
//                        }
//                    }
//
//
//                    if (!TextUtils.isEmpty(foundationStatusName) && foundationStatusID.equalsIgnoreCase("1")) {
//                        Utilities.loadSpinnerSetSelectedItem(this, statusTypes, foundationSpinner, foundationStatusName);
//
//                    } else if (!TextUtils.isEmpty(foundationStatusName) && foundationStatusID.equalsIgnoreCase("2")) {
//                        ArrayList<String> arrayList = new ArrayList<>();
//                        for (int y = 0; y < statusTypes.size(); y++) {
//                            if (y != 0) {
//                                arrayList.add(statusTypes.get(y));
//                            }
//                        }
//                        Utilities.loadSpinnerSetSelectedItem(this, arrayList, foundationSpinner, foundationStatusName);
//
//
//                    } else if (!TextUtils.isEmpty(foundationStatusName) && foundationStatusID.equalsIgnoreCase("3")) {
//
//                        Utilities.loadSpinnerSetSelectedItem(this, statusTypes, foundationSpinner, foundationStatusName);
//                        foundationSpinner.setEnabled(false);
//                        superCard.setVisibility(View.VISIBLE);
//                        shutterCard.setVisibility(View.VISIBLE);
//                    } else {
//                        superCard.setVisibility(View.GONE);
//                        shutterCard.setVisibility(View.GONE);
//                    }
//                }
//            } else {
//                Utilities.loadSpinnerSetSelectedItem(this, statusTypes, foundationSpinner, "");
//            }
//
//
//            if (!TextUtils.isEmpty(superStrID)) {
//
//                String superStatusName = null;
//                for (int z = 0; z < itemStatusResponse.getData().size(); z++) {
//                    if (itemStatusResponse.getData().get(z).getStatusId().equalsIgnoreCase(superStrID)) {
//                        superStatusName = itemStatusResponse.getData().get(z).getStatusName();
//                        break;
//                    }
//                }
//
//                if (!TextUtils.isEmpty(superStatusName) && superStrID.equalsIgnoreCase("1")) {
//                    Utilities.loadSpinnerSetSelectedItem(this, statusTypes, supStrSpinner, superStatusName);
//
//                } else if (!TextUtils.isEmpty(superStatusName) && superStrID.equalsIgnoreCase("2")) {
//                    ArrayList<String> arrayList = new ArrayList<>();
//                    for (int y = 0; y < statusTypes.size(); y++) {
//                        if (y != 0) {
//                            arrayList.add(statusTypes.get(y));
//                        }
//                    }
//                    Utilities.loadSpinnerSetSelectedItem(this, arrayList, supStrSpinner, superStatusName);
//
//
//                } else if (!TextUtils.isEmpty(superStatusName) && superStrID.equalsIgnoreCase("3")) {
//                    Utilities.loadSpinnerSetSelectedItem(this, statusTypes, supStrSpinner, superStatusName);
//                    supStrSpinner.setEnabled(false);
//                }
//            } else {
//                Utilities.loadSpinnerSetSelectedItem(this, statusTypes, supStrSpinner, "");
//            }
//
//
//            if (!TextUtils.isEmpty(shutterStatusID)) {
//
//                String shutterStatusName = null;
//                for (int z = 0; z < itemStatusResponse.getData().size(); z++) {
//                    if (itemStatusResponse.getData().get(z).getStatusId().equalsIgnoreCase(shutterStatusID)) {
//                        shutterStatusName = itemStatusResponse.getData().get(z).getStatusName();
//                        break;
//                    }
//                }
//
//
//                if (!TextUtils.isEmpty(shutterStatusName) && shutterStatusID.equalsIgnoreCase("1")) {
//                    Utilities.loadSpinnerSetSelectedItem(this, statusTypes, shuttersSpinner, shutterStatusName);
//
//                } else if (!TextUtils.isEmpty(shutterStatusName) && shutterStatusID.equalsIgnoreCase("2")) {
//                    ArrayList<String> arrayList = new ArrayList<>();
//                    for (int y = 0; y < statusTypes.size(); y++) {
//                        if (y != 0) {
//                            arrayList.add(statusTypes.get(y));
//                        }
//                    }
//                    Utilities.loadSpinnerSetSelectedItem(this, arrayList, shuttersSpinner, shutterStatusName);
//
//
//                } else if (!TextUtils.isEmpty(shutterStatusName) && shutterStatusID.equalsIgnoreCase("3")) {
//                    Utilities.loadSpinnerSetSelectedItem(this, statusTypes, shuttersSpinner, shutterStatusName);
//                    shuttersSpinner.setEnabled(false);
//                }
//            } else {
//                Utilities.loadSpinnerSetSelectedItem(this, statusTypes, shuttersSpinner, "");
//            }
//        } catch (Exception e) {
//            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
//            e.printStackTrace();
//        }
//
//    }
//
//    AdapterView.OnItemSelectedListener onSpinnerClick = new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            switch (adapterView.getId()) {
//                case R.id.foundationSpinner:
//                    String foundVal = adapterView.getSelectedItem().toString().trim();
//                    if (foundVal.contains("Completed")) {
//                        superCard.setVisibility(View.VISIBLE);
//                        shutterCard.setVisibility(View.VISIBLE);
//                    } else {
//                        superCard.setVisibility(View.GONE);
//                        shutterCard.setVisibility(View.GONE);
//                    }
//                    break;
//
//                case R.id.supStrSpinner:
//                    String superVal = adapterView.getSelectedItem().toString().trim();
//                    break;
//                case R.id.shuttersSpinner:
//                    String shutterVal = adapterView.getSelectedItem().toString().trim();
//                    break;
//            }
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> adapterView) {
//
//        }
//    };
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case REQUEST_WRITE_PERMISSION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    boolean foundationFlag;
//                    if (foundationTextVal.equalsIgnoreCase(foundationSpinner.getSelectedItem().toString())) {
//                        foundationFlag = false;
//                    } else {
//                        foundationFlag = true;
//                    }
//
//                    boolean superFlag;
//                    if (superTextVal.equalsIgnoreCase(supStrSpinner.getSelectedItem().toString())) {
//                        superFlag = false;
//                    } else {
//                        superFlag = true;
//                    }
//
//                    boolean shutterFlag;
//                    if (shutterTextVal.equalsIgnoreCase(shuttersSpinner.getSelectedItem().toString())) {
//                        shutterFlag = false;
//                    } else {
//                        shutterFlag = true;
//                    }
//
//
//                    if (foundationFlag || superFlag || shutterFlag) {
//
//                        if (stoType.equalsIgnoreCase("offline")) {
//                            //cap from gallery
//
//
//                            Intent i = new Intent(
//                                    Intent.ACTION_PICK,
//                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                            startActivityForResult(i, 200);
//                        } else if (mCurrentLocation != null && mCurrentLocation.getLatitude() > 0 && mCurrentLocation.getLongitude() > 0) {
//                            takePicture();
//                        }
//
//                        //add another condition with low network -TODO (else)
//                    } else {
//                        if (stoType.equalsIgnoreCase("offline"))
//                            Utilities.showCustomNetworkAlert(this, "Please update the status to upload photo", false);
//                        else
//                            Utilities.showCustomNetworkAlert(this, "Please update the status to capture photo", false);
//                    }
//
//                } else {
//                    Toast.makeText(UploadDetailActivityLocOffline.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
//                }
//        }
//    }
//
//    private void takePicture() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, PHOTO_REQUEST);
////        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////        fileUri = getOutputMediaFileUri(1);
////        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
////        startActivityForResult(intent, PHOTO_REQUEST);
//    }
//
//    private File getOutputMediaFile(int type) {
//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
//                getString(R.string.app_name));
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                Log.d("TAG", "Oops! Failed create " + "Android File Upload"
//                        + " directory");
//                return null;
//            }
//        }
//        File mediaFile;
//        if (type == 1) {
//            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
//                    .format(new Date());
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator
//                    + "IMG_" + timeStamp + ".jpg");
//        } else {
//            return null;
//        }
//        return mediaFile;
//    }
//
//    public Uri getOutputMediaFileUri(int type) {
//        imageFile = getOutputMediaFile(type);
//        Uri imageUri = FileProvider.getUriForFile(
//                UploadDetailActivityLocOffline.this,
//                "cgg.gov.in.icadworks.provider",
//                imageFile);
//        return imageUri;
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PHOTO_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                onCaptureImageResult(data);
//            } else if (resultCode == RESULT_CANCELED) {
//                Toast.makeText(getApplicationContext(),
//                        "User cancelled image capture", Toast.LENGTH_SHORT)
//                        .show();
//            } else {
//                Toast.makeText(getApplicationContext(),
//                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        }
//
//
//        if (requestCode == 200 && resultCode == RESULT_OK && null != data) {
//
//            Uri imageUri = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//                bitmap = ProcessingOfflineBitmap(bitmap);
//                imageIV.setImageBitmap(bitmap);
//                photoStr = convertBase64(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Exception in image from gallery", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    }
//
//    private void onCaptureImageResult(Intent data) {
//        try {
//            if (data != null) {
//                Bitmap capturePhotoBitmap = (Bitmap) data.getExtras().get("data");
//                Bitmap bmp = ProcessingBitmap(capturePhotoBitmap);
//                imageIV.setImageBitmap(bmp);
//                photoStr = convertBase64(bmp);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private String convertBase64(Bitmap bitmap) {
//        String base64 = "";
//        try {
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//            byte[] byteArray = byteArrayOutputStream.toByteArray();
//            base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return base64;
//    }
//
//    @OnClick({R.id.imageIV, R.id.submitBtn})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.imageIV:
//                ActivityCompat.requestPermissions(UploadDetailActivityLocOffline.this, new
//                        String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_WRITE_PERMISSION);
//                break;
//            case R.id.submitBtn:
//                boolean foundationFlag;
//                if (foundationTextVal.equalsIgnoreCase(foundationSpinner.getSelectedItem().toString())) {
//                    foundationFlag = false;
//                } else {
//                    foundationFlag = true;
//                }
//
//                boolean superFlag;
//                if (superTextVal.equalsIgnoreCase(supStrSpinner.getSelectedItem().toString())) {
//                    superFlag = false;
//                } else {
//                    superFlag = true;
//                }
//
//                boolean shutterFlag;
//                if (shutterTextVal.equalsIgnoreCase(shuttersSpinner.getSelectedItem().toString())) {
//                    shutterFlag = false;
//                } else {
//                    shutterFlag = true;
//                }
//
//                if (foundationFlag || superFlag || shutterFlag) {
//                    if (photoStr != null && !photoStr.equalsIgnoreCase("")) {
//                        ArrayList<OTItemReq> otItemReqs = new ArrayList<>();
//                        OTUpdateRequest otUpdateRequest = new OTUpdateRequest();
//                        otUpdateRequest.setStructureId(otData.getStructureId());
//                        otUpdateRequest.setStructureNo(otData.getStructureNo());
//                        otUpdateRequest.setPhoto(photoStr);
//                        otUpdateRequest.setUser(defUsername);
//                        otUpdateRequest.setPassword(defUserPwd);
//
//                        String foundVal = foundationSpinner.getSelectedItem().toString();
//                        String supStrVal = supStrSpinner.getSelectedItem().toString();
//                        String shutterVal = shuttersSpinner.getSelectedItem().toString();
//
//
//                        if (foundationCard.getVisibility() == View.VISIBLE && !TextUtils.isEmpty(foundVal)) {
//                            for (int z = 0; z < itemStatusResponse.getData().size(); z++) {
//                                if (foundVal.contains(itemStatusResponse.getData().get(z).getStatusName())) {
//                                    OTItemReq otItemReq = new OTItemReq();
//                                    otItemReq.setIrrWorkId("1");
//                                    otItemReq.setStatusId(itemStatusResponse.getData().get(z).getStatusId());
//                                    otItemReqs.add(otItemReq);
//                                    break;
//                                }
//                            }
//                        }
//
//                        if (superCard.getVisibility() == View.VISIBLE && !TextUtils.isEmpty(supStrVal)) {
//                            for (int z = 0; z < itemStatusResponse.getData().size(); z++) {
//                                if (supStrVal.contains(itemStatusResponse.getData().get(z).getStatusName())) {
//                                    OTItemReq otItemReq = new OTItemReq();
//                                    otItemReq.setIrrWorkId("2");
//                                    otItemReq.setStatusId(itemStatusResponse.getData().get(z).getStatusId());
//                                    otItemReqs.add(otItemReq);
//                                    break;
//                                }
//                            }
//                        }
//
//                        if (shutterCard.getVisibility() == View.VISIBLE && !TextUtils.isEmpty(shutterVal)) {
//                            for (int z = 0; z < itemStatusResponse.getData().size(); z++) {
//                                if (shutterVal.contains(itemStatusResponse.getData().get(z).getStatusName())) {
//                                    OTItemReq otItemReq = new OTItemReq();
//                                    otItemReq.setIrrWorkId("3");
//                                    otItemReq.setStatusId(itemStatusResponse.getData().get(z).getStatusId());
//                                    otItemReqs.add(otItemReq);
//                                    break;
//                                }
//                            }
//                        }
//
//
//                        if (otItemReqs.size() > 0) {
//                            otUpdateRequest.setOTItemReq(otItemReqs);
//
//
//                            if(stoType.equalsIgnoreCase("offline")){
//
//                                Gson gson = new Gson();
//                                String ots = gson.toJson(otUpdateRequest.getOTItemReq());
//                                otUpdateRequest.setOts(ots);
//
//
//                                OTUpdateRequest ot = db.getOT(otUpdateRequest.getStructureNo()
//                                ,otUpdateRequest.getEmpId(), otUpdateRequest.getDes(), otUpdateRequest.getPost());
//
//                                if (ot != null) {
//                                    long id = db.updateNote(otUpdateRequest);
//                                    if (id > 0) {
//                                        Utilities.showCustomNetworkAlertSuccess(this, "OT : " + otUpdateRequest.getStructureId() + " Updated successfully");
//                                    } else {
//                                        Utilities.showCustomNetworkAlert(this, "Failed to save, please try again", false);
//                                    }
//                                }else {
//                                    long id = db.insertOT(otUpdateRequest);
//                                    if (id > 0) {
//                                        Utilities.showCustomNetworkAlertSuccess(this, "OT : " + otUpdateRequest.getStructureId() + " Saved successfully");
//                                    } else {
//                                        Utilities.showCustomNetworkAlert(this, "Failed to save, please try again", false);
//                                    }
//                                }
////
//
//                            }else {
//                                progress.setVisibility(View.VISIBLE);
//                                updateOTPresenter.submitOTData(otUpdateRequest);
//                            }
//                        }
//                    } else {
//                        Utilities.showCustomNetworkAlert(this, "Please capture the photo", false);
//                    }
//                } else {
//                    Utilities.showCustomNetworkAlert(this, "Please update the status", false);
//                }
//
//                break;
//        }
//    }
//
//    @Override
//    public Context getContext() {
//        return this;
//    }
//
//    @Override
//    public void showMessage(int stringId) {
//
//    }
//
//    @Override
//    public void showMessage(String message) {
//
//    }
//
//    @Override
//    public void showProgressIndicator(Boolean show) {
//
//    }
//
//    @Override
//    public void showErrorMessage(String message) {
//
//    }
//
//    @Override
//    public void getOTUpdateResponse(OTUpdateResponse otUpdateResponse) {
//        progress.setVisibility(View.GONE);
//        try {
//            if (otUpdateResponse != null) {
//                if (otUpdateResponse.getStatus() == 200) {
//                    Utilities.showCustomNetworkAlertSuccess(this, otUpdateResponse.getTag());
//                } else {
//                    Utilities.showCustomNetworkAlert(this, otUpdateResponse.getTag(), false);
//                }
//            } else {
//                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.server), false);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private Bitmap ProcessingBitmap(Bitmap bitmap) {
//        Bitmap bm1 = null;
//        Bitmap newBitmap = null;
//
//        try {
//            bm1 = bitmap;
//            Bitmap.Config config = bm1.getConfig();
//            if (config == null) {
//                config = Bitmap.Config.ARGB_8888;
//            }
//
//            newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight(), config);
//            Canvas newCanvas = new Canvas(newBitmap);
//
//
//            newCanvas.drawBitmap(bm1, 0, 0, null);
//
//            String currentDateTimeString = (String) DateFormat.format("yyyy/MM/dd hh:mm",
//                    System.currentTimeMillis());
//
//            if (mCurrentLocation != null && mCurrentLocation.getLatitude() > 0 && mCurrentLocation.getLongitude() > 0) {
//
//                Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//                paintText.setTextSize(10);
//                paintText.setStyle(Paint.Style.FILL);
//
//                Rect rectText = new Rect();
//                paintText.getTextBounds(String.valueOf(mCurrentLocation.getLatitude()), 0, String.valueOf(mCurrentLocation.getLatitude()).length(), rectText);
//
//                paintText.setColor(Color.WHITE);
//                newCanvas.drawText(currentDateTimeString,
//                        0, 120, paintText);
//                paintText.setColor(Color.WHITE);
//                newCanvas.drawText(String.valueOf(mCurrentLocation.getLatitude()),
//                        0, 140, paintText);
//                paintText.setColor(Color.WHITE);
//                newCanvas.drawText(String.valueOf(mCurrentLocation.getLongitude()),
//                        0, 150, paintText);
//
//            } else {
//                Toast.makeText(this, "caption empty!", Toast.LENGTH_LONG).show();
//            }
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return newBitmap;
//    }
//
//
//    private Bitmap ProcessingOfflineBitmap(Bitmap bitmap) {
//        Bitmap bm1 = null;
//        Bitmap newBitmap = null;
//
//        try {
//            bm1 = bitmap;
//            Bitmap.Config config = bm1.getConfig();
//            if (config == null) {
//                config = Bitmap.Config.ARGB_8888;
//            }
//
//            newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight(), config);
//            Canvas newCanvas = new Canvas(newBitmap);
//
//
//            newCanvas.drawBitmap(bm1, 0, 0, null);
//
//            String currentDateTimeString = (String) DateFormat.format("yyyy/MM/dd hh:mm",
//                    System.currentTimeMillis());
//
//            Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//            paintText.setTextSize(10);
//            paintText.setStyle(Paint.Style.FILL);
//
//            Rect rectText = new Rect();
//            paintText.getTextBounds("Offline", 0, "Offline".length(), rectText);
//
//            paintText.setColor(Color.WHITE);
//            newCanvas.drawText(currentDateTimeString,
//                    0, 120, paintText);
//            newCanvas.drawText("Offline",
//                    0, 160, paintText);
//            paintText.setColor(Color.WHITE);
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return newBitmap;
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        registerReceiver(mGpsSwitchStateReceiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));
//
//        mLocationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                super.onLocationResult(locationResult);
//
//                mCurrentLocation = locationResult.getLastLocation();
//            }
//        };
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(mGpsSwitchStateReceiver);
//    }
//
//
//    private BroadcastReceiver mGpsSwitchStateReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            try {
//                if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
//                    // Make an action or refresh an already managed state.
//                    callPermissions();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    };
//
//
//    @Override
//    public void onBackPressed() {
//        if (progress.getVisibility() != View.VISIBLE) {
//            super.onBackPressed();
//        }
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//}
