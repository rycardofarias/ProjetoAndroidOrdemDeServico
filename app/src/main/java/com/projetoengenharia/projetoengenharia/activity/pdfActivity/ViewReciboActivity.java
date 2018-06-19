package com.projetoengenharia.projetoengenharia.activity.pdfActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.projetoengenharia.projetoengenharia.R;

import java.io.File;

public class ViewReciboActivity extends AppCompatActivity {

    private PDFView pdfView;
    private File arquivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recibo);
        pdfView=(PDFView)findViewById(R.id.pdfViewReciboId);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            arquivo=new File(bundle.getString("path",""));
        }
        pdfView.fromFile(arquivo)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableAntialiasing(true)
                .load();


    }
}
