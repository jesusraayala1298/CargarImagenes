package net.jesusramirez.cargarimagenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //OBJETOS PARA EL MANEJO DEL RECYCLER VIEW Y EL MANEJO DE LA SOLICITUD
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RequestQueue queue;
    List<Heroe> listaH;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        listaH= new ArrayList<>();
        solicitud();
    }

    //EN ESTE METODO SE REALIZA LA PETICION A INTERNET Y SE RECUPERA LA INFOMACION JSON
    private void solicitud(){
        //URL DE LA PAGINA QUE CONTIENE LA INFORMACION
        String url="https://simplifiedcoding.net/demos/view-flipper/heroes.php";
        //OBTENER LA INFORMACION EN TEXTO PLANO
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //EL JSONOBJECT SE USA PARA CONVERTIR A OBJETOS JSON LA INFORMACION DE LA PAGINA
                            JSONObject obj = new JSONObject(response);

                            //EL OBJETO JSON ES CONVERTIDO A UN ARREGLO PARA SEPARAR LA INFORMACION DE LA PAGINA
                            JSONArray heroArray = obj.getJSONArray("heroes");

                            //SE ITERA EL JSON ARRAY PARA OBTENER LA INFORMACIONY ALMACENARLA EN OBJETOS DE JAVA
                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);
                                //POR CADA JSONOBJECT SE OBTIENEN LOS CAMPOS DE NAME Y IMAGEURL Y SE COLOCAN EN UN OBJETO
                                //DE JAVA DEFINIDO
                                Heroe hero = new Heroe(heroObject.getString("name"), heroObject.getString("imageurl"));
                                Log.d("name",hero.name);
                                Log.d("url",hero.imageUrl);
                                //SE AÑADE EL OBJETO JAVA A UNA LISTA DE OBJETOS
                                listaH.add(hero);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //SE MANDA LLAMAR UN METODO PARA COLOCAR LOS DATOS EN EL RECYCLER VIEW
                        llenarRecycler();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //SE AÑADE LA RECUEST A LA COLA PARA QUE FUNCIONE LA SOLICITUD
        queue.add(stringRequest);
    }

    //SE CONFIGURA EL RECYCLER CON SU LAYOUT MANAGER Y SU RESPECTIVO ADAPTADOR
    public void llenarRecycler(){
        mRecyclerView= findViewById(R.id.listaImagenes);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdaptadorRecycler(getApplicationContext(),listaH);
        mRecyclerView.setAdapter(mAdapter);
    }
}