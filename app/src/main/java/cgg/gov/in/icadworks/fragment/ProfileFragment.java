package cgg.gov.in.icadworks.fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.base.BaseFragment;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.Utilities;
import cgg.gov.in.icadworks.view.DashboardActivity;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    @BindView(R.id.empNameTV)
    CustomFontTextView empNameTV;
    @BindView(R.id.desTV)
    CustomFontTextView desTV;
    @BindView(R.id.userNameTV)
    TextView userNameTV;
    @BindView(R.id.postTV)
    TextView postTV;
    @BindView(R.id.contactTV)
    TextView contactTV;
    @BindView(R.id.emailTV)
    TextView emailTV;
    @BindView(R.id.unitTV)
    TextView unitTV;
    @BindView(R.id.circleTV)
    TextView circleTV;
    @BindView(R.id.divTV)
    TextView divTV;
    @BindView(R.id.subDivTV)
    TextView subDivTV;
    @BindView(R.id.secTV)
    TextView secTV;
    Unbinder unbinder;
    private int defSelection;
    private SharedPreferences sharedPreferences;
    private EmployeeDetailss employeeDetailss = null;
    private int pos=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, view);
        setProfileData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            ((DashboardActivity)getActivity()).getSupportActionBar().setTitle("Profile");
            ((DashboardActivity)getActivity()).getSupportActionBar().setSubtitle("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setProfileData() {
        try {
            Gson gson = new Gson();
            if (getActivity() != null) {
                sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
                String string = sharedPreferences.getString("LOGIN_DATA", "");
                defSelection = sharedPreferences.getInt("DEFAULT_SELECTION", -1);
                employeeDetailss = gson.fromJson(string, EmployeeDetailss.class);
                if (defSelection>=0 && employeeDetailss != null) {
                    empNameTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getEmpName());
                    desTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation());
                    userNameTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getUserName());
                    postTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getPostType());
                    contactTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getContactNo());
                    emailTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getEmailId());
                    unitTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getUnit());
                    circleTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getCircle());
                    divTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getDivision());
                    subDivTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getSubdivision());
                    secTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getSection());
                } else {
                    Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
                }
            }
        } catch (Exception e) {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onPrepareOptionsMenu(menu);
        inflater.inflate(R.menu.search_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (employeeDetailss != null && employeeDetailss.getEmployeeDetail() != null && employeeDetailss.getEmployeeDetail().size() > 1) {
            menu.findItem(R.id.action_view).setVisible(true);
        } else {
            menu.findItem(R.id.action_view).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view:
                if (employeeDetailss != null && employeeDetailss.getEmployeeDetail() != null
                        && employeeDetailss.getEmployeeDetail().size() > 0)
                    displayMultiSelectDialog(employeeDetailss);
                else
                    Utilities.showCustomNetworkAlert(getActivity(), getActivity().getResources().getString(R.string.something), false);
                return true;
            case R.id.action_logout:
                Utilities.showCustomNetworkAlertLogout(getActivity(), "Do you want logout from app?");
                return true;
            default:
                break;
        }

        return false;
    }

    private void displayMultiSelectDialog(final EmployeeDetailss employeeDetailss) {
        final ArrayList<String> desArrayList = new ArrayList<>();

        for (int z = 0; z < employeeDetailss.getEmployeeDetail().size(); z++) {
            desArrayList.add(employeeDetailss.getEmployeeDetail().get(z).getDesignation());
        }

        if (desArrayList.size() > 0) {

            sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
            defSelection = sharedPreferences.getInt("DEFAULT_SELECTION", 0);

            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Switch Role");

            final String[] str = desArrayList.toArray(new String[desArrayList.size()]);
            pos = defSelection;

            builder.setSingleChoiceItems(
                    str,
                    defSelection,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            pos = i;
                            String selectedItem = Arrays.asList(str).get(pos);
                            Snackbar.make(
                                    getView(),
                                    "Selected Role : " + selectedItem,
                                    Snackbar.LENGTH_LONG
                            ).show();
                        }
                    });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (defSelection != pos && defSelection >= 0) {

                        defSelection = pos;

//                        String selectedItem = Arrays.asList(str).get(pos);
//                        for (int z = 0; z < employeeDetailss.getEmployeeDetail().size(); z++) {
//                            if (selectedItem.equalsIgnoreCase(employeeDetailss.getEmployeeDetail().get(z).getDesignation())) {
//                                defSelection = z;
//                                break;
//                            }
//                        }

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("DEFAULT_SELECTION", defSelection);
                        editor.commit();

                        setProfileData();
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
