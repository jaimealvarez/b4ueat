package com.example.b4ueat.demo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button scanBtn;
	private TextView formatTxt, contentTxt;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        
        scanBtn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onClick(View v){
    	//respond to clicks
    	if(v.getId()==R.id.scan_button){
    		//scan
    		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
    		scanIntegrator.initiateScan();
    	}
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	//retrieve scan result
    	IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    	if (scanningResult != null) {
    		//we have a result
    		String scanContent = scanningResult.getContents();
    		String scanFormat = scanningResult.getFormatName();
    		formatTxt.setText("Formato: " + scanFormat);
    		contentTxt.setText("C�digo: " + scanContent);
    	} else {
    	    Toast toast = Toast.makeText(getApplicationContext(),
    	        "No scan data received!", Toast.LENGTH_SHORT);
    	    toast.show();
    	}
    }
}
