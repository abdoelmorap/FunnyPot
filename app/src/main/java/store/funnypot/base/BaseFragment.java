package store.funnypot.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import store.funnypot.base.view.model.BaseViewModel;
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
    }

    protected abstract View getViewRoot();

    protected abstract BaseViewModel mbaseViewModel();

}
