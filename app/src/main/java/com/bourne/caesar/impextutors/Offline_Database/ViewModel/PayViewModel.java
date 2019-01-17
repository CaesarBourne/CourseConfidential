package com.bourne.caesar.impextutors.Offline_Database.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Offline_Database.PayTable;
import com.bourne.caesar.impextutors.Offline_Database.Repository.ImpexRepository;

import java.util.List;

public class PayViewModel extends AndroidViewModel {
    private LiveData<List<PayTable>> allPayment;
    private ImpexRepository impexRepository;

    public PayViewModel(@NonNull Application application) {
        super(application);
        impexRepository = new ImpexRepository(application);
        allPayment = impexRepository.getAllPayment();
    }

    public void InsertPayID(PayTable payTable){
        impexRepository.Insert(payTable);
    }

    public void UpdatePayID(PayTable payTable){
        impexRepository.Update(payTable);
    }

    public void DeletePayId(PayTable payTable){
        impexRepository.Delete(payTable);
    }

    public void DeleteAllPayment(PayTable payTable){
        impexRepository.DeleteAll();
    }
    public LiveData<List<PayTable>> getAllPayment(){
        return allPayment;
    }
}
