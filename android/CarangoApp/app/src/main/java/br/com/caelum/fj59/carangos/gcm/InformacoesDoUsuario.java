package br.com.caelum.fj59.carangos.gcm;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import br.com.caelum.fj59.carangos.infra.MyLog;

/**
 * Created by Hamilton on 22/09/16.
 */

public class InformacoesDoUsuario {

    public static String getEmail(Context context){

        AccountManager accountManager = AccountManager.get(context);
        Account account = getAccount(accountManager);
        if(account == null){
            return null;
        }
        MyLog.i("InformacoesDoUsuario.getEmail = " + account.name);
        return account.name;

    }
//    @SuppressWarnings("ResourceType")
    private static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account = null;
        if(accounts.length > 0){
            account = accounts[0];
        }
        return account;
    }
}
