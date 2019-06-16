package com.ncusoft.ncudocmsapp.activitys.course;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.pojo.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends BaseAdapter {
    private Context context;//声明适配器中引用的上下文
    private LayoutInflater layoutInflater; //布局加载器
    private List<Course> courseList;
    private CourseAdapter.ViewHolder viewHolder=null;

    private int currentSel=-1; //当前选择项
    private ArrayList<Boolean> isSelectedList; //标记点击的状态

    private int[] imgId={
            R.drawable.course_1,R.drawable.course_2,
            R.drawable.course_3,R.drawable.course_4,
            R.drawable.course_5,R.drawable.course_6,
            R.drawable.course_7,R.drawable.course_8};

    public CourseAdapter(Context context, List<Course> courseList){
        this.context=context;
        this.courseList=courseList;
        this.layoutInflater=LayoutInflater.from(context);
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
            viewHolder=new CourseAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(CourseAdapter.ViewHolder)convertView.getTag();
        }
        Log.i("posit:",String.valueOf(position));
        //根据当前是否选中状态来改变颜色
        if (currentSel==position){

            if (isSelectedList.get(position)){
                convertView.setBackgroundColor(Color.TRANSPARENT);
                isSelectedList.set(position,false);
            } else {
                convertView.setBackgroundColor(Color.BLUE);
                isSelectedList.set(position,true);
            }
            currentSel=-1;
        }

        viewHolder.imgImgV.setImageResource(imgId[position%7]);
        viewHolder.courseIdTxtV.setText(courseList.get(position).getId());
        viewHolder.courseNameTxtV.setText(courseList.get(position).getName());

        return convertView;
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
