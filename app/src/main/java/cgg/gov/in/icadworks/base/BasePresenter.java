package cgg.gov.in.icadworks.base;


public interface BasePresenter<V> {

    void attachView(V view);

    void detachView();

}

