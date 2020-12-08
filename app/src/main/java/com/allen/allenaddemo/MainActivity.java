package com.allen.allenaddemo;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){

        expandableListView=findViewById(R.id.listview);
        expandableListAdapter=new ExpandableListAdapter() {
            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getGroupCount() {
                return adtypes.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return networks[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return adtypes[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return networks[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean b, View convertView, ViewGroup viewGroup) {
                GroupViewHolder groupViewHolder;
                if (convertView == null) {
                    convertView = View.inflate(MainActivity.this,
                            android.R.layout.simple_expandable_list_item_2, null);
                    groupViewHolder = new GroupViewHolder();
                    groupViewHolder.tvTitle = (TextView) convertView.findViewById(android.R.id.text1);
                    convertView.setTag(groupViewHolder);
                } else {
                    groupViewHolder = (GroupViewHolder) convertView.getTag();
                }
                if (adtypes[groupPosition]!=null){
                groupViewHolder.tvTitle.setText(adtypes[groupPosition]);
                }
                return convertView;

            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
                ChildViewHolder childViewHolder;
                if (convertView == null) {
                    convertView = View.inflate(MainActivity.this,
                            android.R.layout.simple_list_item_2, null);
                    childViewHolder = new ChildViewHolder();
                    childViewHolder.tvTitle = (TextView) convertView.findViewById(android.R.id.text2);
                    convertView.setTag(childViewHolder);
                } else {
                    childViewHolder = (ChildViewHolder) convertView.getTag();
                }
                if (networks[groupPosition][childPosition]!=null){
                    childViewHolder.tvTitle.setText(networks[groupPosition][childPosition]);
                }
                return convertView;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void onGroupExpanded(int i) {

            }

            @Override
            public void onGroupCollapsed(int i) {

            }

            @Override
            public long getCombinedChildId(long l, long l1) {
                return 0;
            }

            @Override
            public long getCombinedGroupId(long l) {
                return 0;
            }
        };
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                MyApplicaiton.selectedNetwordk=networks[groupPosition][childPosition];
                switch (groupPosition){
                    case 0://Native
                        Intent intent0 =new Intent(MainActivity.this,NativeActivity.class);
                        startActivity(intent0);
                        break;
                    case 1://RewardedVideo
                        Intent intent1 =new Intent(MainActivity.this,RewardedActivity.class);
                        startActivity(intent1);
                        break;
                    case 2://SplashAd
                        Intent intent2 =new Intent(MainActivity.this,SplashAdActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });

    }
    static class GroupViewHolder {
        TextView tvTitle;
    }
    static class ChildViewHolder {
        TextView tvTitle;
    }
}

