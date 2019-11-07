package hamilton.com.br.tempo.ui

import android.app.Application
import hamilton.com.br.tempo.extensions.DelegatesExt

/**
 * Created by hamilton on 01/11/17.
 */
class App :  Application(){

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}