package cgg.gov.in.icadworks.interfaces;

import cgg.gov.in.icadworks.base.BaseView;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamResponse;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;

public interface OTView extends BaseView {
    void showMessage(int stringId);

    void showMessage(String message);

    void getOTResponse(OTResponse otResponse);

    void getReportResponse(ReportResponse reportResponse);

    void getCheckDamResponse(CheckDamResponse checkDamResponse);

}
