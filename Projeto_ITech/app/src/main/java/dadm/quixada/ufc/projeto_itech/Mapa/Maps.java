package dadm.quixada.ufc.projeto_itech.Mapa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dadm.quixada.ufc.projeto_itech.Components.Usuario;
import dadm.quixada.ufc.projeto_itech.R;

public class Maps extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseFirestore db;
    private List<Usuario> usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getSupportActionBar().hide();

        db = FirebaseFirestore.getInstance();
        usuarios = new ArrayList<>();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        db.collection("Usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        usuarios.clear();
                        for (DocumentSnapshot documentSnapshot : task.getResult()) {
                            Usuario usuario = new Usuario(
                                    documentSnapshot.getString("id"),
                                    documentSnapshot.getString("nome"),
                                    documentSnapshot.getString("email"),
                                    documentSnapshot.getString("senha"),
                                    documentSnapshot.getString("telefone"),
                                    documentSnapshot.getString("endereco"),
                                    documentSnapshot.getString("n_rua"),
                                    documentSnapshot.getString("bairro")
                            );
                            usuarios.add(usuario);
                        }
                        Geocoder geocoder = new Geocoder(Maps.this, Locale.getDefault());
                        ArrayList<List<Address>> arrayList = new ArrayList<>();
                        ArrayList<LatLng> latLngs = new ArrayList<>();
                        ArrayList<String> nomeTitle = new ArrayList<>();

                        try {
                            for (int i = 0; i < usuarios.size(); i++) {
                                arrayList.add(geocoder.getFromLocationName(usuarios.get(i).getRua(), 1));
                                nomeTitle.add(usuarios.get(i).getNome());
                                latLngs.add(new LatLng(arrayList.get(i).get(0).getLatitude(), arrayList.get(i).get(0).getLongitude()));
                                mMap.addMarker(new MarkerOptions()
                                        .position(latLngs.get(i))
                                        .title(nomeTitle.get(i))
                                );
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

        /*Geocoder geocoder = new Geocoder(Maps.this, Locale.getDefault());
        ArrayList<List<Address>> arrayList= new ArrayList<>();
        ArrayList<LatLng> latLngs = new ArrayList<>();
        ArrayList<String> nomeTitle = new ArrayList<>();
        try {
            for(int i = 0; i < usuarios.size(); i++){
                arrayList.add(geocoder.getFromLocationName(usuarios.get(i).getRua(), 1));
                nomeTitle.add(usuarios.get(i).getNome());
                Log.d("Maps", usuarios.get(i).getNome() + " no endereco " + usuarios.get(i).getRua());
                /*for(int i = 0; i < arrayList.size(); i++){
                    latLngs.add(new LatLng(arrayList.get(i).get(0).getLatitude(), arrayList.get(i).get(0).getLongitude()));
                    mMap.addMarker(new MarkerOptions()
                            .position(latLngs.get(i))
                            .title(nomeTitle.get(i))
                    );
                }*/



            /*arrayList.add(geocoder.getFromLocationName("rua 26 de junho", 1));
            arrayList.add(geocoder.getFromLocationName("rua Aristides Alves Cavalcante", 1));

                LatLng latLng = new LatLng(arrayList.get(0).get(0).getLatitude(), arrayList.get(0).get(0).getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(latLng).title("Eu")
                );

            LatLng latLng1 = new LatLng(arrayList.get(1).get(0).getLatitude(), arrayList.get(1).get(0).getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(latLng1).title("Felipe")
            );
            */

       /* } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            List<Address> addressList = geocoder.getFromLocationName("Boa Viagem", 1);
            Log.d("Maps", String.valueOf(addressList.size()));
            if(addressList.size() > 0){
                LatLng latLng = new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(latLng).title("Eu")
                );

            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}