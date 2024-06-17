package store.funnypot.base.view.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseViewModel extends AndroidViewModel {
    public  Scheduler ioScheduler = Schedulers.io();
    public  Scheduler mainThread  = AndroidSchedulers.mainThread();
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
    public  MutableLiveData<String> sendMsg = new MutableLiveData<>();
     public  MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
     protected void sendMsgToUI(String msg){
             sendMsg.postValue(msg);

     }
     protected void  changeLoadingStatus(Boolean status){
             loadingLiveData.postValue(status);

     }
}
