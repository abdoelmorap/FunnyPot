package store.funnypot.view.modelView;

import android.app.Activity;
import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
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
import store.funnypot.data.models.auth.User;
import store.funnypot.data.models.auth.UserResponses;
import store.funnypot.data.models.cart.Cart;
import store.funnypot.data.models.cart.CartAdd;
import store.funnypot.data.models.items.ItemsDetails;
import store.funnypot.data.sharedPref.SharedConfg;
import store.funnypot.ui.activities.ItemDetails;

public class MainViewModel extends BaseViewModel {




    public MutableLiveData<ProductsModel> DataOfProducts = new MutableLiveData<>();
    public MutableLiveData<HomeData> HomeDataLive = new MutableLiveData<>();
    public MutableLiveData<ItemsDetails> itemDetailsMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<UserResponses> userResponsesMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<User> getUserMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Cart> mCartDetailsMutableLiveData = new MutableLiveData<>();


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

    public  void  updateUser(String token, User user) {
        changeLoadingStatus(true);
        MethodsInterFace methodsInterFace = new RepoImpl();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable=    methodsInterFace.updateUserName( token, user).subscribeOn(ioScheduler)
                .observeOn(mainThread)
                .subscribe((e)->{
                    changeLoadingStatus(false);
                    getUserMutableLiveData.postValue(e.getUser());
                 },e->{

                    changeLoadingStatus(false);
                    sendMsgToUI("Please Check that's E-mail");
                });
        compositeDisposable.add(disposable); //IDE is satisfied that the Disposable is being managed.

    }
    public  void  getprofile(String token) {
        MethodsInterFace methodsInterFace = new RepoImpl();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable=    methodsInterFace.getProfile( token).subscribeOn(ioScheduler)
                .observeOn(mainThread)
                .subscribe((e)->{
                    getUserMutableLiveData.postValue(e.getUser());
                },e->{

                    sendMsgToUI("No Internet Or something Wrong Happened");
                });
        compositeDisposable.add(disposable); //IDE is satisfied that the Disposable is being managed.

    }


    public  void  getCartItems(String token) {
        MethodsInterFace methodsInterFace = new RepoImpl();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable=    methodsInterFace.getCartItems( token).subscribeOn(ioScheduler)
                .observeOn(mainThread)
                .subscribe((e)->{
                    mCartDetailsMutableLiveData.postValue(e);
                },e->{

                    sendMsgToUI("No Internet Or something Wrong Happened"+e);
                });
        compositeDisposable.add(disposable); //IDE is satisfied that the Disposable is being managed.

    }


    public  void  login(String phone) {
        changeLoadingStatus(true);
        MethodsInterFace methodsInterFace = new RepoImpl();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable=    methodsInterFace.logIn(phone).subscribeOn(ioScheduler)
                .observeOn(mainThread)
                .subscribe((e)->{
                    changeLoadingStatus(false);
                    userResponsesMutableLiveData.postValue(e);
                },e->{
                    changeLoadingStatus(false);
                    sendMsgToUI("No Internet Or something Wrong Happened");

                });
        compositeDisposable.add(disposable); //IDE is satisfied that the Disposable is being managed.

    }
    public  void  addToCart(String token, int product_id, int count, Activity da) {
        changeLoadingStatus(true);
        MethodsInterFace methodsInterFace = new RepoImpl();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable=    methodsInterFace.addToCart( token, new CartAdd(product_id,count)).subscribeOn(ioScheduler)
                .observeOn(mainThread)
                .subscribe((e)->{
                    changeLoadingStatus(false);
                    mCartDetailsMutableLiveData.postValue(e);

                    new SharedConfg().saveCounterCart(da);
                },e->{

                    changeLoadingStatus(false);
                    sendMsgToUI("Please Check Your Internet");
                });
        compositeDisposable.add(disposable); //IDE is satisfied that the Disposable is being managed.

    }
}
