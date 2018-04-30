package com.example.ivyng.oantuti;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public enum COMMAND{
        BAO,
        BUA,
        KEO
    }
    public enum RESULT {
        HOA,
        THANG,
        THUA
    }

    COMMAND myCmd;
    ImageView imgvBao,imgvBua,imgvKeo,imgvCmd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    public void Object_click(View view) {
        switch (view.getId()){
            case R.id.imageViewbao:
                myCmd=COMMAND.BAO;
                imgvCmd.setImageResource(R.drawable.baoimage);
                break;
            case R.id.imageViewbua:
                myCmd=COMMAND.BUA;
                imgvCmd.setImageResource(R.drawable.buaimage);
                break;
            case R.id.imageViewkeo:
                myCmd=COMMAND.KEO;
                imgvCmd.setImageResource(R.drawable.keoimage);
                break;
        }

        notify(checkCommand(myCmd,COMMAND.BAO),myCmd,COMMAND.BAO);
    }

    private void notify(RESULT result,COMMAND player,COMMAND competitor){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game oẳn tù tì");
        builder.setMessage(" Bạn ra: "+player.toString()+"\n Đối thủ ra: "+competitor.toString());
        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        switch (result){
            case HOA:
                builder.setTitle("Bạn đã hòa");break;
            case THANG:
                builder.setTitle("Bạn đã thắng !");break;
            case THUA:
                builder.setTitle("Bạn đã thua");break;
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private RESULT checkCommand(COMMAND player, COMMAND competitor){
        switch (player){
            case BAO:
                switch (competitor){
                    case BAO: return RESULT.HOA;
                    case BUA: return RESULT.THANG;
                    case KEO:return RESULT.THUA;
                }
            case BUA:
                switch (competitor){
                    case BAO: return RESULT.THUA;
                    case BUA: return RESULT.HOA;
                    case KEO:return RESULT.THANG;
                }
            case KEO:
                switch (competitor){
                    case BAO: return RESULT.THANG;
                    case BUA: return RESULT.THUA;
                    case KEO:return RESULT.HOA;
                }
        }
        return RESULT.HOA;
    }

    private void init(){
        imgvBao = (ImageView) findViewById(R.id.imageViewbao);
        imgvBua = (ImageView) findViewById(R.id.imageViewbua);
        imgvKeo = (ImageView) findViewById(R.id.imageViewkeo);
        imgvCmd = (ImageView) findViewById(R.id.imgvcmd);
    }
}
