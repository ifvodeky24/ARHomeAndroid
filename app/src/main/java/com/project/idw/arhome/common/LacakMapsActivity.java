package com.project.idw.arhome.common;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.project.idw.arhome.R;
import com.project.idw.arhome.model.RekomendasiKontrakans;
import com.project.idw.arhome.model.RekomendasiKoses;
import com.project.idw.arhome.pengguna.DetailRekomendasiKontrakanActivity;
import com.project.idw.arhome.pengguna.DetailRekomendasiKosActivity;
import com.project.idw.arhome.response.ResponseKontrakans;
import com.project.idw.arhome.response.ResponseKoses;
import com.project.idw.arhome.rest.ApiClient;
import com.project.idw.arhome.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.gms.location.places.AutocompleteFilter.TYPE_FILTER_ADDRESS;

public class LacakMapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    EditText et_cari_lokasi;
    Button btn_filter;
    ApiInterface apiService;
    EditText edt_harga_min_kontrakan_kos, edt_harga_max_kontrakan_kos;
    RatingBar rt_bar;
    Button btn_lacak_filter;
    TextView tv_rate;

    public String nama;
    public Double lat;
    public Double lng;
    public int id;
    public int code;
    public double rating;

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Lacak Lokasi");
        setContentView(R.layout.activity_lacak_maps);

        btn_filter = findViewById(R.id.btn_filter);

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps_lacak_kontrakan_kos);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_filter_dialog();
            }
        });

        apiService = ApiClient.getClient().create(ApiInterface.class);

        //fungsi untuk mendapatkan data kos dan kontrakan awal

        getKos("0", "100000000");

        getKontrakan("0", "100000000");

        PlaceAutocompleteFragment places= (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 12.0f));
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("ID")
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_GEOCODE)
                .build();

    }

    private void getKontrakan(String harga_min, String harga_max) {
        apiService.kontrakanGetAll().enqueue(new Callback<ResponseKontrakans>() {
            @Override
            public void onResponse(Call<ResponseKontrakans> call, Response<ResponseKontrakans> response) {
                if (response.isSuccessful()){
                    System.out.println("response GetKontrakan"+response);
                    if (response.body() != null) {
                        for (int i = 0; i < response.body().getMaster().size() ; i++) {
                            nama = response.body().getMaster().get(i).getNama();
                            lat = response.body().getMaster().get(i).getLatitude();
                            lng = response.body().getMaster().get(i).getLongitude();
                            id = response.body().getMaster().get(i).getIdKontrakan();
                            code = 1;

                            addMarker(id, nama, new LatLng(lat, lng), "Kontrakan", code);

                            System.out.println("lat"+lat+ "lng"+lng);

                            System.out.println("Menambahkan Marker "+nama+" "+id);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKontrakans> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getKos(String harga_min, String harga_max) {
        apiService.kosGetAll().enqueue(new Callback<ResponseKoses>() {
            @Override
            public void onResponse(Call<ResponseKoses> call, Response<ResponseKoses> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        for (int i = 0; i < response.body().getMaster().size() ; i++) {
                            nama = response.body().getMaster().get(i).getNama();
                            lat = response.body().getMaster().get(i).getLatitude();
                            lng = response.body().getMaster().get(i).getLongitude();
                            id = response.body().getMaster().get(i).getIdKos();
                            code = 0;

                            addMarker(id, nama, new LatLng(lat, lng), "Kos", code);

                            System.out.println("lat"+lat+ "lng"+lng);

                            System.out.println("Menambahkan Marker "+nama+" "+id);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKoses> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke Server", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void show_filter_dialog() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LacakMapsActivity.this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle("ARHome Filter");
        alertDialogBuilder.setIcon(R.drawable.ic_icon);

        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.filter_kontrakan_kos_dialog, null);

        alertDialogBuilder.setView(dialogView);

        edt_harga_min_kontrakan_kos = dialogView.findViewById(R.id.edt_harga_min_kontrakan_kos);
        edt_harga_max_kontrakan_kos = dialogView.findViewById(R.id.edt_harga_max_kontrakan_kos);
//        rt_bar= dialogView.findViewById(R.id.rt_bar);
//        tv_rate = dialogView.findViewById(R.id.tv_rate);
        btn_lacak_filter = dialogView.findViewById(R.id.btn_lacak_filter);

        final AlertDialog alertDialog = alertDialogBuilder.create();

//        rt_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                tv_rate.setText("Skor: "+ String.valueOf(rating));
//            }
//        });

        btn_lacak_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirim_filter();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void kirim_filter() {
        String harga_min_kontrakan_kos = edt_harga_min_kontrakan_kos.getText().toString();
        String harga_max_kontrakan_kos = edt_harga_max_kontrakan_kos.getText().toString();
//        String rating_kontrakan_kos = tv_rate.getText().toString();

        System.out.println("nilai : "+harga_max_kontrakan_kos+harga_min_kontrakan_kos);

        mMap.clear();

        apiService.kosGetAllWithParameter(harga_min_kontrakan_kos,
                harga_max_kontrakan_kos).enqueue(new Callback<ResponseKoses>() {
            @Override
            public void onResponse(Call<ResponseKoses> call, Response<ResponseKoses> response) {
                System.out.println(response.toString());
                if (response.isSuccessful()){
                    System.out.println(response.body().toString());

                    for (int i = 0; i < response.body().getMaster().size(); i++) {
                        nama = response.body().getMaster().get(i).getNama();
                        lat = response.body().getMaster().get(i).getLatitude();
                        lng = response.body().getMaster().get(i).getLongitude();
                        id = response.body().getMaster().get(i).getIdKos();
//                        rating = response.body().getMaster().get(i).getRating();
                        code = 0;

                        addMarker(id, nama, new LatLng(lat, lng), "Kos", code);

                        System.out.println("Menambahkan Marker "+nama);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKoses> call, Throwable t) {
                t.printStackTrace();
            }
        });

        apiService.kontrakanGetAllWithParameter(harga_min_kontrakan_kos,
                harga_max_kontrakan_kos).enqueue(new Callback<ResponseKontrakans>() {
            @Override
            public void onResponse(Call<ResponseKontrakans> call, Response<ResponseKontrakans> response) {
                System.out.println(response.toString());
                if (response.isSuccessful()){
                    System.out.println(response.body().toString());

                    for (int i = 0; i < response.body().getMaster().size(); i++) {
                        nama = response.body().getMaster().get(i).getNama();
                        lat = response.body().getMaster().get(i).getLatitude();
                        lng = response.body().getMaster().get(i).getLongitude();
                        id = response.body().getMaster().get(i).getIdKontrakan();
//                        rating = response.body().getMaster().get(i).getRating();
                        code = 1;

                        addMarker(id, nama, new LatLng(lat, lng), "Kontrakan", code);

                        System.out.println("Menambahkan Marker "+nama);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKontrakans> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(15);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(0.507068,101.447779)));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                int id = Integer.parseInt(marker.getTitle().split(" ")[0]);
                String nama = marker.getTitle().split(" ")[1];
                int code = Integer.parseInt(marker.getSnippet());

                Toast.makeText(getApplicationContext(), nama, Toast.LENGTH_SHORT).show();

//               System.out.println("cek keadaan : " +id+" "+ kategori + " " + " " + nama+ " " + code);

                if (code==0){
                    Intent intent = new Intent(LacakMapsActivity.this, DetailRekomendasiKosActivity.class);
                    intent.putExtra(RekomendasiKoses.TAG_ID, id);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(LacakMapsActivity.this, DetailRekomendasiKontrakanActivity.class);
                    intent.putExtra(RekomendasiKontrakans.TAG_ID, id);
                    startActivity(intent);
                }




            }
        });


        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // do you work now
                            mMap.setMyLocationEnabled(true);
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    public void addMarker(int id, String nama, LatLng latLng, String kategori, int code){
        BitmapDescriptor icon;

        if (kategori.equalsIgnoreCase("Kos") && code==0){
            icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_kos);
        }else {
            icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_kontrakan);
        }

        // Membuat marker
        MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.snippet(String.valueOf(code));
                // Mengatur posisi marker
                markerOptions.position(latLng);

                System.out.println("latLng"+latLng);
                // Mengatur gambar ikon marker
                markerOptions.icon(icon);
                // Mengatur judul dari marker
                // Akan ditampilkan saat marker di klik
                markerOptions.title(id+" "+nama);

        // Placing a marker on the touched position
        mMap.addMarker(markerOptions);

    }
}


