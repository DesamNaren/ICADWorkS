package cgg.gov.in.icadworks.interfaces;

import cgg.gov.in.icadworks.base.BaseView;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.model.response.version.VersionResponse;

public interface SplashView extends BaseView {
    void showMessage(int stringId);

    void showMessage(String message);

    void getAppVersionResponse(VersionResponse versionResponse);

}
