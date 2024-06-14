package store.funnypot.view.modelView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import store.funnypot.base.view.model.BaseViewModel;
import store.funnypot.data.impl.RepoImpl;
import store.funnypot.data.interfaces.MethodsInterFace;
import store.funnypot.data.models.HomeData;
import store.funnypot.data.models.ProductsModel;
import store.funnypot.data.models.auth.LoginReq;
import store.funnypot.data.models.items.ItemsDetails;
import store.funnypot.ui.activities.ItemDetails;

public class MainViewModel extends BaseViewModel {




    public MutableLiveData<ProductsModel> DataOfProducts = new MutableLiveData<>();
    public MutableLiveData<HomeData> HomeDataLive = new MutableLiveData<>();
    public MutableLiveData<ItemsDetails> itemDetailsMutableLiveData = new MutableLiveData<>();



    @Inject
    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    public void startLoading(){
        boolean tt=true;
        changeLoadingStatus(tt);
    }
    public void stopLoading(){
        boolean tt=false;
        changeLoadingStatus(tt);
    }
    public  void  getDataOfProducts() {
        changeLoadingStatus(true);
        MethodsInterFace methodsInterFace = new RepoImpl();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable=    methodsInterFace.getItems() .subscribeOn(ioScheduler)
                .observeOn(mainThread)
                .subscribe((e)->{
                        changeLoadingStatus(false);
                        DataOfProducts.postValue(e);
                },e->{
            changeLoadingStatus(false);

                });
        compositeDisposable.add(disposable); //IDE is satisfied that the Disposable is being managed.

    }
    public  void  getHomeData() {
        changeLoadingStatus(true);
        MethodsInterFace methodsInterFace = new RepoImpl();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable=    methodsInterFace.getHomeData() .subscribeOn(ioScheduler)
                .observeOn(mainThread)
                .subscribe((e)->{
                    changeLoadingStatus(false);
                    HomeDataLive.postValue(e);
                },e->{
                    changeLoadingStatus(false);

                });
        compositeDisposable.add(disposable); //IDE is satisfied that the Disposable is being managed.

    }
    public  void  getItemDetails(String id) {
        changeLoadingStatus(true);
        MethodsInterFace methodsInterFace = new RepoImpl();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable=    methodsInterFace.getItemDetails(id) .subscribeOn(ioScheduler)
                .observeOn(mainThread)
                .subscribe((e)->{
                    changeLoadingStatus(false);
                    itemDetailsMutableLiveData.postValue(e);
                },e->{
                    changeLoadingStatus(false);

                });
        compositeDisposable.add(disposable); //IDE is satisfied that the Disposable is being managed.

    }



    public  void  login(String phone) {
        changeLoadingStatus(true);
        MethodsInterFace methodsInterFace = new RepoImpl();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable=    methodsInterFace.logIn(new LoginReq(phone)) .subscribeOn(ioScheduler)
                .observeOn(mainThread)
                .subscribe((e)->{
                    changeLoadingStatus(false);

                },e->{
                    changeLoadingStatus(false);

                });
        compositeDisposable.add(disposable); //IDE is satisfied that the Disposable is being managed.

    }

}
