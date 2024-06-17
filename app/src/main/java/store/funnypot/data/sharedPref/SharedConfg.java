package store.funnypot.data.sharedPref;

import android.content.Context;

import java.util.ArrayList;

import store.funnypot.data.models.auth.User;

public class SharedConfg {
    public Boolean isLogin(Context context){
        return      new SharedVariables(context).getBooleanSharedVariable(SharedKeys.IS_LOGIN);

    }
    public User getUser(Context context){
        return  new  SharedVariables(context).getUserFromSharedVariable(SharedKeys.USER);
    }
    public String getToken(Context context){
        return  new  SharedVariables(context).getStrSharedVariable(SharedKeys.TOKEN);
    }
    public  void saveToken(Context context,String token){
        new SharedVariables(context).setStringSharedVariable(SharedKeys.TOKEN,token);
    }
    public  void saveUser(Context context,User user){
        new SharedVariables(context).setUserInSharedVariable(SharedKeys.USER,user);
    }
    public  void saveLogIn(Context context,boolean login){
        new SharedVariables(context).setBooleanSharedVariable(SharedKeys.IS_LOGIN,login);
    }
    public  void saveCounterCart(Context context){
        new SharedVariables(context).setIntSharedVariable(SharedKeys.CartCount,new SharedVariables(context).getintSharedVariable(SharedKeys.CartCount)+1);
    }
    public  void updateCounterCart(Context context,int count){
        new SharedVariables(context).setIntSharedVariable(SharedKeys.CartCount,count);
    }
    public  int getCounterCart(Context context){
        return  new SharedVariables(context).getintSharedVariable(SharedKeys.CartCount);
    }
    public  void saveCart(Context context,int ItemsId){
        ArrayList<Integer> arrayList = new SharedVariables(context).getIntArrSharedVariable(SharedKeys.CartIds);
        if (arrayList ==null){
            arrayList =new ArrayList<>();
        }
        arrayList.add(ItemsId);
        new SharedVariables(context).setIntArrSharedVariable(SharedKeys.CartIds,arrayList);
    }
    public ArrayList<Integer> getCart(Context context){
        return  new SharedVariables(context).getIntArrSharedVariable(SharedKeys.CartIds);
    }
/*
    fun isSeenOnBoarding(context: Context): Boolean =
    SharedVariables(context).getBooleanSharedVariable(SharedValueFlags.IS_SEEN_ON_BOARDING)

    fun getLoggedUser(context: Context): ActivationResponse? =
    SharedVariables(context).getObjectFromSharedVariable<ActivationResponse>(SharedValueFlags.USER)

    fun getLang(context: Context):String?= SharedVariables(context).getStringSharedVariable(
            SharedValueFlags.LANGUAGE,"en")

    fun getVerified(context: Context):String? = SharedVariables(context).getStringSharedVariable(
            SharedValueFlags.ISVerified,"")
    fun getRegisteredUser(context: Context): RegisterResponse? =
    SharedVariables(context).getObjectFromSharedVariable<RegisterResponse>(SharedValueFlags.REGISTERED_USER)
    fun getToken(context: Context):String? = SharedVariables(context).getStringSharedVariable(
            SharedValueFlags.TOKEN,"")
    fun getUser(context: Context): UserMainDAta? =
    SharedVariables(context).getObjectFromSharedVariable<UserMainDAta>(SharedValueFlags.USER_Data)
    fun getFirebaseToken(context: Context):String? = SharedVariables(context).getStringSharedVariable(
            SharedValueFlags.FIREBASE_TOKEN,"")
    fun clear(context: Context){
        SharedVariables(context).clearAllData()
    }*/
}
