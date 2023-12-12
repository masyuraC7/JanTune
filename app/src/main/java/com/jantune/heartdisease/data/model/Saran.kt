package com.jantune.heartdisease.data.model

data class Saran(
    val title : String,
    val description : String
)

val saranPencegahan = listOf(
    Saran("Makan Sehat (Tinggi Serat, Rendah Lemak Jenuh):","Pilih makanan yang kaya serat dan rendah lemak jenuh untuk mendukung kesehatan jantung dan sistem pencernaan"),
    Saran("Aktivitas Fisik Teratur (Minimal 150 Menit/Minggu):","Lakukan aktivitas fisik secara teratur selama setidaknya 150 menit setiap minggu untuk menjaga kesehatan jantung, mengendalikan berat badan, dan meningkatkan kesejahteraan fisik dan mental."),
    Saran("Pengelolaan Stres (Meditasi, Olahraga):","Terapkan teknik pengelolaan stres seperti meditasi atau olahraga untuk menjaga keseimbangan mental dan fisik."),
    Saran("Pertahankan Berat Badan Sehat:","Jaga berat badan ideal dengan kombinasi pola makan sehat dan aktivitas fisik."),
    Saran("Pemantauan Tekanan Darah dan Kadar Kolesterol:","Rutin memantau tekanan darah dan kadar kolesterol untuk mendeteksi dan mengelola potensi risiko penyakit jantung.")
)

val saranPenanganan = listOf(
    Saran("Konsultasi dengan Dokter atau spesialis kardiovaskular:","Dokter akan melakukan pemeriksaan fisik, menilai riwayat kesehatan, dan mungkin melakukan tes diagnostik seperti elektrokardiogram (EKG), tes darah, atau pencitraan jantung."),
    Saran("Perubahan Gaya Hidup:","Pertama, adopsi pola makan sehat dengan mengurangi asupan garam, lemak jenuh, dan meningkatkan konsumsi serat dapat membantu mengendalikan tekanan darah dan kolesterol. Selain itu, melibatkan diri dalam aktivitas fisik yang sesuai dengan kondisi kesehatan adalah langkah penting; sebaiknya diskusikan rencana latihan baru dengan dokter sebelum memulainya. Berhenti merokok juga merupakan keputusan besar yang dapat memberikan manfaat signifikan bagi kesehatan jantung. Terakhir, pengelolaan stres sehari-hari melalui praktik seperti meditasi, yoga, atau mengejar hobi yang menenangkan dapat membantu menjaga keseimbangan mental dan emosional, mendukung kesehatan jantung secara menyeluruh.")
)
