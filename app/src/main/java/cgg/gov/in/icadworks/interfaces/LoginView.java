package cgg.gov.in.icadworks.interfaces;

import cgg.gov.in.icadworks.base.BaseView;
import cgg.gov.in.icadworks.model.response.itemStatus.ItemStatusData;
import cgg.gov.in.icadworks.model.response.itemStatus.ItemStatusResponse;
import cgg.gov.in.icadworks.model.response.items.WorkItemsResponse;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;

public interface LoginView extends BaseView {
    void showMessage(int stringId);

    void showMessage(String message);

    void getLoginResponseSuccess(EmployeeDetailss employeeDetailss);
    void getItemStatusResponse(ItemStatusResponse itemStatusResponse);
    void getWorkItemsResponse(WorkItemsResponse workItemsResponse);

}
