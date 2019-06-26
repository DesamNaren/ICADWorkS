package cgg.gov.in.icadworks.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.text.style.LeadingMarginSpan;
import android.util.Log;
import android.util.LruCache;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
//import com.shashank.sony.fancydialoglib.Animation;
//import com.shashank.sony.fancydialoglib.FancyAlertDialog;
//import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
//import com.shashank.sony.fancydialoglib.Icon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cgg.gov.in.icadworks.BuildConfig;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.view.DashboardActivity;
import cgg.gov.in.icadworks.view.LoginActivity;

public class Utilities {
    final static String dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/ICAD";
    private Context context;
    private static Uri imageUri;
    private static File mediaFile;
    private static AlertDialog alert_dialog;
    private static Uri fileUri;
    private static final int MEDIA_TYPE_IMAGE = 1;

    public Utilities(Context context) {
        this.context = context;
    }

    @SuppressLint("NewApi")
    public static boolean checkPermission(Context context, String permission) {
        // TODO Auto-generated method stub
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    public static SpannableString createIndentedText(String text, int marginFirstLine, int marginNextLines) {
        SpannableString result = new SpannableString(text);
        result.setSpan(new LeadingMarginSpan.Standard(marginFirstLine, marginNextLines), 0, text.length(), 0);
        return result;
    }

    public static View setDialogTitle(Context context, String title_str) {
        View view = View.inflate(context, R.layout.dialog_custom_title, null);
        LinearLayout dialog_title_ll = (LinearLayout) view.findViewById(R.id.dialog_title_ll);
        dialog_title_ll.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        CustomFontTextView title = (CustomFontTextView) view.findViewById(R.id.dialog_title);
        title.setText(title_str);
        return view;
    }

//    public static Boolean checkForceUpdate(Context context) {
//        // TODO Auto-generated method stub
//        // force update business login
//        String current_version_name;
//        int current_version_code = 0;
//        Boolean is_force_upgrade_status = false;
//        DatabaseUtilities dbUtilities = DatabaseUtilities.getInstance();
//        is_force_upgrade_status = false;
//
//        try {
//            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
//            current_version_name = pInfo.versionName;
//            current_version_code = pInfo.versionCode;
//        } catch (PackageManager.NameNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        List<AppDetail> appDetails = dbUtilities.getAppDetails();
//        Collections.sort(appDetails, new Comparator<AppDetail>() {
//            public int compare(AppDetail o1, AppDetail o2) {
//                if (o1.getId() == null || o2.getId() == null)
//                    return 0;
//                return Integer.valueOf(o1.getAndroidCurrentVersionCode()).compareTo(Integer.valueOf(o2.getAndroidCurrentVersionCode()));
//            }
//        });
//        if (appDetails != null){
//            for (int i=0; i< appDetails.size(); i++) {
//                if (appDetails.get(i).getAndroidCurrentVersionCode() > 0
//                        && appDetails.get(i).getAndroidCurrentVersionCode() > current_version_code && appDetails.get(i).getAndroidIsForceUpgrade()){
//                    if (!is_force_upgrade_status){
//                        return is_force_upgrade_status = true;
//                    }
//                }
//            }
//        }
////        /*HashMap<String, String> app_version_pref = pref.getAppVersion();
////        String app_version_from_pref = app_version_pref.get(CCSharedPreference.APP_VERSION_TAG);*/
////        if (appDetails != null && appDetails.getAndroidIsForceUpgrade() && appDetails.getAndroidCurrentVersionCode() > 0
////                && current_version_code < appDetails.getAndroidCurrentVersionCode()) {
////            is_force_upgrade_status = true;
//////            if (force_update_dialog == null || (force_update_dialog != null && !force_update_dialog.isShowing())){
////            // showForceUpdateDialog();
//////                return true;
//////            }
////            return true;
////
////        }
//
//        return false;
//    }

    public static String roundDecimal(double value, int places) {
       /* if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;*/

//        System.out.printf("Value: %.2f", value);
        String format = "%." + places + "f";
        String d = String.format(format, value);
        return d;
    }

    public static Boolean isJSONObject(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    public static Boolean isValidInput(Object str) {
        if (str != null && String.valueOf(str).length() > 0 &&
                !String.valueOf(str).equalsIgnoreCase("null")) {
            String new_string = String.valueOf(str).trim();
            if (new_string.isEmpty()) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isFiledEmpty(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isStringNotNull(Object str) {
        if (str == null || String.valueOf(str).length() == 0 || String.valueOf(str).equalsIgnoreCase("null")) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean isBooleanValue(Object str) {
        if (str == null && String.valueOf(str).length() == 0 || String.valueOf(str).equalsIgnoreCase("null")) {
            return false;
        } else {
            return (Boolean) str;
        }
    }

    public static Boolean isStringEmpty(Object str) {
        if (str != null && String.valueOf(str).length() > 0 && !String.valueOf(str).equalsIgnoreCase("null")) {
            return true;
        } else {
            return false;
        }
    }

    public static void editTextFocusHandler(final EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // If has focus then change the alpha to 1
//                    editText.setAlpha(0.5f);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        editText.setBackgroundTintList(ColorStateList.valueOf(getR.color.white));
                    }
                } else {
                    // When focus is lost, check if some text has been entered.
                    // If not then set the alpha to 0.5f (50% transparent)
                    if (TextUtils.isEmpty(editText.getText().toString())) {
//                        editText.setAlpha(0.3f);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            editText.setBackgroundTintList(ColorStateList.valueOf(R.color.color_grey));
//                            editText.setBackgroundTintList(ColorStateList.valueOf(R.color.white));
                        }
                    }
                }
                editText.invalidate();
            }
        });
    }

