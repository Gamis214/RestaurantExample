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
import android.view.Menu;
import android.view.MenuItem;

import com.example.pro.restaurante_demo.Fragments.Categorias.FragmentoCategorias;
import com.example.pro.restaurante_demo.Fragments.MiCuenta.FragmentoCuenta;
import com.example.pro.restaurante_demo.Fragments.inicio.FragmentoInicio;
import com.example.pro.restaurante_demo.Models.Comida;
import com.example.pro.restaurante_demo.Models.ComidasService;
import com.example.pro.restaurante_demo.R;
import com.example.pro.restaurante_demo.Servicios.menuServiceAPI;
import com.example.pro.restaurante_demo.Utils.Constants;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    private Fragment fragmentoGenerico;
    private ComidasService service_result_comidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregarToolbar();
        iniciarVariables();
        getComidasService();

    }

    private void getComidasService(){

        final ProgressDialog loading = ProgressDialog.show(this, "Obteniendo Datos", "Porfavor espere...", false, false);

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Constants.ROOT_URL_COMIDA_SERVICE)
                .build();

        menuServiceAPI api = adapter.create(menuServiceAPI.class);

        api.getComidasService(new Callback<ComidasService>() {
            @Override
            public void success(ComidasService comidasServices, Response response) {
                loading.dismiss();
                service_result_comidas = comidasServices;
            }

            @Override
            public void failure(RetrofitError error) {
                loading.dismiss();
            }
        });
    }

    private void iniciarVariables() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {

            navigationView.setNavigationItemSelectedListener(this);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));

        }
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
