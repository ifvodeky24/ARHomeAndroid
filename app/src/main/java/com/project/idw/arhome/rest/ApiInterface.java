package com.project.idw.arhome.rest;

import com.project.idw.arhome.response.ResponseBatalBookingKontrakan;
import com.project.idw.arhome.response.ResponseBatalBookingKos;
import com.project.idw.arhome.response.ResponseBookingKontrakan;
import com.project.idw.arhome.response.ResponseBookingKos;
import com.project.idw.arhome.response.ResponseDalamPemesananKontrakan;
import com.project.idw.arhome.response.ResponseDalamPemesananKos;
import com.project.idw.arhome.response.ResponseHapusKontrakan;
import com.project.idw.arhome.response.ResponseHapusKos;
import com.project.idw.arhome.response.ResponseHapusPemesananKontrakan;
import com.project.idw.arhome.response.ResponseHapusPemesananKos;
import com.project.idw.arhome.response.ResponseKontrakanDetail;
import com.project.idw.arhome.response.ResponseKontrakanPemilik;
import com.project.idw.arhome.response.ResponseKontrakans;
import com.project.idw.arhome.response.ResponseKosDetail;
import com.project.idw.arhome.response.ResponseKosPemilik;
import com.project.idw.arhome.response.ResponseKoses;
import com.project.idw.arhome.response.ResponseLoginPemilik;
import com.project.idw.arhome.response.ResponseLoginPengguna;
import com.project.idw.arhome.response.ResponsePemesananKontrakanDetail;
import com.project.idw.arhome.response.ResponsePemesananKontrakans;
import com.project.idw.arhome.response.ResponsePemesananKosDetail;
import com.project.idw.arhome.response.ResponsePemesananKoses;
import com.project.idw.arhome.response.ResponsePemilik;
import com.project.idw.arhome.response.ResponsePemilikDetail;
import com.project.idw.arhome.response.ResponsePengguna;
import com.project.idw.arhome.response.ResponsePenggunaDetail;
import com.project.idw.arhome.response.ResponseRegisterPemilik;
import com.project.idw.arhome.response.ResponseRegisterPengguna;
import com.project.idw.arhome.response.ResponseRekomendasiKontrakans;
import com.project.idw.arhome.response.ResponseRekomendasiKoses;
import com.project.idw.arhome.response.ResponseSelesaiKontrakan;
import com.project.idw.arhome.response.ResponseSelesaiKos;
import com.project.idw.arhome.response.ResponseTambahKontrakan;
import com.project.idw.arhome.response.ResponseTambahKos;
import com.project.idw.arhome.response.ResponseUncheckRatingKontrakan;
import com.project.idw.arhome.response.ResponseUncheckRatingKos;
import com.project.idw.arhome.response.ResponseUpdateKontrakan;
import com.project.idw.arhome.response.ResponseUpdateKos;
import com.project.idw.arhome.response.ResponseUpdatePemilik;
import com.project.idw.arhome.response.ResponseUpdatePengguna;
import com.project.idw.arhome.response.ResponseUpdateRatingReviewKontrakan;
import com.project.idw.arhome.response.ResponseUpdateRatingReviewKos;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    //untuk login pemilik
    @FormUrlEncoded
    @POST("pemilik/login")
    Call<ResponseLoginPemilik> loginPemilik(
            @Field("email") String email,
            @Field("password") String password
    );

    //untuk register pemilik
    @FormUrlEncoded
    @POST("pemilik/register")
    Call<ResponseRegisterPemilik> registerPemilik(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("no_kk") String no_kk,
            @Field("alamat") String alamat,
            @Field("foto") String foto,
            @Field("no_handphone") String no_handphone
    );

    //untuk get detail pengguna
    @GET("pengguna/get-all")
    Call<ResponsePengguna> penggunaGetAll(
    );

    //untuk get seluruh pengguna
    @GET("pengguna/by-id")
    Call<ResponsePenggunaDetail> penggunaById(
        @Query("id_pengguna") String id_pengguna
    );

    //untuk get seluruh pemilik
    @GET("pemilik/get-all")
    Call<ResponsePemilik> pemilikGetAll(
    );

    //untuk get detail pemilik
    @GET("pemilik/by-id")
    Call<ResponsePemilikDetail> pemilikById(
            @Query("id_pemilik") String id_pemilik
    );

    //untuk register pengguna
    @FormUrlEncoded
    @POST("pengguna/register")
    Call<ResponseRegisterPengguna> registerPengguna(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("alamat") String alamat,
            @Field("foto") String foto,
            @Field("no_handphone") String no_handphone
    );

    //untuk update pemilik
    @FormUrlEncoded
    @POST("pemilik/update-pemilik")
    Call<ResponseUpdatePemilik> updatePemilik(
            @Field("id_pemilik") String id_pemilik,
            @Field("username") String username,
            @Field("email") String email,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("no_kk") String no_kk,
            @Field("alamat") String alamat,
            @Field("no_handphone") String no_handphone
    );

    //untuk upload foto pemilik
    @FormUrlEncoded
    @POST("pemilik/upload-foto-pemilik")
    Call<ResponseUpdatePemilik> uploadFotoPemilik(
            @Field("id_pemilik") String id_pemilik,
            @Field("foto") String foto
    );

    //untuk upload password pemilik
    @FormUrlEncoded
    @POST("pemilik/update-password-pemilik")
    Call<ResponseUpdatePemilik> updatePasswordPemilik(
            @Field("id_pemilik") String id_pemilik,
            @Field("password") String password
    );

    //untuk update pengguna
    @FormUrlEncoded
    @POST("pengguna/update-pengguna")
    Call<ResponseUpdatePengguna> updatePengguna(
            @Field("id_pengguna") String id_pengguna,
            @Field("username") String username,
            @Field("email") String email,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("alamat") String alamat,
            @Field("no_handphone") String no_handphone
    );

    //untuk upload foto pengguna
    @FormUrlEncoded
    @POST("pengguna/upload-foto-pengguna")
    Call<ResponseUpdatePengguna> uploadFotoPengguna(
            @Field("id_pengguna") String id_pengguna,
            @Field("foto") String foto
    );

    //untuk update password pengguna
    @FormUrlEncoded
    @POST("pengguna/update-password-pengguna")
    Call<ResponseUpdatePengguna> updatePasswordPengguna(
            @Field("id_pengguna") String id_pengguna,
            @Field("password") String password
    );



    //untuk insert Google Sign In
    @FormUrlEncoded
    @POST("pengguna/insert-google-sign-in")
    Call<ResponseRegisterPengguna> insertGoogleSignIn(
            @Field("email") String email,
            @Field("foto") String foto
    );

    //untuk login pengguna
    @FormUrlEncoded
    @POST("pengguna/login")
    Call<ResponseLoginPengguna> loginPengguna(
            @Field("email") String email,
            @Field("password") String password
    );

    //untuk get kontrakan
    @GET("kontrakan/get-all")
    Call<ResponseKontrakans> kontrakanGetAll(
    );

    //untuk get kos
    @GET("kos/get-all")
    Call<ResponseKoses> kosGetAll(

    );

    //untuk get rekomendasi kos
    @GET("kos/get-all-rekomendasi-kos")
    Call<ResponseRekomendasiKoses> rekomendasiKosGetAll(

    );

    //untuk get rekomendasi kontrakan
    @GET("kontrakan/get-all-rekomendasi-kontrakan")
    Call<ResponseRekomendasiKontrakans> rekomendasiKontrakanGetAll(

    );

    //untuk get kos dengan parameter
    @GET("kos/get-all-with-parameter")
    Call<ResponseKoses> kosGetAllWithParameter(
            @Query("harga_min") String harga_min,
            @Query("harga_max") String harga_max
//            ,
//            @Query("rating") String rating

    );

    //untuk get kontrakan dengan parameter
    @GET("kontrakan/get-all-with-parameter")
    Call<ResponseKontrakans> kontrakanGetAllWithParameter(
            @Query("harga_min") String harga_min,
            @Query("harga_max") String harga_max
//            ,
//            @Query("rating") String rating

    );

    //untuk get kontrakan detail
    @GET("kontrakan/by-id")
    Call<ResponseKontrakanDetail> kontrakanById(
            @Query("id_kontrakan") String id_kontrakan

    );

    //untuk get kos detail
    @GET("kos/by-id")
    Call<ResponseKosDetail> kosById(
            @Query("id_kos") String id_kos

    );

    //untuk tambah kontrakan
    @FormUrlEncoded
    @POST("kontrakan/tambah-kontrakan")
    Call<ResponseTambahKontrakan> tambahKontrakan(
            @Field("nama") String nama,
            @Field("deskripsi") String deskripsi,
            @Field("alamat") String alamat,
            @Field("fasilitas") String fasilitas,
            @Field("foto") String foto,
            @Field("foto_2") String foto_2,
            @Field("foto_3") String foto_3,
            @Field("id_pemilik") String id_pemilik,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("harga") String harga

    );

    //untuk tambah kos
    @FormUrlEncoded
    @POST("kos/tambah-kos")
    Call<ResponseTambahKos> tambahKos(
            @Field("nama") String nama,
            @Field("deskripsi") String deskripsi,
            @Field("alamat") String alamat,
            @Field("fasilitas") String fasilitas,
            @Field("foto") String foto,
            @Field("id_pemilik") String id_pemilik,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("harga") String harga,
            @Field("stok_kamar") String stok_kamar,
            @Field("jenis_kos") String jenis_kos
    );

    //untuk get pemesanan kontrakan by id_pemilik
    @GET("pemesanan-kontrakan/get-all-by-pemilik")
    Call<ResponsePemesananKontrakans> pemesananKontrakanGetAllByPemilik(
            @Query("id_pemilik") String id_pemilik

    );

    //untuk get pemesanan kontrakan by id_pengguna
    @GET("pemesanan-kontrakan/get-all-by-pengguna")
    Call<ResponsePemesananKontrakans> pemesananKontrakanGetAllByPengguna(
            @Query("id_pengguna") String id_pengguna

    );

    //untuk get pemesanan kontrakan detail by id_pemesanan_kontrakan
    @GET("pemesanan-kontrakan/by-id")
    Call<ResponsePemesananKontrakanDetail> pemesananKontrakanById(
            @Query("id_pemesanan_kontrakan") String id_pemesanan_kontrakan

    );

    //untuk get pemesanan kos by id-pemilik
    @GET("pemesanan-kos/get-all-by-pemilik")
    Call<ResponsePemesananKoses> pemesananKosesGetAllByPemilik(
            @Query("id_pemilik") String id_pemilik

    );

    //untuk get pemesanan kos by id-pengguna
    @GET("pemesanan-kos/get-all-by-pengguna")
    Call<ResponsePemesananKoses> pemesananKosesGetAllByPengguna(
            @Query("id_pengguna") String id_pengguna

    );

    //untuk get pemesanan kos detail by id_pemesanan_kos
    @GET("pemesanan-kos/by-id")
    Call<ResponsePemesananKosDetail> pemesananKosById(
            @Query("id_pemesanan_kos") String id_pemesanan_kos

    );

    //untuk get kontrakan filter by id_pemilik
    @GET("kontrakan/get-all-by-pemilik-kontrakan")
    Call<ResponseKontrakanPemilik> kontrakanGetAllByPemilikKontrakan(
            @Query("id_pemilik") String id_pemilik

    );

    //untuk get kos filter by id_pemilik
    @GET("kos/get-all-by-pemilik-kos")
    Call<ResponseKosPemilik> kosGetAllByPemilikKos(
            @Query("id_pemilik") String id_pemilik

    );

    //untuk booking kontrakan
    @FormUrlEncoded
    @POST("pemesanan-kontrakan/booking-kontrakan")
    Call<ResponseBookingKontrakan> bookingKontrakan(
            @Field("id_pengguna") String id_pengguna,
            @Field("id_kontrakan") String id_kontrakan
    );

    //untuk booking kos
    @FormUrlEncoded
    @POST("pemesanan-kos/booking-kos")
    Call<ResponseBookingKos> bookingKos(
            @Field("id_pengguna") String id_pengguna,
            @Field("id_kos") String id_kos
    );

    //untuk update menjadi dalam pemesanan kontrakan
    @FormUrlEncoded
    @POST("pemesanan-kontrakan/update-pemesanan-kontrakan-dalam-pemesanan")
    Call<ResponseDalamPemesananKontrakan> dalamPemesananKontrakan(
            @Field("id_pemesanan_kontrakan") String id_pemesanan_kontrakan
    );

    //untuk update menjadi dalam pemesanan kos
    @FormUrlEncoded
    @POST("pemesanan-kos/update-pemesanan-kos-dalam-pemesanan")
    Call<ResponseDalamPemesananKos> dalamPemesananKos(
            @Field("id_pemesanan_kos") String id_pemesanan_kos
    );

    //untuk update menjadi selesai kontrakan
    @FormUrlEncoded
    @POST("pemesanan-kontrakan/update-pemesanan-kontrakan-selesai")
    Call<ResponseSelesaiKontrakan> selesaiKontrakan(
            @Field("id_pemesanan_kontrakan") String id_pemesanan_kontrakan
    );

    //untuk update menjadi selesai kos
    @FormUrlEncoded
    @POST("pemesanan-kos/update-pemesanan-kos-selesai")
    Call<ResponseSelesaiKos> selesaiKos(
            @Field("id_pemesanan_kos") String id_pemesanan_kos
    );

    //untuk hapus pemesanan kos
    @FormUrlEncoded
    @POST("pemesanan-kos/delete-pemesanan-kos")
    Call<ResponseHapusPemesananKos> hapusPemesananKos(
            @Field("id_pemesanan_kos") String id_pemesanan_kos
    );

    //untuk hapus pemesanan kontrakan
    @FormUrlEncoded
    @POST("pemesanan-kontrakan/delete-pemesanan-kontrakan")
    Call<ResponseHapusPemesananKontrakan> hapusPemesananKontrakan(
            @Field("id_pemesanan_kontrakan") String id_pemesanan_kontrakan
    );

    //untuk hapus kontrakan
    @FormUrlEncoded
    @POST("kontrakan/delete-kontrakan")
    Call<ResponseHapusKontrakan> hapusKontrakan(
            @Field("id_kontrakan") String id_kontrakan
    );

    //untuk hapus kos
    @FormUrlEncoded
    @POST("kos/delete-kos")
    Call<ResponseHapusKos> hapusKos(
            @Field("id_kos") String id_kos
    );

    //untuk update kontrakan
    @FormUrlEncoded
    @POST("kontrakan/update-kontrakan")
    Call<ResponseUpdateKontrakan> updateKontrakan(
            @Field("nama") String nama,
            @Field("deskripsi") String deskripsi,
            @Field("alamat") String alamat,
            @Field("fasilitas") String fasilitas,
            @Field("foto") String foto,
            @Field("foto_2") String foto_2,
            @Field("foto_3") String foto_3,
            @Field("id_pemilik") String id_pemilik,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("harga") String harga
    );

    //untuk update kos
    @FormUrlEncoded
    @POST("kos/update-kos")
    Call<ResponseUpdateKos> updateKos(
            @Field("nama") String nama,
            @Field("deskripsi") String deskripsi,
            @Field("alamat") String alamat,
            @Field("fasilitas") String fasilitas,
            @Field("foto") String foto,
            @Field("foto_2") String foto_2,
            @Field("foto_3") String foto_3,
            @Field("id_pemilik") String id_pemilik,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("harga") String harga,
            @Field("stok_kamar") String stok_kamar,
            @Field("jenis_kos") String jenis_kos
    );

    //untuk batal booking kos
    @FormUrlEncoded
    @POST("pemesanan-kos/batal-booking-kos")
    Call<ResponseBatalBookingKos> batalBookingKos(
            @Field("id_pemesanan_kos") String id_pemesanan_kos
    );

    //untuk batal booking kontrakan
    @FormUrlEncoded
    @POST("pemesanan-kontrakan/batal-booking-kontrakan")
    Call<ResponseBatalBookingKontrakan> batalBookingKontrakan(
            @Field("id_pemesanan_kontrakan") String id_pemesanan_kontrakan
    );

    //untuk check rating dan review kontrakan sudah terisi atau belum
    @GET("pemesanan-kontrakan/check-unrating-kontrakan")
    Call<ResponseUncheckRatingKontrakan> checkUnratingKontrakan(
            @Query("id_pengguna") String id_pengguna
    );

    //untuk check rating dan review kos sudah terisi atau belum
    @GET("pemesanan-kos/check-unrating-kos")
    Call<ResponseUncheckRatingKos> checkUnratingKos(
            @Query("id_pengguna") String id_pengguna
    );

    //untuk update rating dan review kos
    @FormUrlEncoded
    @POST("pemesanan-kos/update-rating-review-kos")
    Call<ResponseUpdateRatingReviewKos> updateRatingReviewKos(
            @Field("id_pemesanan_kos") String id_pemesanan_kos,
            @Field("review") String review,
            @Field("rating") String rating
    );

    //untuk update rating dan review kontrakan
    @FormUrlEncoded
    @POST("pemesanan-kontrakan/update-rating-review-kontrakan")
    Call<ResponseUpdateRatingReviewKontrakan> updateRatingReviewKontrakan(
            @Field("id_pemesanan_kontrakan") String id_pemesanan_kontrakan,
            @Field("review") String review,
            @Field("rating") String rating
    );
}
