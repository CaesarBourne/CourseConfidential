package com.bourne.caesar.impextutors.Offline_Database.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Update;
import android.os.AsyncTask;

import com.bourne.caesar.impextutors.Offline_Database.PayDao;
import com.bourne.caesar.impextutors.Offline_Database.PayDatabase;
import com.bourne.caesar.impextutors.Offline_Database.PayTable;

import java.util.List;

public class ImpexRepository {
    private PayDao payDao;
    private LiveData<List<PayTable>> allPayment;

    public ImpexRepository(Application application){
        PayDatabase payDatabaseRepoInstance  = PayDatabase.getPayDatabaseInstance(application);
        payDao = payDatabaseRepoInstance.payDao();
        //this below doe snot nee asynch task as it returns a live data that runs on background thread
        allPayment = payDao.getAllPayment();
    }

    public void Insert(PayTable payTable){
        new InsertPayTableAsynchTask(payDao).execute(payTable);
    }

    public void Update(PayTable payTable){
        new UpdatePayTableAsynchTask(payDao).execute(payTable);
    }

    public void Delete(PayTable payTable){
        new DeletePayTableAsynchTask(payDao).execute(payTable);
    }

    public void DeleteAll(){
        new DeleteAllPayTableAsynchTask(payDao).execute();
    }


    public LiveData<List<PayTable>> getAllPayment(){
        return allPayment;
    }

    private static class InsertPayTableAsynchTask extends AsyncTask<PayTable, Void, Void>{
        PayDao payDao;

        public InsertPayTableAsynchTask(PayDao payDao) {
            this.payDao = payDao;
        }

        @Override
        protected Void doInBackground(PayTable... payTables) {
            payDao.insert(payTables[0]);
            return null;
        }
    }
    private static class UpdatePayTableAsynchTask extends AsyncTask<PayTable, Void, Void>{
        PayDao payDao;

        public UpdatePayTableAsynchTask(PayDao payDao) {
            this.payDao = payDao;
        }

        @Override
        protected Void doInBackground(PayTable... payTables) {
            payDao.update(payTables[0]);
            return null;
        }
    }
    private static class DeletePayTableAsynchTask extends AsyncTask<PayTable, Void, Void>{
        PayDao payDao;

        public DeletePayTableAsynchTask(PayDao payDao) {
            this.payDao = payDao;
        }

        @Override
        protected Void doInBackground(PayTable... payTables) {
            payDao.delete(payTables[0]);
            return null;
        }
    }
    private static class DeleteAllPayTableAsynchTask extends AsyncTask<Void, Void, Void>{
        PayDao payDao;

        public DeleteAllPayTableAsynchTask(PayDao payDao) {
            this.payDao = payDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            payDao.deleteAllNotes();
            return null;
        }
    }
}
