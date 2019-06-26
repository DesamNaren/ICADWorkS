package cgg.gov.in.icadworks.interfaces;

import cgg.gov.in.icadworks.base.BaseView;
import cgg.gov.in.icadworks.model.response.ot.OTUpdateResponse;

public interface UpdateOTView extends BaseView {
    void showMessage(int stringId);

    void showMessage(String message);

    void getOTUpdateResponse(OTUpdateResponse otUpdateResponse);

}
