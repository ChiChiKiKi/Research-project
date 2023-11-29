package com.example.cd_ptc;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity0 extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener{
        ExpandableListView listView;
        String[] groups = {"설문지 작성", "설문지 카테고리 등록", "설문조사 참여", "랭킹"};
        String[][] childs = {{"작성하기", "비슷한 설문지 확인하기"}, {"카테고리 작성", "카테고리 유사성 확인"},
                {"설문조사 하러가기",""}, {"카테고리별 랭킹확인", "전체 랭킹확인"} };

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton menu1 = (ImageButton) findViewById(R.id.show_menu);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.main_container);

        ExpandableListAdapter listAdapter = new MyExpandableListAdapter();

        listView = (ExpandableListView) findViewById(R.id.menu_list);
        // listView에 listAdapter를 설정해서, 리스트뷰의 각 항목을 어떻게 표시할지 결정
        listView.setAdapter(listAdapter);
        // listView의 부모항목 클릭 이벤트 설정 (this는 이벤트 리스너를 구현한 객체)
        listView.setOnGroupClickListener(this);
        // listView의 자식항목 클릭 이벤트 설정 (this는 이벤트 리스너를 구현한 객체)
        listView.setOnChildClickListener(this);


        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.getVisibility() == View.VISIBLE){
                    listView.setVisibility(View.GONE);
                }
                else{
                    listView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // 확장 리스트뷰에서 사용하는 기본 클래스인 BaseExpandableListAdapter를 상속해서 원하는 데이터와 원하는 동작 설정
    public class MyExpandableListAdapter extends BaseExpandableListAdapter{
        // 주어진 그룹 위치와 차일드 위치에 해당하는 차일드 아이템 반환
        // childs변수는 MainActivity클래스의 멤버 변수로 선언함.
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childs[groupPosition][childPosition];
        }

        // 차일드 아이디를 반환.
        // 차일드 아이디는 유일한 값이면 되므로 childPosition을 그대로 사용함. <<-- 배열 인덱스는 유일한 값이기 때문
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childs[groupPosition].length;
        }

        // 그룹과 차일드 아이템을 보여줄 텍스트뷰를 직접 생성
        // 이 메소드에서는 텍스트뷰의 레이아웃과 패딩을 직접 설정하지만, 인플레이션을 사용해서하는 것이 더 나은 방법임.

        public TextView getGenericView(){
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
            TextView textView = new TextView(Activity0.this);
            textView.setLayoutParams(lp);
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            textView.setPadding(36, 0,0,0);
            return textView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition, childPosition).toString());
            textView.setPadding(60,0,0,0);
            return textView;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        // 그룹아이디를 반환
        // groupPosition을 그대로 사용함 ---> 배열 인덱스는 유일한 값이므로
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());

            //그룹 표시 아이콘 존재 여부
            listView.setGroupIndicator(null);

            return textView;
        }

        // 그룹과 차일드가 반환하는 아이디가 항상 같으면 true
        // 배열 인덱스는 항상 groupPosition으로 고정이므로 true를 반환
        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(this, childs[groupPosition][childPosition] + "을 선택하셨습니다", Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        Toast.makeText(this, groups[groupPosition] + "을 선택하셨습니다", Toast.LENGTH_SHORT).show();

        return false;
    }
}
