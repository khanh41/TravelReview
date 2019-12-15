package com.example.travelreview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.travelreview.R.id.image_view;

public class Information extends AppCompatActivity {
    private Button btn_finish;
    private ImageView imageView;
    private TextView tv_trichdan;
    private TextView tv_head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        btn_finish = findViewById(R.id.btn_finish);
        imageView = findViewById(image_view);
        tv_trichdan = findViewById(R.id.tv_trichdan);
        tv_head = findViewById(R.id.tv_name);

        ArrayList<ItemTravel> arrayList = new ArrayList<>();
        arrayList.add(new ItemTravel(R.drawable.causonghan,"Cầu sông Hàn","Cầu Sông Hàn là một trong những cây cầu bắc qua sông Hàn ở Đà Nẵng, miền Trung Việt Nam. Cầu được khởi công ngày 2 tháng 9 năm 1998, khánh thành ngày 29 tháng 3 năm 2000. Đây là cây cầu xoay đầu tiên do kỹ sư, công nhân Việt Nam tự thiết kế và thi công, và là cây cầu quay duy nhất ở Việt Nam hiện nay"));
        arrayList.add(new ItemTravel(R.drawable.caurong,"Cầu Rồng","Cầu Rồng là cây cầu thứ 6 và là cây cầu mới nhất bắc qua sông Hàn[1]. Vì cây cầu có hình dáng giống một con rồng nên được gọi là Cầu Rồng."));
        arrayList.add(new ItemTravel(R.drawable.cautinhyeu,"Cầu tình yêu","Cầu tình yêu bên sông Hàn (Đà Nẵng) là nơi mà nhiều đôi tình nhân có thể \"khóa\" tình yêu của mình để thể hiện sự vĩnh cửu, thủy chung. Cầu nằm ngay đầu cầu Rồng, đường Trần Hưng Đạo, quận Sơn Trà, Đà Nẵng. Đây là cụm công trình điểm nhấn bên bờ sông Hàn của thành phố Đà Nẵng, mở cửa miễn phí cho du khách tham quan."));
        arrayList.add(new ItemTravel(R.drawable.cauvang,"Cầu vàng","Cầu Vàng là tên một cây cầu bộ hành dài khoảng 150 m tại khu nghỉ dưỡng Bà Nà, Đà Nẵng, Việt Nam. Nằm ở độ cao khoảng 1.400 m trên núi Bà Nà, cầu nối liền trạm cáp treo với các khu vườn khác của khu nghỉ dưỡng. Ở giữa cầu có hai bàn tay lớn được tạc từ đá. Cầu Vàng chính thức được khánh thành vào tháng 6 năm 2018. "));
        arrayList.add(new ItemTravel(R.drawable.cocobay,"Cocobay","Tọa lạc ở vị trí trung tâm du lịch Đà Nẵng - Hội An, tổ hợp Du lịch và Giải trí Cocobay như một viên ngọc tuyệt mỹ nổi bật giữa khung cảnh nên thơ của biển trời Đà Nẵng. Với tổng diện tích 31 ha bao gồm bãi biển xanh ngát trải dài 600m thơ mộng, Cocobay mang đến những trải nghiệm chưa từng có về du lịch, lưu trú, giải trí và là biểu tượng mới của phong cách sống thời thượng."));

        Intent intent = getIntent();
        for (int i =0; i<arrayList.size(); i++){
            int x = arrayList.get(i).getName().compareTo(intent.getStringExtra("name"));
            if(x==0){
                String url = "R.id."+intent.getStringExtra("name");
                tv_head.setText(arrayList.get(i).getName());
                this.imageView.setImageResource(arrayList.get(i).getImage());
                Toast.makeText(this, ""+arrayList.get(i).getContent(), Toast.LENGTH_SHORT).show();
                tv_trichdan.setText(arrayList.get(i).getContent());
            }
        }
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