    public static void setTextColor(CustomFontTextView view, Context context, int color_id) {

        if (Build.VERSION.SDK_INT >= 23) {
            //do your check here
            view.setTextColor(ContextCompat.getColor(context, color_id));
        } else {
            view.setTextColor(context.getResources().getColor(color_id));
        }
    }

    public static String getString(Object str) {
        if (str != null && String.valueOf(str).length() > 0 && !String.valueOf(str).equalsIgnoreCase("null")) {
            return String.valueOf(str);
        } else {
            return "";
        }
    }

    public static Boolean validateClickForNonInternet(Boolean is_active_internet, Context context) {
        if (is_active_internet) {

        } else {
            Utilities.showToast(context.getResources().getString(R.string.no_internet), context);
        }
        return is_active_internet;
    }

    static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static String urlEncodeUTF8(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    urlEncodeUTF8(entry.getKey().toString()),
                    urlEncodeUTF8(entry.getValue().toString())
            ));
        }
        return sb.toString();
    }

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    public static void showToast(String msg, Context ctx) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
        }
    }


    public static void showToastLong(String msg, Context ctx) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
        }
    }

    public static void showAlertDialog(final Activity context, String title, String message,
                                       final Boolean isActivityClose, final Boolean isCloseVisible, final Boolean isOkVisible) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        if (title != null)
            dialogBuilder.setCustomTitle(Utilities.setDialogTitle(context, title));
        else
            dialogBuilder.setCustomTitle(Utilities.setDialogTitle(context, context.getResources().getString(R.string.app_name)));
        LayoutInflater inflater = context.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_simple, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        final ViewHolder viewHolder = new ViewHolder(dialogView);
        viewHolder.dialogMessageTv.setText(message);
        if (isOkVisible) {
            viewHolder.okBtn.setVisibility(View.VISIBLE);
        } else {
            viewHolder.okBtn.setVisibility(View.GONE);
        }
        if (isCloseVisible) {
            viewHolder.cancelBtn.setVisibility(View.VISIBLE);
        } else {
            viewHolder.cancelBtn.setVisibility(View.GONE);
        }

        viewHolder.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isActivityClose) {
                    context.finish();
                } else {
                    alert_dialog.cancel();
                }
            }
        });
        viewHolder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isActivityClose) {
                    context.finish();
                } else {
                    alert_dialog.cancel();
                }
            }
        });
        alert_dialog = dialogBuilder.create();
        if (!alert_dialog.isShowing()) {
            alert_dialog.show();
        }
    }

    public static class ViewHolder {
        @BindView(R.id.dialog_message_tv)
        public CustomFontTextView dialogMessageTv;
        @BindView(R.id.ok_btn)
        public CustomFontTextView okBtn;
        @BindView(R.id.cancel_btn)
        public CustomFontTextView cancelBtn;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



    public static void showToastCenter(String msg, Context ctx) {
        if (!TextUtils.isEmpty(msg)) {
            Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
    }

//    public static Map<String, Object> getDeviceDetailsMap(Context context) {
//        DeviceDetailsVO deviceDetailsVO = new DeviceDetailsVO();
//        DeviceDetails deviceDetails = new DeviceDetails();
//        Map<String, Object> stringObjectMap = null;
//        try {
//            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
//            String version = pInfo.versionName;
//            deviceDetails.setVersionCode(pInfo.versionCode);
//            deviceDetails.setVersionName(pInfo.versionName);
//            deviceDetails.setDeviceType(DEVICE_TYPE_ANDROID);
//            deviceDetails.setDeviceTypeCode(DEVICE_TYPE_ANDROID_CODE);
//            deviceDetails.setDeviceId(getDeviceId(context));
//            deviceDetails.setOsVersion(getDeviceOS());
//            deviceDetails.setDeviceCompanyName(Build.MANUFACTURER.toString());
//            deviceDetails.setDeviceModel(Build.MODEL);
//            deviceDetails.setGcmId("");
//            /*if (FirebaseInstanceId.getInstance() != null && FirebaseInstanceId.getInstance().getToken() != null) {
//                deviceDetails.setGcmId(FirebaseInstanceId.getInstance().getToken());
//                Log.d("Utilities ", "Refreshed Token : " + FirebaseInstanceId.getInstance().getToken());
//            }*/
//
//            String s = new Gson().toJson(deviceDetails);
//
//            try {
//                stringObjectMap = Utilities.jsonToMap(new JSONObject(s));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        } catch (Exception e) {
//            // no Twitter app, revert to browser
//        }
//        deviceDetailsVO.setDeviceDetails(deviceDetails);
//        //    return deviceDetailsVO;
//        return stringObjectMap;
//    }

//    public static DeviceDetails getDeviceDetails(Context context) {
//        DeviceDetails deviceDetails = new DeviceDetails();
//        try {
//            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
//            String version = pInfo.versionName;
//            deviceDetails.setVersionCode(pInfo.versionCode);
//            deviceDetails.setVersionName(pInfo.versionName);
//            deviceDetails.setDeviceType(DEVICE_TYPE_ANDROID);
//            deviceDetails.setDeviceTypeCode(DEVICE_TYPE_ANDROID_CODE);
//            deviceDetails.setDeviceId(getDeviceId(context));
//            deviceDetails.setOsVersion(getDeviceOS());
//            deviceDetails.setDeviceCompanyName(Build.MANUFACTURER.toString());
//            deviceDetails.setDeviceModel(Build.MODEL);
//            deviceDetails.setGcmId("");
//
//            /*if (FirebaseInstanceId.getInstance() != null && FirebaseInstanceId.getInstance().getToken() != null) {
//                deviceDetails.setGcmId(FirebaseInstanceId.getInstance().getToken());
//                Log.d("Utilities ", "Refreshed Token : " + FirebaseInstanceId.getInstance().getToken());
//            }*/
//
//        } catch (Exception e) {
//            // no Twitter app, revert to browser
//        }
//        return deviceDetails;
//    }
//
//    public static String getDeviceOS() {
//        // TODO Auto-generated method stub
//        String os_details = "";
//        StringBuilder builder = new StringBuilder();
//        //		builder.append("android : ").append(Build.VERSION.RELEASE);
//
//        Field[] fields = Build.VERSION_CODES.class.getFields();
//        for (Field field : fields) {
//            String fieldName = field.getName();
//            int fieldValue = -1;
//
//            try {
//                fieldValue = field.getInt(new Object());
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            }
//
//            if (fieldValue == Build.VERSION.SDK_INT) {
//                builder = new StringBuilder();
//                builder.append("").append(fieldName).append(" ").append(Build.VERSION.RELEASE);
//                //		        builder.append("sdk=").append(fieldValue);
//            }
//        }
//
//        //		Log.d(LOG_TAG, "OS: " + builder.toString());
//
//        os_details = builder.toString();
//        //		Log.d(LOG_TAG, "OS: " + builder.toString());
//        return os_details;
//    }

    public static String getAppVersion(Context activity) {
        // TODO Auto-generated method stub
        PackageInfo pInfo;
        String app_version = "";
        try {
            pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            app_version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return app_version;
    }

    public static String getDeviceId(Context activity) {
        return Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getIpAddress() {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            ip = "";
            ex.printStackTrace();
        }
        return ip;
    }

    public static void setViewbackgroundStrokeCOlor(View view, int color, int size) {
        GradientDrawable myGrad = (GradientDrawable) view.getBackground();
        if (size == 0) {
            myGrad.setStroke(1, color);
        } else {
            myGrad.setStroke(size, color);
        }

    }

    public static String loadJSONFromAsset(Context context, String file_name) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(file_name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    public static String md5Hash(String str) {
        try {

            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
//                hexString.append(Integer.toHexString(0xFF & messageDigest[i]), 32);
                hexString.append(Integer.toString((messageDigest[i] & 0xff) + 0x100, 16).substring(1));

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void loadSpinnerData(Context context, ArrayList<String> arrayList, Spinner spinner) {
        try {
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arrayList);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerArrayAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadSpinnerSetSelectedItem(Context context, ArrayList<String> arrayList, Spinner spinner, String selection) {
        try {
            ArrayList<String> lables = arrayList;
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, lables);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerArrayAdapter);
            spinner.setSelection(spinnerArrayAdapter.getPosition(selection));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getVersionInfo() {
        String versionName = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void showCustomNetworkAlertLogout(final Activity activity, String str) {
        new TTFancyGifDialog.Builder(activity)
                .setTitle(activity.getResources().getString(R.string.app_name))
                .setMessage(str)
                .setPositiveBtnText("No")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Yes")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.log)      //pass your gif, png or jpg
                .isCancellable(true)
                .OnPositiveClicked(new TTFancyGifDialogListener() {
                    @Override
                    public void OnClick() {

                    }
                })
                .OnNegativeClicked(new TTFancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        activity.startActivity(new Intent(activity, LoginActivity.class));
                        activity.finish();
                    }
                })
                .build();
    }

    public static void showCustomPlayAlertLogout(final Activity activity, String str) {
        new TTFancyGifDialog.Builder(activity)
                .setTitle(activity.getResources().getString(R.string.app_name))
                .setMessage(str)
                .setPositiveBtnText("Get Started")
                .setPositiveBtnBackground("#22b573")
                .setGifResource(R.drawable.hello)
                .isCancellable(false)
                .OnPositiveClicked(new TTFancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        navigatePlayStore(activity);
                        activity.finish();
                    }
                })
                .build();
    }

    private static void navigatePlayStore(Activity activity) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=cgg.gov.in.icadworks"));
        activity.startActivity(browserIntent);
    }

    public static void showCustomNetworkAlert(final Activity activity, String str, final boolean flag) {
        new TTFancyGifDialog.Builder(activity)
                .setTitle(activity.getResources().getString(R.string.app_name))
                .setMessage(str)
                .setPositiveBtnText("Ok")
                .setPositiveBtnBackground("#c1272d")
                .setGifResource(R.drawable.hello)      //pass your gif, png or jpg
                .isCancellable(false)
                .OnPositiveClicked(new TTFancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        if (flag) {
                            activity.finish();
                        }
                    }
                })
                .build();
    }

    public static void showSettingsAlert(final Activity activity, String str, final boolean flag) {
        new TTFancyGifDialog.Builder(activity)
                .setTitle(activity.getResources().getString(R.string.app_name))
                .setMessage(str)
                .setPositiveBtnText("OK")
                .setPositiveBtnBackground("#22b573")
                .setGifResource(R.drawable.hello)      //pass your gif, png or jpg
                .isCancellable(true)
                .OnPositiveClicked(new TTFancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        if (flag) {
                            openSettings(activity);
                        }
                    }
                })
                .build();
    }

    private static void openSettings(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
    public static void showCustomNetworkAlertSuccess(final Activity activity, String str) {
        new TTFancyGifDialog.Builder(activity)
                .setTitle(activity.getResources().getString(R.string.app_name))
                .setMessage(str)
                .setPositiveBtnText("Ok")
                .setPositiveBtnBackground("#22b573")
                .setGifResource(R.drawable.hello)      //pass your gif, png or jpg
                .isCancellable(false)
                .OnPositiveClicked(new TTFancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        activity.startActivity(new Intent(activity, DashboardActivity.class));
                        activity.finish();
                    }
                })
                .build();
    }

    public static void takeSCImageAbstarct(Activity activity, RecyclerView recyclerView, String typeData) {
        try {
            fileUri = getOutputMediaFileUri(activity, MEDIA_TYPE_IMAGE, typeData);
            Bitmap bitmap1 = loadRV(recyclerView, recyclerView.getWidth(), 100);
            if(bitmap1!=null){
                store(bitmap1, activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void takeSCImage(Activity activity, View view, String typeData) {
        try {
            fileUri = getOutputMediaFileUri(activity, MEDIA_TYPE_IMAGE, typeData);
            Bitmap bitmap = getScreenShot(view);
            if(bitmap!=null){
                store(bitmap, activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Bitmap getRecyclerViewScreenshot(RecyclerView view) {
        Bitmap bigBitmap = null;
        try {
            int size = view.getAdapter().getItemCount();
            RecyclerView.ViewHolder holder = view.getAdapter().createViewHolder(view, 0);
            view.getAdapter().onBindViewHolder(holder, 0);
            holder.itemView.measure(MeasureSpec.makeMeasureSpec(view.getWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
            bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), holder.itemView.getMeasuredHeight() * size,
                    Bitmap.Config.ARGB_8888);
            Canvas bigCanvas = new Canvas(bigBitmap);
            bigCanvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            int iHeight = 0;
            holder.itemView.setDrawingCacheEnabled(true);
            holder.itemView.buildDrawingCache();
            bigCanvas.drawBitmap(holder.itemView.getDrawingCache(), 0f, iHeight, paint);
            holder.itemView.setDrawingCacheEnabled(true);
            holder.itemView.destroyDrawingCache();
            iHeight += holder.itemView.getMeasuredHeight();
            for (int i = 1; i < size; i++) {
                view.getAdapter().onBindViewHolder(holder, i);
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();
                bigCanvas.drawBitmap(holder.itemView.getDrawingCache(), 0f, iHeight, paint);
                iHeight += holder.itemView.getMeasuredHeight();
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.destroyDrawingCache();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bigBitmap;
    }

    public static Bitmap  loadRV(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width , height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    public static Bitmap getScreenshotFromRecyclerView(RecyclerView view) {
        RecyclerView.Adapter adapter = view.getAdapter();
        Bitmap bigBitmap = null;
        if (adapter != null) {
            int size = adapter.getItemCount();
            int height = 0;
            Paint paint = new Paint();
            int iHeight = 0;
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024*4);

            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = maxMemory / 8;
            LruCache<String, Bitmap> bitmaCache = new LruCache<>(cacheSize);
            for (int i = 0; i < size; i++) {
                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));
                adapter.onBindViewHolder(holder, i);
                holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();
                Bitmap drawingCache = holder.itemView.getDrawingCache();
                if (drawingCache != null) {

                    bitmaCache.put(String.valueOf(i), drawingCache);
                }
//                holder.itemView.setDrawingCacheEnabled(false);
//                holder.itemView.destroyDrawingCache();
                height += holder.itemView.getMeasuredHeight();
            }

            bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888);
            Canvas bigCanvas = new Canvas(bigBitmap);
            bigCanvas.drawColor(Color.WHITE);

            for (int i = 0; i < size; i++) {
                Bitmap bitmap = bitmaCache.get(String.valueOf(i));
                bigCanvas.drawBitmap(bitmap, 0f, iHeight, paint);
                iHeight += bitmap.getHeight();
                bitmap.recycle();
            }

        }
        return bigBitmap;
    }

    private static void shareImage(Activity activity, Uri fileUri){
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");

            intent.putExtra(Intent.EXTRA_SUBJECT, activity.getResources().getString(R.string.app_name));
            intent.putExtra(Intent.EXTRA_STREAM, fileUri);
            try {
                activity. startActivity(Intent.createChooser(intent, "Project Data"));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(activity, "No App Available", Toast.LENGTH_SHORT).show();
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Bitmap getScreenShot(View ll) {
        ll.setDrawingCacheEnabled(true);
        ll.buildDrawingCache(true);
        return Bitmap.createBitmap(ll.getDrawingCache());
    }

    private static void store(Bitmap bm, Activity activity){
        try {
            FileOutputStream fOut = new FileOutputStream(mediaFile);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();

            shareImage(activity, fileUri);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Uri getOutputMediaFileUri(Activity activity, int type, String typeData) {
        File imageFile = getOutputMediaFile(type, typeData);
        imageUri = FileProvider.getUriForFile(
                activity,
                "cgg.gov.in.icadworks.provider", //(use your app signature + ".provider" )
                imageFile);
        return imageUri;
    }

    private static File getOutputMediaFile(int type, String typeData) {
        File mediaStorageDir = new File(dirPath);
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }

        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + typeData + getTimeStamp() + ".jpg");
        } else {
            return null;
        }
        return mediaFile;
    }

    private static String getTimeStamp(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        return simpleDateFormat.format(new Date());
    }

    public static void loadWebView(final Activity activity, final ProgressBar progressBar, WebView webView, String url) {

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        progressBar.setVisibility(View.VISIBLE);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressBar.getVisibility() == View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                if (progressBar.getVisibility() == View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                }
                try {
                    Utilities.showAlertDialog(activity, getString(R.string.app_name), getString(R.string.something),true, false, true);
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        webView.loadUrl(url);
    }

}
