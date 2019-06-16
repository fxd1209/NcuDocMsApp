package com.ncusoft.ncudocmsapp.activitys.course;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.pojo.TeacherCourse;

import java.util.List;

public class CourseGridAdapter extends BaseAdapter {

    private Context context;//声明适配器中引用的上下文
    private LayoutInflater layoutInflater; //布局加载器
    private List<TeacherCourse> courseList;
    private ViewHolder viewHolder=null;
    private int[] imgId={
            R.drawable.course_1,R.drawable.course_2,
            R.drawable.course_3,R.drawable.course_4,
            R.drawable.course_5,R.drawable.course_6,
            R.drawable.course_7,R.drawable.course_8};

    public CourseGridAdapter(Context context, List<TeacherCourse> courseList){
        this.context=context;
        this.courseList=courseList;
        layoutInflater=LayoutInflater.from(context);
    }
    public List<TeacherCourse> getCourseList() {
        return courseList;
    }
    public void setCourseList(List<TeacherCourse> courseList) {
        this.courseList = courseList;
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
            convertView=layoutInflater.inflate(R.layout.course_item_grid,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.imgImgV.setImageResource(imgId[position%7]);
        viewHolder.nameTxtV.setText(courseList.get(position).getCourse().getName());
        viewHolder.termTxtV.setText(courseList.get(position).getTerm());
        viewHolder.classCountTxtV.setText(courseList.get(position).getClassCount());
        return convertView;
    }
    public void remove(int index){
        if (index>=0 && index <courseList.size())
            courseList.remove(index);
    }
    public void refreshDataSet(){
        notifyDataSetChanged();
    }

    class ViewHolder{
        private ImageView imgImgV;
        private TextView nameTxtV;
        private TextView termTxtV;
        private TextView classCountTxtV;
        private View itemView;

        public ViewHolder(View itemView){
            if (itemView==null)
                throw new IllegalArgumentException("itemView can not be null!");
            this.itemView=itemView;
            imgImgV=(ImageView)itemView.findViewById(R.id.course_item_grid_img);
            nameTxtV=(TextView)itemView.findViewById(R.id.course_item_grid_name);
            termTxtV=(TextView)itemView.findViewById(R.id.course_item_grid_term);
            classCountTxtV=(TextView)itemView.findViewById(R.id.course_item_grid_classCount);
        }
    }
}
