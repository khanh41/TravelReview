package com.example.travelreview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.travelreview.R.id.image_view;

public class Information extends AppCompatActivity {
    private Button btn_finish,buttonCmt;
    private ImageView imageView,imgCurrentUser;
    private EditText editTextComment;
    String PostKey;
    private TextView tv_trichdan,txtPostDateName;
    private TextView tv_head;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    RecyclerView RvComment;
    CommentAdapter commentAdapter;
    List<Comment> listComment;
    static String COMMENT_KEY = "Comment" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

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
                tv_trichdan.setText(arrayList.get(i).getContent());
            }
        }
        RvComment = findViewById(R.id.rv_comment);
        buttonCmt = findViewById(R.id.post_detail_add_comment_btn);
        imgCurrentUser = findViewById(R.id.post_detail_currentuser_img);
        editTextComment = findViewById(R.id.post_detail_comment);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        buttonCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCmt.setVisibility(View.INVISIBLE);
                DatabaseReference commentReference = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey).push();
                String comment_content = editTextComment.getText().toString();
                String uid = firebaseUser.getUid();
                String uname = firebaseUser.getDisplayName();
                String uimg = firebaseUser.getPhotoUrl().toString();
                Comment comment = new Comment(comment_content,uid,uimg,uname);
                Log.d("pluto","zcx");
                commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showMessage("comment added");
                        editTextComment.setText("");
                        buttonCmt.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("fail to add comment : "+e.getMessage());
                    }
                });

            }
        });
        Glide.with(this).load(firebaseUser.getPhotoUrl()).into(imgCurrentUser);
        // get post id
        PostKey = getIntent().getExtras().getString("name");
        // ini Recyclerview Comment
        iniRvComment();
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void iniRvComment() {

        RvComment.setLayoutManager(new LinearLayoutManager(this));
        Log.d("pluto","zcx");
        DatabaseReference commentRef = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listComment = new ArrayList<>();
                for (DataSnapshot snap:dataSnapshot.getChildren()) {

                    Comment comment = snap.getValue(Comment.class);
                    listComment.add(comment) ;
                }

                commentAdapter = new CommentAdapter(getApplicationContext(),listComment);
                RvComment.setAdapter(commentAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void showMessage(String message) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }
}
