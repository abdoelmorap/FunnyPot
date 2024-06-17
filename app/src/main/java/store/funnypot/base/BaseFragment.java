package store.funnypot.base;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.regex.Pattern;

import store.funnypot.base.view.model.BaseViewModel;
import store.funnypot.data.sharedPref.SharedConfg;
import store.funnypot.util.helper.ProgressHelper;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getViewRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mbaseViewModel().loadingLiveData.observe(requireActivity(), (data)->{

            if (data){
                ProgressHelper.showDialog(requireActivity(),"Loading");
            }else {
                ProgressHelper.dismissDialog();
            }});


        mbaseViewModel().sendMsg.observe(requireActivity(), (data)->{

           new AlertDialog.Builder(requireActivity()).setMessage(data).show();

            });
    }
    protected String getToken(FragmentActivity fragmentActivity) {
        String token=new SharedConfg().getToken(fragmentActivity);
        return "Bearer "+token;
    }
    protected boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
    protected abstract View getViewRoot();

    protected abstract BaseViewModel mbaseViewModel();

}
