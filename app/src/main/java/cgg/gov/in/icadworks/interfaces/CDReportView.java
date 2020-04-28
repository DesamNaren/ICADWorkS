package cgg.gov.in.icadworks.interfaces;

import cgg.gov.in.icadworks.base.BaseView;
import cgg.gov.in.icadworks.model.response.checkdam.office.CDOfficeResponse;

public interface CDReportView extends BaseView {
    void getCDOfficeReportResponse(CDOfficeResponse cdOfficeResponse);
}

