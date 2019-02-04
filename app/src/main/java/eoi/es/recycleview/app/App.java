package eoi.es.recycleview.app;

import android.app.Application;

import eoi.es.recycleview.data.api.RestClient;
import io.realm.Realm;

public class App extends Application {

    static App instance;
    RestClient restClient;
    Realm realm;

    public Realm getRealm() {
        return realm;
    }

    public App() {
        instance = this;
    }

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }

        return instance;
    }

    public RestClient getRestClient() {
        if (restClient == null) {
            restClient = new RestClient();
        }

        return restClient;
    }

    @Override
    public void onCreate() {

        super.onCreate();

        //Inicializa Realm en nuestra app
        Realm.init(this);

        realm = Realm.getDefaultInstance();
    }
}
