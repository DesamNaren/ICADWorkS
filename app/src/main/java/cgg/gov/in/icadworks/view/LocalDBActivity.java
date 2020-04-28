//package cgg.gov.in.icadworks.view;
//
//import android.content.SharedPreferences;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.ProgressBar;
//
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//import java.util.List;
//import cgg.gov.in.icadworks.R;
//
//import cgg.gov.in.icadworks.custom.CustomFontTextView;
//import cgg.gov.in.icadworks.localdb.DatabaseHelper;
//import cgg.gov.in.icadworks.model.request.OTUpdateRequest;
//import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
//import cgg.gov.in.icadworks.util.Utilities;
//
//public class LocalDBActivity extends AppCompatActivity {
//
//    private RecyclerView localRV;
//    private CustomFontTextView emptyTV;
//    private ProgressBar progress;
//
//    private DatabaseHelper db;
//    private int defSelection;
//    private EmployeeDetailss employeeDetailss;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_local_db);
//
//        localRV = findViewById(R.id.localRV);
//        emptyTV = findViewById(R.id.emptyTV);
//        progress = findViewById(R.id.progress);
//
//
//        db = new DatabaseHelper(this);
//
//        try {
//            getSupportActionBar().show();
//            getSupportActionBar().setTitle("OT Details");
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//            Gson gson = new Gson();
//            SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
//            String string = sharedPreferences.getString("LOGIN_DATA", "");
//            defSelection = sharedPreferences.getInt("DEFAULT_SELECTION", -1);
//
//            employeeDetailss = gson.fromJson(string, EmployeeDetailss.class);
//            if (defSelection >= 0 && employeeDetailss != null && employeeDetailss.getEmployeeDetail() != null) {
//                List<OTUpdateRequest> localOTs = db.getAllOTs(
//                        employeeDetailss.getEmployeeDetail().get(defSelection).getEmpId(),
//                        employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation(),
//                        employeeDetailss.getEmployeeDetail().get(defSelection).getPostType());
//
//                if(localOTs!=null && localOTs.size()>0){
//                    localRV.setVisibility(View.VISIBLE);
//                    emptyTV.setVisibility(View.GONE);
//                }else {
//                    localRV.setVisibility(View.GONE);
//                    emptyTV.setVisibility(View.VISIBLE);
//                }
//            } else {
//                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
