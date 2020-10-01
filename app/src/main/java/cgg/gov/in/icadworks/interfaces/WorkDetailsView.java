package cgg.gov.in.icadworks.interfaces;

import cgg.gov.in.icadworks.base.BaseView;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamResponse;
import cgg.gov.in.icadworks.model.response.checkdam.office.CDOfficeResponse;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.model.response.works.WorkDetailsResponse;

public interface WorkDetailsView extends BaseView {
    void showMessage(int stringId);

    void showMessage(String message);

    void getWorkDetailsResponse(WorkDetailsResponse workDetailsResponse);

}
