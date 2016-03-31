package com.example.pro.restaurante_demo.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.pro.restaurante_demo.Fragments.Categorias.FragmentoCategorias;
import com.example.pro.restaurante_demo.Fragments.MiCuenta.FragmentoCuenta;
import com.example.pro.restaurante_demo.Fragments.inicio.FragmentoInicio;
import com.example.pro.restaurante_demo.Models.Comida;
import com.example.pro.restaurante_demo.Models.ComidasService;
import com.example.pro.restaurante_demo.Models.Menu;
import com.example.pro.restaurante_demo.Models.realm.ComidaCore;
import com.example.pro.restaurante_demo.Models.realm.MenuCore;
import com.example.pro.restaurante_demo.R;
import com.example.pro.restaurante_demo.Servicios.menuServiceAPI;
import com.example.pro.restaurante_demo.Utils.Constants;


import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    private Fragment fragmentoGenerico;
    private ComidasService service_result_comidas;
    private ProgressDialog loading;

    //-->RealmObjects
    private Realm realm;
    private RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregarToolbar();
        iniciarVariables();
        setRealm();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //-->validate core data from mobile
        RealmResults<MenuCore> result = realm.where(MenuCore.class).findAll();
        if(result.size() < 0){
            getComidasService();
        }

    }

    private void getComidasService(){

        loading = ProgressDialog.show(this, "Obteniendo Datos", "Porfavor espere...", false, false);

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Constants.ROOT_URL_COMIDA_SERVICE)
                .build();

        menuServiceAPI api = adapter.create(menuServiceAPI.class);

        api.getComidasService(new Callback<ComidasService>() {
            @Override
            public void success(ComidasService comidasServices, Response response) {
                Log.i("KEY","Succes Service Food Object: " + response);
                service_result_comidas = comidasServices;
                saveData();
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.e("Error Service retrofit: " + error);
                loading.dismiss();
            }
        });
    }

    private void saveData() {

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.beginTransaction();
        MenuCore menucore = realm.createObject(MenuCore.class);
        RealmList<ComidaCore> listComidaPostres = new RealmList<>();
        RealmList<ComidaCore> listComidabebidas = new RealmList<>();
        RealmList<ComidaCore> listComidaPlatillos = new RealmList<>();
        RealmList<ComidaCore> listComidaComidaspOPULARES = new RealmList<>();

        List<Comida> comidaPostres = service_result_comidas.getMENU().getPOSTRES();
        List<Comida> comidaBebidas = service_result_comidas.getMENU().getBEBIDAS();
        List<Comida> comidaPlatillos = service_result_comidas.getMENU().getPLATILLOS();
        List<Comida> comidaComidasPopulares = service_result_comidas.getMENU().getCOMIDAS_POPULARES();

        for(int x=0; x < comidaPostres.size() ;x++){
            ComidaCore comida = realm.createObject(ComidaCore.class);
            comida.setNombre(comidaPostres.get(x).getNombre());
            comida.setImg(comidaPostres.get(x).getImg());
            comida.setPrecio(comidaPostres.get(x).getPrecio());
            listComidaPostres.add(comida);
        }

        menucore.setPOSTRES(listComidaPostres);

        for(int x=0; x < comidaBebidas.size() ;x++){
            ComidaCore comida = realm.createObject(ComidaCore.class);
            comida.setNombre(comidaBebidas.get(x).getNombre());
            comida.setImg(comidaBebidas.get(x).getImg());
            comida.setPrecio(comidaBebidas.get(x).getPrecio());
            listComidabebidas.add(comida);
        }

        menucore.setBEBIDAS(listComidabebidas);

        for(int x=0; x < comidaPlatillos.size() ;x++){
            ComidaCore comida = realm.createObject(ComidaCore.class);
            comida.setNombre(comidaPlatillos.get(x).getNombre());
            comida.setImg(comidaPlatillos.get(x).getImg());
            comida.setPrecio(comidaPlatillos.get(x).getPrecio());
            listComidaPlatillos.add(comida);
        }

        menucore.setPLATILLOS(listComidaPlatillos);


        for(int x=0; x < comidaComidasPopulares.size() ;x++){
            ComidaCore comida = realm.createObject(ComidaCore.class);
            comida.setNombre(comidaComidasPopulares.get(x).getNombre());
            comida.setImg(comidaComidasPopulares.get(x).getImg());
            comida.setPrecio(comidaComidasPopulares.get(x).getPrecio());
            listComidaComidaspOPULARES.add(comida);
        }

        menucore.setCOMIDAS_POPULARES(listComidaComidaspOPULARES);

        // When the transaction is committed, all changes a synced to disk.
        realm.commitTransaction();

        loading.dismiss();
    }

    private void iniciarVariables() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {

            navigationView.setNavigationItemSelectedListener(this);
            seleccionarItem(navigationView.getMenu().getItem(0));

        }
    }

    private void setRealm() {
        // Create the Realm configuration
        realmConfig = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfig);
    }

    private void seleccionarItem(MenuItem item){
        fragmentoGenerico = null;
        fragmentManager = getSupportFragmentManager();
        int id = item.getItemId();
        switch (id){
            case R.id.item_inicio:
                 fragmentoGenerico = new FragmentoInicio();
                break;
            case R.id.item_cuenta:
                fragmentoGenerico = new FragmentoCuenta();
                break;
            case R.id.item_categorias:
                fragmentoGenerico = new FragmentoCategorias();
                break;
            case R.id.item_configuracion:
                startActivity(new Intent(this, ActividadConfiguracion.class));
                break;
        }

        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.mipmap.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //-->Sombreamos la seleccion
        item.setChecked(true);
        //-->Mandamos el obj para escojer nuestro fragment
        seleccionarItem(item);
        //-->Cerramos una vez que se escojio la OPC.
        drawerLayout.closeDrawers();
        return true;
    }
}
