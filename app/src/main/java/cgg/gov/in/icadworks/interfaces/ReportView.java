package cgg.gov.in.icadworks.interfaces;

import cgg.gov.in.icadworks.base.BaseView;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;

public interface ReportView extends BaseView {
    void getReportResponse(ReportResponse reportResponse);
}

