package cgg.gov.in.icadworks.base;

import android.content.Context;

public interface BaseView {
    Context getContext();

    void showMessage(int stringId);

    void showMessage(String message);

    void showProgressIndicator(Boolean show);

    void showErrorMessage(String message);

}
