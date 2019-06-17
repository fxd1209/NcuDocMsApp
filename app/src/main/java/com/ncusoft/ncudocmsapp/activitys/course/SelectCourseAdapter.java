package com.ncusoft.ncudocmsapp.activitys.course;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.pojo.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectCourseAdapter extends BaseAdapter {
    private int currentSel=-1; //当前选择项
    private int count_0_Position=0;  //记录0的次数，具体见getView。
    private Context context;//声明适配器中引用的上下文
    private LayoutInflater layoutInflater; //布局加载器
    private SelectCourseAdapter.ViewHolder viewHolder=null;

    private List<Course> courseList;  //所有课程
    private Map<String,Course> mapSelected; //记录被选中的课程
    private ArrayList<Boolean> isSelectedList; //标记点击的状态

    private int[] imgId={
            R.drawable.course_1,R.drawable.course_2,
            R.drawable.course_3,R.drawable.course_4,
            R.drawable.course_5,R.drawable.course_6,
            R.drawable.course_7,R.drawable.course_8};

    public SelectCourseAdapter(Context context, List<Course> courseList){
        this.context=context;
        this.courseList=courseList;
        this.layoutInflater=LayoutInflater.from(context);
        this.mapSelected=new HashMap<>();
        resetIsSelectedList();

    }

    public void setCurrentSel(int currentSel) {
        this.currentSel = currentSel;
    }
    public int getCurrentSel(){
        return currentSel;
    }
    public List<Course> getCourseList() {
        return courseList;
    }
    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        resetIsSelectedList();
    }
    public void resetIsSelectedList(){
        isSelectedList=new ArrayList<>();
        for (int i=0;i<courseList.size();i++){
            isSelectedList.add(false);
        }
    }
    public void remove(int index){
        if (index>=0 && index <courseList.size()){
            courseList.remove(index);
            isSelectedList.remove(index);
        }
    }
    public void refreshDataSet(){
        notifyDataSetChanged();
    }
    /**
     * 数据量
     * @return
     */
    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.course_item,null);
            viewHolder=new SelectCourseAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(SelectCourseAdapter.ViewHolder)convertView.getTag();
        }
        viewHolder.imgImgV.setImageResource(imgId[position%7+1]);
        viewHolder.courseIdTxtV.setText(courseList.get(position).getId());
        viewHolder.courseNameTxtV.setText(courseList.get(position).getName());
        //TODO:疑惑，position为0的时候会进入两次，也就是返回两次convertView,当点击第一个的时
        // 候，修改了背景，但是第二次返回的时候是又换成了原来的。
        //处理为0的时候
        if (position==0 && currentSel==position){
            count_0_Position++;
            if (count_0_Position==2){
                setClickItemBack(position);
                addSelectedCourse(position);
                currentSel=-1;
                count_0_Position=0;
            }
        }
        //根据当前是否选中状态来改变颜色
        if (position!=0 && currentSel==position){
            setClickItemBack(position);
            addSelectedCourse(position);
            currentSel=-1;
        }
        return convertView;
    }

    public void setClickItemBack(int position){
        if (isSelectedList.get(position)){
            viewHolder.imgImgV.setBackgroundColor(Color.TRANSPARENT);
            isSelectedList.set(position,false);
        } else {
            viewHolder.imgImgV.setBackgroundColor(Color.BLUE);
            isSelectedList.set(position,true);
        }
    }
    public void addSelectedCourse(int position){
        Course temp=courseList.get(position);
        mapSelected.put(temp.getId(),temp);
    }
    public Map<String,Course> getSelectedCourse(){
        return mapSelected;
    }
    class ViewHolder{
        private ImageView imgImgV;
        private TextView courseIdTxtV;
        private TextView courseNameTxtV;
        private View itemView;

        public ViewHolder(View itemView){
            if (itemView==null)
                throw new IllegalArgumentException("itemView can not be null!");
            this.itemView=itemView;
            imgImgV=(ImageView)itemView.findViewById(R.id.course_item_img);
            courseIdTxtV=(TextView)itemView.findViewById(R.id.course_item_courseId);
            courseNameTxtV=(TextView)itemView.findViewById(R.id.course_item_courseName);
        }
    }
}
