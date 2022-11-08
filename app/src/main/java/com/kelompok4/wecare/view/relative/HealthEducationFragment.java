package com.kelompok4.wecare.view.relative;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.databinding.FragmentHealthEducationBinding;
import com.kelompok4.wecare.model.healthEducation.GetHealthEducation;
import com.kelompok4.wecare.model.healthEducation.HealthEducation;
import com.kelompok4.wecare.view.relative.adapter.HealthEducationAdapter;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthEducationFragment extends Fragment {

    private FragmentHealthEducationBinding binding;
    private HealthEducationAdapter adapter;

    ApiInterface mApiInterface;
    public static Fragment thisFragment;

    public HealthEducationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.healtheduRecvie.setLayoutManager(layoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        thisFragment = this;
        binding.progressBar.setVisibility(View.VISIBLE);
        refresh();
//
//
//        List<HealthEducation> items = new ArrayList<>();
//        items.add(new HealthEducation( "0","Pelajari CPR untuk Selamatkan Nyawa Seseorang", "CPR dilakukan terhadap orang yang tidak mampu bernapas atau mengalami henti jantung akibat suatu hal, misalnya tenggelam atau serangan jantung. Dengan mengembalikan fungsi napas dan jantung, CPR dapat menyelamatkan nyawa seseorang.",
//                "CPR dilakukan terhadap orang yang tidak mampu bernapas atau mengalami henti jantung akibat suatu hal, misalnya tenggelam atau serangan jantung. Dengan mengembalikan fungsi napas dan jantung, CPR dapat menyelamatkan nyawa seseorang. \\n\\n\n" +
//                        "\n" +
//                        "CPR (cardiopulmonary resuscitation) atau dikenal juga dengan sebutan RJP (resusitasi jantung paru) adalah upaya pertolongan medis untuk mengembalikan kemampuan bernapas dan sirkulasi darah dalam tubuh.\\n\\n\n" +
//                        "\n" +
//                        "Pelajari CPR untuk Selamatkan Nyawa Seseorang - Alodokter\\n\\n\n" +
//                        "\n" +
//                        "Terhentinya aliran darah atau pernapasan bisa memicu kerusakan otak yang dapat mengakibatkan seseorang meninggal dalam hitungan 8–10 menit.\\n\\n\n" +
//                        "\n" +
//                        "Dengan pemberian CPR, aliran darah yang mengandung oksigen akan tetap tersalurkan ke otak dan seluruh tubuh hingga orang tersebut mendapatkan bantuan medis lebih lanjut.\\n\\n\n" +
//                        "\n" +
//                        "Beberapa Hal yang Perlu Diperhatikan Sebelum Melakukan CPR \\n\\n\n" +
//                        "Sebelum memberikan CPR, ada beberapa hal yang harus Anda perhatikan, di antaranya:\\n\\n\n" +
//                        "\n" +
//                        "1. Periksa keamanan lokasi sekitar\\n\\n\n" +
//                        "Pastikan lokasi dan lingkungan di sekitar orang yang tidak sadarkan diri tersebut aman. Misalnya, jika korban ditemukan di tengah jalan, lakukan evakuasi korban ke tempat yang lebih aman sebelum melakukan CPR.\\n\\n\n" +
//                        "\n" +
//                        "2. Periksa kesadaran orang yang akan ditolong\\n\\n\n" +
//                        "Periksa tingkat kesadaran korban dengan mencoba menanyakan namanya dengan suara yang cukup lantang atau menggoyangkan tubuhnya secara perlahan. Jika ia merespons, upayakan agar korban tetap sadarkan diri hingga bantuan tiba. Namun, tetap periksa pernapasan, denyut nadi, dan tingkat responsnya.\\n\\n\n" +
//                        "\n" +
//                        "3. Evaluasi pernapasan \\n\\n\n" +
//                        "Pastikan korban masih bernapas secara normal dengan melihat apakah dadanya bergerak naik-turun. Selanjutnya, dekatkan telinga Anda ke mulut dan hidung korban untuk mendengar suara napas dan merasakan embusan napasnya di pipi Anda.\\n\\n\n" +
//                        "\n" +
//                        "4. Periksa nadi\\n\\n\n" +
//                        "Pastikan jantung korban tetap berdetak dengan memeriksa denyut nadi di pergelangan tangannya atau memeriksa denyut nadi di bagian sisi lehernya.\\n\\n\n" +
//                        "\n" +
//                        "5. Panggil bantuan medis\\n\\n\n" +
//                        "Jika orang yang hendak ditolong tidak menunjukkan respons atau tidak sadarkan diri, segera hubungi tenaga medis di nomor 112 atau rumah sakit terdekat dan lakukan CPR hingga bantuan datang.\\n\\n\n" +
//                        "\n" +
//                        "Cara Melakukan CPR\\n\\n\n" +
//                        "CPR dapat dilakukan oleh semua orang yang sudah terlatih. Teknik ini terbagi menjadi tiga tahapan yang dikenal dengan istilah C-A-B (compression, airways, breathing).\\n\\n\n" +
//                        "\n" +
//                        "Berikut ini adalah penjelasan tentang cara pemberian CPR kepada orang dewasa yang tidak sadarkan diri:\\n\\n\n" +
//                        "\n" +
//                        "Tahap kompresi dada (compression)\\n\\n\n" +
//                        "Bila korban tidak sadarkan diri dan denyut jantungnya tidak terdeteksi, langkah awal CPR dapat dilakukan dengan tindakan kompresi dada. Berikut ini adalah cara melakukannya:\\n\\n\n" +
//                        "\n" +
//                        "Baringkan tubuh korban di atas permukaan yang keras dan datar, lalu posisikan diri Anda berlutut di samping leher dan bahu korban.\\n\\n\n" +
//                        "Letakkan satu telapak tangan Anda di bagian tengah dada pasien, tepatnya di antara payudara.\\n\\n\n" +
//                        "Posisikan telapak tangan Anda yang lain di atas tangan pertama. Pastikan posisi siku Anda lurus dan bahu berada tepat di atas tangan Anda.\\n\\n\n" +
//                        "Tekan dada korban setidaknya 100–120 kali per menit, dengan kecepatan 1–2 tekanan per detik.\\n\\n\n" +
//                        "Saat menekan, gunakan kekuatan tubuh bagian atas. Jangan hanya mengandalkan kekuatan lengan agar tekanan yang dihasilkan lebih kuat.\\n\\n\n" +
//                        "Cek apakah terlihat tanda-tanda pasien bernapas atau menunjukkan respons. Jika belum, Anda bisa melanjutkan proses kompresi dada hingga tenaga medis datang atau mulai mencoba membuka jalur napas korban untuk memberikan napas buatan.\\n\\n\n" +
//                        "\n" +
//                        "Tahap membuka jalur napas (airways)\\n\\n\n" +
//                        "Tahap ini biasanya dilakukan setelah tindakan kompresi. Untuk membuka jalur napas korban, Anda bisa mencoba untuk mendongakkan kepalanya, kemudian letakkan tangan Anda di dahirnya. Selanjutnya, angkat dagu pasien secara perlahan untuk membuka saluran napas.\\n\\n\n" +
//                        "\n" +
//                        "Tahap pemberian napas buatan dari mulut ke mulut (breathing)\\n\\n\n" +
//                        "Setelah mengamankan saluran pernapasan korban, Anda bisa mulai memberikan napas buatan. Namun, langkah ini hanya dilakukan apabila Anda sudah terlatih.\\n\\n\n" +
//                        "\n" +
//                        "Pemberian napas buatan bisa dilakukan dari mulut ke mulut atau dari mulut ke hidung, terutama jika mulut terluka parah atau tidak bisa dibuka. Cara memberikan napas buatan adalah sebagai berikut:\\n\\n\n" +
//                        "\n" +
//                        "Jepit hidung korban, lalu tempatkan mulut Anda ke mulutnya.\\n\\n\n" +
//                        "Berikan napas atau udara dari mulut Anda sebanyak 2 kali sambil melihat apakah bagian dadanya terangkat seperti orang bernapas atau belum. Jika belum, coba perbaiki posisi lehernya atau periksa kembali apakah terdapat sumbatan pada jalan napasnya.\\n\\n\n" +
//                        "Ulangi proses kompresi dada sebanyak 30 kali yang diikuti oleh 2 kali pemberian napas buatan.\\n\\n\n" +
//                        "Tidak ada salahnya membekali diri dengan pengetahuan mengenai cara melakukan CPR, karena bisa saja suatu saat Anda dihadapkan pada situasi di mana keterampilan ini sangat dibutuhkan untuk menyelamatkan nyawa orang lain.\\n\\n\n" +
//                        "\n" +
//                        "Pada kondisi di mana seseorang mengalami henti napas dan henti jantung, Anda dapat melakukan CPR hingga dokter atau tenaga medis tiba di lokasi.\\n\\n\"", "AloDokter.com", ""));
//        items.add(new HealthEducation("1", "Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident", "content", "Dr. Raeya", "from api"));
//        items.add(new HealthEducation("2", "Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident", "content", "Dr. Raeya", "from api"));
//        items.add(new HealthEducation("3", "Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident", "content", "Dr. Raeya", "from api"));
//        items.add(new HealthEducation("4", "Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident", "content", "Dr. Raeya", "from api"));
//        items.add(new HealthEducation("5", "Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident", "content", "Dr. Raeya", "from api"));
//        items.add(new HealthEducation("6", "Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident", "content", "Dr. Raeya", "from api"));
//        items.add(new HealthEducation("7", "Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident", "content", "Dr. Raeya", "from api"));
//        items.add(new HealthEducation("8", "Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident", "content", "Dr. Raeya", "from api"));
//
//        adapter = new HealthEducationAdapter(items);
//        binding.healtheduRecvie.setAdapter(adapter);

    }

    private void refresh() {
        Call<GetHealthEducation> healthEducationCall = mApiInterface.getHealthEducation();
        healthEducationCall.enqueue(new Callback<GetHealthEducation>() {

            @Override
            public void onResponse(Call<GetHealthEducation> call, Response<GetHealthEducation> response) {
                List<HealthEducation> HealthEducationList = response.body().getListHealthEducations();
                Log.d("Retrofit GET", "jumlah healthEducation data: " + String.valueOf(HealthEducationList.size()));
                adapter = new HealthEducationAdapter(HealthEducationList);
                binding.healtheduRecvie.setAdapter(adapter);
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetHealthEducation> call, Throwable t) {
                Log.e("Retrofit GET", "onFailure: " + t.toString());
            }

        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHealthEducationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}