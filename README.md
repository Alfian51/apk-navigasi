# Alfian04 - Aplikasi Android Navigasi Lengkap

Aplikasi Android berbasis **Java** dan **XML Layout** yang mengimplementasikan arsitektur navigasi Android modern, dibuat sesuai spesifikasi Product Requirement Document (PRD).

---

## 1. Ringkasan Proyek

- **Nama Aplikasi:** Alfian04
- **Platform:** Android
- **Bahasa Pemrograman:** Java (100% tanpa Kotlin)
- **UI:** XML Layout (Material Components)
- **Minimum SDK:** Android 7.0 (API 24)
- **Target SDK:** Android 16 (API 36)
- **Arsitektur:** Activity + 5 Fragments + PageAdapter

---

## 2. Fitur & Komponen yang Diimplementasikan

1. **Toolbar & Hamburger Menu (`ActionBarDrawerToggle`)**
   - Ikon menu hamburger di toolbar kiri atas untuk membuka/menutup panel samping.
2. **Navigation Drawer (`DrawerLayout` + `NavigationView`)**
   - Header aplikasi (`nav_header_main.xml`).
   - Navigasi menu samping menuju 5 halaman utama (Beranda, Fitur Data, Kalkulator, Dokumen, Peta Lokasi) dan Keluar.
3. **Ikon Keluar & Options Menu Responsif di Toolbar**
   - **Ikon Keluar (`ic_exit`)**: Berdiri sendiri tepat di sebelah kiri menu titik tiga untuk akses cepat keluar dari aplikasi.
   - **Menu Titik Tiga (Options Menu)**: Hanya berisi opsi *Tentang Aplikasi*.
   - **Responsif Mode Gelap (Dark Mode)**: Warna background dan teks popup menu menggunakan tema adaptif sehingga teks selalu terbaca jelas baik di mode terang maupun mode gelap.
4. **TabLayout & ViewPager2 (5 Halaman Tab)**
   - Menghubungkan 5 halaman tab dengan transisi swipe yang mulus menggunakan `TabLayoutMediator` dan mode scrollable.
5. **Fragment Architecture (5 Halaman Utama)**:
   - `HomeFragment`: Sambutan, ringkasan pengenalan aplikasi, dan 4 tombol pintas akses cepat antar-halaman.
   - `FeatureFragment`: Formulir input data siswa (Nama, Jurusan, Nilai ujian 0-100) dengan placeholder informatif dan validasi input.
   - `CalculatorFragment`: Kalkulator dengan 4 operasi (+, -, ×, ÷), placeholder pada input angka, penanganan pembagian nol (*division by zero*), dan tombol pembersih (*Clear*).
   - `DocumentFragment`: Dokumentasi teks petunjuk penggunaan dengan `ScrollView` dan tombol navigasi ke halaman peta lokasi.
   - `MapsFragment`: Halaman khusus pencarian lokasi Google Maps dengan input teks lokasi, placeholder informatif, tombol cari dinamis, dan tombol pintas lokasi (Monas Jakarta).
6. **Placeholder di Seluruh Input Text**
   - Seluruh input field (`EditText`) telah dilengkapi teks placeholder/hint yang jelas dan ramah pengguna.
7. **PageAdapter (`MainPagerAdapter`)**
   - Mewarisi `FragmentStateAdapter` murni Java untuk mengelola 5 Fragment pada `ViewPager2`.
8. **Result Page (`ResultActivity`)**
   - Menerima hasil olah data dari `FeatureFragment` via `Intent.putExtra()` dan menampilkan nama siswa, jurusan, nilai, grade (A/B/C/D/E), serta status kelulusan (LULUS/TIDAK LULUS) dengan badge warna indikator.

---

## 3. Struktur Direktori Proyek

```text
app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/Alfian04/
│       │       ├── MainActivity.java        (Toolbar, Drawer, TabLayout, ViewPager2, Options Menu)
│       │       ├── MainPagerAdapter.java   (FragmentStateAdapter)
│       │       ├── HomeFragment.java       (Fragment Beranda & Tombol Pintas)
│       │       ├── FeatureFragment.java    (Fragment Form Data Siswa)
│       │       ├── CalculatorFragment.java (Fragment Kalkulator Matematika)
│       │       ├── DocumentFragment.java   (Fragment Panduan)
│       │       ├── MapsFragment.java       (Fragment Pencarian Google Maps)
│       │       └── ResultActivity.java     (Activity Hasil Form)
│       │
│       ├── res/
│       │   ├── drawable/
│       │   │   └── ic_exit.xml             (Ikon Keluar Toolbar)
│       │   ├── layout/
│       │   │   ├── activity_main.xml
│       │   │   ├── nav_header_main.xml
│       │   │   ├── fragment_home.xml
│       │   │   ├── fragment_feature.xml
│       │   │   ├── fragment_calculator.xml
│       │   │   ├── fragment_document.xml
│       │   │   ├── fragment_maps.xml
│       │   │   └── activity_result.xml
│       │   │
│       │   ├── menu/
│       │   │   ├── drawer_menu.xml
│       │   │   └── main_options_menu.xml
│       │   │
│       │   ├── values/
│       │   │   ├── colors.xml
│       │   │   ├── strings.xml
│       │   │   └── themes.xml
│       │   └── values-night/
│       │       ├── colors.xml
│       │       └── themes.xml
│       │
│       └── AndroidManifest.xml
└── build.gradle.kts
```

---

## 4. Cara Menjalankan & Build Proyek

### Membuka di Android Studio:
1. Buka Android Studio.
2. Pilih menu **Open** dan arahkan ke folder proyek ini (`d:\Android Studio Project\pkl\Alfian04`).
3. Tunggu hingga proses Gradle Sync selesai.
4. Hubungkan emulator atau perangkat fisik Android, lalu klik tombol **Run** (ikon Play segitiga hijau).

### Build APK Debug via Terminal / PowerShell:
```powershell
.\gradlew.bat assembleDebug
```

File APK hasil build otomatis tersimpan di:
```text
app/build/outputs/apk/debug/app-debug.apk
```
